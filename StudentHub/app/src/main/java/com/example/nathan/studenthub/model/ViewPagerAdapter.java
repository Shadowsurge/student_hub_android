package com.example.nathan.studenthub.model;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nathan on 27/04/2017.
 */

public class ViewPagerAdapter extends PagerAdapter
{
    private Context callback;

    @Override
    public Object instantiateItem(ViewGroup collection, int position)
    {
        InfoPage infoPages = InfoPage.values()[position];
        LayoutInflater inflater = LayoutInflater.from(callback);
        ViewGroup layout = (ViewGroup) inflater.inflate(infoPages.getLayoutID(), collection, false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view)
    {
        collection.removeView((View) view);
    }

    @Override
    public int getCount()
    {
        return InfoPage.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        InfoPage infoPage = InfoPage.values()[position];
        return callback.getString(infoPage.getTitleID());
    }
}
