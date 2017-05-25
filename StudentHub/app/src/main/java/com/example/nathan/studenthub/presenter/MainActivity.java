package com.example.nathan.studenthub.presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.*;
import java.util.List;

public class MainActivity extends NavDrawer implements ParseDownloadData.OnDataReady
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.LoadCurrentView(R.layout.content_fragment);
//        setContentView(R.layout.content_fragment);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        SharedPreferences prefs = getSharedPreferences(Config.SHARED_PREFS_TAG, MODE_PRIVATE);
        if(prefs.contains(Config.USERNAME_TAG))
        {
            RedirectToHomeFeed();
        }
        else
        {
            MainActivityFragment mainActivityFragment = new MainActivityFragment();


            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeholder, mainActivityFragment);
            fragmentTransaction.commit();
        }
    }

    private void RedirectToHomeFeed()
    {
        Intent intent = new Intent(MainActivity.this, HomeFeed.class);
        startActivity(intent);
        finish();
    }

    public void LogUser(String username)
    {
        ParseDownloadData getData = new ParseDownloadData(MainActivity.this, Config.GET_USER_URL + username, Config.USER_DATA);
        getData.execute();
    }

    @Override
    public void onDataReady(List<Object> data, int status)
    {
        User user = (User) data.get(0);
        super.setUser(user);

        RedirectToHomeFeed();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        return super.dispatchTouchEvent(ev);
    }
}
