package com.example.nathan.studenthub.model;

import java.util.List;

/**
 * Created by Nathan on 25/04/2017.
 */

public class User
{
    private String username;
    private String _id;
    private String email;
    private List<Advert> adverts;
    private boolean isAdmin;

    public User(String username, String _id, String email, boolean isAdmin)
    {
        if(email == null)
        {
            this.email = "No email provided";
        }
        else
        {
            this.email = email;
        }
        this.username = username;
        this._id = _id;
        this.isAdmin = isAdmin;
    }

    public String getUsername()
    {
        return username;
    }

    public String get_id()
    {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public List<Advert> getAdverts()
    {
        return adverts;
    }

    public boolean isAdmin()
    {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", _id='" + _id + '\'' +
                ", adverts=" + adverts +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
