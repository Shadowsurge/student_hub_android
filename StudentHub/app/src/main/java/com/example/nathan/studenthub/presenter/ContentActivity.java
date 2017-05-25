package com.example.nathan.studenthub.presenter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.MenuItem;

/**
 * Created by Nathan on 02/05/2017.
 */

enum FragmentPage
{
    ABOUT, SERVICES, CONTACT, TREATMENT
}

public class ContentActivity extends AppCompatActivity
{
    public static final String MENU_RESOURCE ="menu_resource";

    private MentalHealthPagerFragment current;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fragment);
        current = null;

        Bundle bundle = getIntent().getExtras();

        MenuItem item = (MenuItem) bundle.getSerializable(MENU_RESOURCE);

        if(item.isService())
        {
            ContentFragment fragment = new ContentFragment();
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeholder, fragment);
            fragmentTransaction.commit();
        }
        else
        {
            MentalHealthPagerFragment fragment = new MentalHealthPagerFragment();
            fragment.setArguments(bundle);
            this.current = fragment;

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeholder, fragment);
            fragmentTransaction.commit();
        }
    }

    public MentalHealthPagerFragment getCurrent()
    {
        return current;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        return super.dispatchTouchEvent(ev);
    }
}
