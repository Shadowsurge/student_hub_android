package com.example.nathan.studenthub.model;

import java.io.Serializable;

/**
 * Created by Nathan on 15/04/2017.
 */

// Serializable class representing adverts. Serialized for transfer via Intents
public class Advert implements Serializable
{
    // used to check the data is the same when stored and retrieved.
    private static final long serialID = 1L;

    private String _id;
    private String title;
    private String content;
    private String school;
    private String datePosted;
    private String author_id;
    private String username;
    private String email;
    private String phone;

    public Advert(String _id, String title, String content, String school, String datePosted, String author_id, String username, String email, String phone)
    {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.school = school;
        this.datePosted = datePosted;
        this.author_id = author_id;
        this.username = username;
        this.email = email;
        this.phone = phone;
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

    public String getSchool()
    {
        return school;
    }

    public String getDatePosted()
    {
        return datePosted;
    }

    public String getAuthor_id()
    {
        return author_id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone()
    {
        return phone;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", school='" + school + '\'' +
                ", datePosted='" + datePosted + '\'' +
                ", author_id='" + author_id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
