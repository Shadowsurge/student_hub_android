package com.example.nathan.studenthub.model;

/**
 * Created by Nathan on 12/05/2017.
 *
 * Class tht is used for creating the menu buttons inside the recycler views. Used in University Services, Mental Health Information
 */
import java.io.Serializable;

// Serializable class representing adverts. Serialized for transfer via Intents
public class MenuItem implements Serializable
{
    // used to check the data is the same when stored and retrieved.
    private static final long serialID = 2L;

    private String title;
    private int titleID;
    private int imageID;
    private boolean isService;

    public MenuItem(String title, int imageID, boolean isService)
    {
        this.title = title;
        this.imageID = imageID;
        this.isService = isService;
    }

    public String getTitle()
    {
        return title;
    }

    public int getTitleID() {
        return titleID;
    }

    public boolean isService() {
        return isService;
    }

    int getImageID() {
        return imageID;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "title='" + title + '\'' +
                ", imageID=" + imageID +
                ", isService=" + isService +
                '}';
    }
}

