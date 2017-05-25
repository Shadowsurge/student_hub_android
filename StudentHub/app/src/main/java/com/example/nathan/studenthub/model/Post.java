package com.example.nathan.studenthub.model;

/**
 * Created by Nathan on 14/05/2017.
 */

public class Post
{
    private String _id;
    private String title;
    private String content;
    private String datePosted;
    private String category;
    private String submittedBy;
    private String username;
    private String userId;

    public Post(String _id, String title, String content, String datePosted, String category, String submittedBy, String username, String userId)
    {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.datePosted = datePosted;
        this.category = category;
        this.submittedBy = submittedBy;
        this.username = username;
        this.userId = userId;
    }

    public String get_id()
    {
        return _id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getContent()
    {
        return content;
    }

    public String getDatePosted()
    {
        return datePosted;
    }

    public String getCategory()
    {
        return category;
    }

    public String getSubmittedBy()
    {
        return submittedBy;
    }

    public String getUsername()
    {
        return username;
    }

    public String getUserId()
    {
        return userId;
    }
}
