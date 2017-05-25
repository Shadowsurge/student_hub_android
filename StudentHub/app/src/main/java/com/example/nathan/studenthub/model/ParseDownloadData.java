package com.example.nathan.studenthub.model;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseDownloadData extends AsyncTask<String, Void, List<Object>> implements DownloadData.OnDownloadComplete
{
    private List<Object> items = null;
    private boolean isSameThread = false;

    private String url;
    private final OnDataReady callback;
    private int dataToDownload;

    // interface for classes to receive the parsed data back
    public interface OnDataReady
    {
        void onDataReady(List<Object> data, int status);
    }

    // constructor
    public ParseDownloadData(OnDataReady callback, String url, int dataToDownload)
    {
        this.callback = callback;
        this.url = url;
        this.dataToDownload = dataToDownload;
    }

    @Override
    protected List<Object> doInBackground(String... params)
    {
        DownloadData getData = new DownloadData(this, this.dataToDownload);
        getData.runInBackgroundThread(this.url);
        return items;
    }

    @Override
    protected void onPostExecute(List list)
    {
        if(callback != null)
        {
            callback.onDataReady(this.items, Config.DOWNLOAD_SUCCESS);
        }
    }

    @Override
    public void onDownloadComplete(String data, int status, int dataType)
    {
        switch(dataType)
        {
            case Config.ADVERT_DATA:
                ParseAdverts(data, status);
                break;

            case Config.BLOG_DATA:
                ParseBlogs(data, status);
                break;

            case Config.USER_DATA:
                ParseUser(data, status);
                break;
        }
    }

    private void ParseAdverts(String data, int status)
    {
        if(status == Config.DOWNLOAD_SUCCESS)
        {
            this.items = new ArrayList<>();

            try
            {
                JSONObject json = new JSONObject(data);

                JSONArray adverts = json.getJSONArray("adverts");

                for(int i = 0; i < adverts.length(); i++)
                {
                    JSONObject newAdvert = adverts.getJSONObject(i);

                    // get the advert key value pairs from the JSON
                    String _id = newAdvert.getString("_id");
                    String title = newAdvert.getString("title");
                    String content = newAdvert.getString("content");
                    String school = newAdvert.getString("school");
                    String datePosted = newAdvert.getString("createdAt");

                    // Get the author key value pairs from the JSON
                    JSONObject author = newAdvert.getJSONObject("author");
                    String author_id = author.getString("id");
                    String username = author.getString("username");
                    String email = author.getString("email");
                    String phone = author.getString("phone");

                    // Create a new advert for the current item in the JSON array. Passing in all collected data, add it to the list
                    Advert advert = new Advert(_id, title, content, school, datePosted, author_id, username, email, phone);
                    this.items.add(advert);
                }
            }
            catch(JSONException error)
            {
                status = Config.DOWNLOAD_FAILED_OR_EMPTY;
            }
        }

        if(callback != null && isSameThread)
        {
            callback.onDataReady(this.items, status);
        }
    }

    private void ParseBlogs(String data, int status)
    {
        if(status == Config.DOWNLOAD_SUCCESS)
        {
            this.items = new ArrayList<>();

            try
            {
                JSONObject json = new JSONObject(data);

                JSONArray blogs = json.getJSONArray("blogs");

                for(int i = blogs.length() - 1; i >= 0; i--)
                {
                    JSONObject newBlog = blogs.getJSONObject(i);

                    String _id = newBlog.getString("_id");
                    String title = newBlog.getString("title");
                    String content = newBlog.getString("content");
                    String submittedBy = newBlog.getString("submittedBy");

                    JSONObject admin = newBlog.getJSONObject("approvedBy");
                    String admin_id = admin.getString("id");
                    String username = admin.getString("username");

                    Post blog = new Post(_id,title,content,"14/05/2017","Blog",submittedBy,username,admin_id);
                    this.items.add(blog);
                }
            }
            catch(JSONException error)
            {
                status = Config.DOWNLOAD_FAILED_OR_EMPTY;
            }
        }

        if(callback != null && isSameThread)
        {
            callback.onDataReady(this.items, status);
        }
    }

    private void ParseUser(String data, int status)
    {
        if(status == Config.DOWNLOAD_SUCCESS)
        {
            this.items = new ArrayList<>();
            try
            {
                JSONObject mainObject = new JSONObject(data);

                JSONArray userArray = mainObject.getJSONArray("loggedUser");

                String username = userArray.getJSONObject(0).getString("username");
                String _id = userArray.getJSONObject(0).getString("_id");
                String email;
                try
                {
                    email = userArray.getJSONObject(0).getString("email");
                }
                catch(Exception error)
                {
                    email = null;
                }
                //TODO parse adverts into GSON
//              List<Advert> adverts = userJson.getString("adverts");
                Boolean isAdmin = userArray.getJSONObject(0).getBoolean("isAdmin");

                //TODO add in adverts for user logged in
                User user = new User(username, _id, email, isAdmin);
                this.items.add(user);
            }
            catch(JSONException error)
            {
                Log.e("ON DOWNLOAD - LOGIN", "onResponse: JSON ERROR ", error);
            }
        }

        if(callback != null && isSameThread)
        {
            callback.onDataReady(this.items, status);
        }
    }
}
