package com.example.nathan.studenthub.model;


import com.example.nathan.studenthub.R;

public enum InfoPage
{
    PAGE_ONE(R.string.page_one, R.layout.page_one),
    PAGE_TWO(R.string.page_two, R.layout.page_two),
    PAGE_THREE(R.string.page_three, R.layout.page_three);

    private int titleID;
    private int layoutID;

    InfoPage(int titleID, int layoutID)
    {
        this.titleID = titleID;
        this.layoutID = layoutID;
    }

    public int getTitleID()
    {
        return this.titleID;
    }

    public int getLayoutID()
    {
        return this.layoutID;
    }
}