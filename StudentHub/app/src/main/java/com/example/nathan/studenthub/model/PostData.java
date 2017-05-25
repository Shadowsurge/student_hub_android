package com.example.nathan.studenthub.model;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nathan on 17/04/2017.
 */

enum UploadStatus
{
    WAITING, UPLOADING, NOT_INITIALISED, FAILED_EMPTY, SUCCESS
}

public class PostData extends StringRequest
{
    private static final String TAG = "PostData";

    private UploadStatus status;
    private final OnUploadComplete callback;

    private Map<String, String> params;

    public interface OnUploadComplete
    {
        void onUploadComplete(String data, UploadStatus status);
    }

    public PostData(OnUploadComplete callback, String username, String password, String url, Response.Listener<String> listener)
    {
        super(Method.POST, url , listener, null);

        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        this.callback = callback;
    }

    // Post route for login/login
    public PostData(String username, String password, String url, Response.Listener<String> listener)
    {
        super(Method.POST, url , listener, null);

        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        this.callback = null;
    }

    // Post route for login/login
    public PostData(String username, String password, String email, String url, Response.Listener<String> listener)
    {
        super(Method.POST, url , listener, null);

        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);
        this.callback = null;
    }

    //Post route for creating an advert
    public PostData(String title, String content, String category, String email, String phone, String id, String username, String url, Response.Listener<String> listener)
    {
        super(Method.POST, url , listener, null);

        params = new HashMap<>();
        params.put("title", title);
        params.put("content", content);
        params.put("email", email);
        params.put("category", category);
        params.put("_id", id);
        params.put("username", username);
        params.put("phone", phone);
        this.callback = null;
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
