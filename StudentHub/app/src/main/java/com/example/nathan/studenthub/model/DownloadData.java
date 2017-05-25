package com.example.nathan.studenthub.model;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Nathan on 15/04/2017.
 *
 * This class handles downloading the data from the API. The class has no need to know what the data is, it simply downloads it and
 * passes it to the parser - ParseDownloadData
 */

public class DownloadData extends AsyncTask<String, Void, String>
{
    private static final String TAG = "DownloadData";
    private int status;
    private int dataToDownload;
    private final OnDownloadComplete callback;

    // interface for classes that need to use this class for downloads
    public interface OnDownloadComplete
    {
        void onDownloadComplete(String data, int status, int dataType);

    }

    // create a new object to get the data and set its download status to WAITING
    DownloadData(OnDownloadComplete callback, int dataToDownload)
    {
        this.status = Config.DOWNLOAD_WAITING;
        this.callback = callback;
        this.dataToDownload = dataToDownload;
    }

    // runs in background when this class is activated
    @Override
    protected String doInBackground(String... params)
    {
        HttpURLConnection connection = null;

        BufferedReader reader = null;

        if(params == null)
        {
            this.status = Config.DOWNLOAD_NOT_INITIALISED;
            return null;
        }
        try
        {
            this.status = Config.DOWNLOAD_PROCESSING;

            URL url = new URL(params[0]);

            // open the connection
            connection = (HttpURLConnection) url.openConnection();

            // Set the connection parameters
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.connect();
            //TODO - use response code to determine errors
//            int response = connection.getResponseCode();

            StringBuilder data = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            for(String line = reader.readLine(); line != null; line = reader.readLine())
            {
                data.append(line).append('\n');
            }

            this.status = Config.DOWNLOAD_SUCCESS;
            return data.toString();
        }
        catch(MalformedURLException error)
        {
            Log.e(TAG, "URL error in Async task - " + error.getMessage());
            // handle url being wrong
        }
        catch(IOException error)
        {
            Log.e(TAG, "IO Exception in Async task - " + error.getMessage());
            // Handle IO error
        }
        catch(SecurityException error)
        {
            Log.d(TAG, "Permissions error in Async task - " + error.getMessage());
            // handle permissions error
        }
        finally
        {
            // Close connection if present
            if(connection != null)
            {
                connection.disconnect();
            }
            // Close reader if present
            if(reader != null)
            {
                try
                {
                    reader.close();
                }
                catch(IOException error)
                {
                    Log.e(TAG, "IO error closing reader");
                }
            }
        }

        this.status = Config.DOWNLOAD_FAILED_OR_EMPTY;
        return null;
    }

    // runs after doInBackground and uses the returned value from it
    @Override
    protected void onPostExecute(String data)
    {
        if(callback != null)
        {
            callback.onDownloadComplete(data, this.status, this.dataToDownload);
        }
    }

    void runInBackgroundThread(String request)
    {
        if(callback != null)
        {
            callback.onDownloadComplete(doInBackground(request), this.status, this.dataToDownload);
        }
    }
}

