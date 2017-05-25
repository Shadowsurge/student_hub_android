package com.example.nathan.studenthub.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.Config;
import com.example.nathan.studenthub.model.CustomAlertDialog;
import com.example.nathan.studenthub.model.User;
import com.google.gson.Gson;

public class NavDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    protected DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView help = (ImageView) findViewById(R.id.helpImageView);
        help.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = NavDrawer.this.getClass().getSimpleName();

                switch(name)
                {
                    case Config.HOME_FEED_NAME:
                        new CustomAlertDialog(NavDrawer.this).showDialog(getResources().getStringArray(R.array.home_feed_help));
                        break;

                    case Config.UNIVERSITY_SERVICES_NAME:
                        new CustomAlertDialog(NavDrawer.this).showDialog(getResources().getStringArray(R.array.university_services_help));
                        break;

                    case Config.MENTAL_HEALTH_SERVICES_NAME:
                        new CustomAlertDialog(NavDrawer.this).showDialog(getResources().getStringArray(R.array.mental_health_help));
                        break;

                    case Config.ADVERT_LISTING_NAME:
                        new CustomAlertDialog(NavDrawer.this).showDialog(getResources().getStringArray(R.array.advert_listing_help));
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "It didnt work", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

        });
    }

    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        switch(id)
        {
            case R.id.nav_home:
                intent = new Intent(this, HomeFeed.class);
                startActivity(intent);
                break;

            case R.id.nav_university_services:
                intent = new Intent(this, UniversityServices.class);
                startActivity(intent);
                break;

            case R.id.nav_mental_health:
                intent = new Intent(this, MentalHealthResources.class);
                startActivity(intent);
                break;


            case R.id.nav_physical_health:
                Toast.makeText(this, "Physical health was clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_art:
                intent = new Intent(this, AdvertListing.class);
                intent.putExtra(Config.LIVE_ADVERT_RESULTS, Config.ART_ADVERTS);
                intent.putExtra(Config.TITLE, getString(R.string.art_adverts_title));
                startActivity(intent);
                break;

            case R.id.nav_dentistry:
                intent = new Intent(this, AdvertListing.class);
                intent.putExtra(Config.LIVE_ADVERT_RESULTS, Config.DENTISTRY_ADVERTS);
                intent.putExtra(Config.TITLE, getString(R.string.dentistry_adverts_title));
                startActivity(intent);
                break;

            case R.id.nav_education:
                intent = new Intent(this, AdvertListing.class);
                intent.putExtra(Config.LIVE_ADVERT_RESULTS, Config.EDUCATION_ADVERTS);
                intent.putExtra(Config.TITLE, getString(R.string.education_adverts_title));
                startActivity(intent);
                break;

            case R.id.nav_humanities:
                intent = new Intent(this, AdvertListing.class);
                intent.putExtra(Config.LIVE_ADVERT_RESULTS, Config.HUMANITIES_ADVERTS);
                intent.putExtra(Config.TITLE, getString(R.string.humanities_adverts_title));
                startActivity(intent);
                break;

            case R.id.nav_life_sciences:
                intent = new Intent(this, AdvertListing.class);
                intent.putExtra(Config.LIVE_ADVERT_RESULTS, Config.LIFE_SCIENCE_ADVERTS);
                intent.putExtra(Config.TITLE, getString(R.string.life_sciences_adverts_title));
                startActivity(intent);
                break;

            case R.id.nav_medicine:
                intent = new Intent(this, AdvertListing.class);
                intent.putExtra(Config.LIVE_ADVERT_RESULTS, Config.MEDICINE_ADVERTS);
                intent.putExtra(Config.TITLE, getString(R.string.medicine_adverts_title));
                startActivity(intent);
                break;

            case R.id.nav_nursing:
                intent = new Intent(this, AdvertListing.class);
                intent.putExtra(Config.LIVE_ADVERT_RESULTS, Config.NURSING_ADVERTS);
                intent.putExtra(Config.TITLE, getString(R.string.nursing_adverts_title));
                startActivity(intent);
                break;

            case R.id.nav_science:
                intent = new Intent(this, AdvertListing.class);
                intent.putExtra(Config.LIVE_ADVERT_RESULTS, Config.SCIENCE_ADVERTS);
                intent.putExtra(Config.TITLE, getString(R.string.science_adverts_title));
                startActivity(intent);
                break;

            case R.id.nav_social_sciences:
                intent = new Intent(this, AdvertListing.class);
                intent.putExtra(Config.LIVE_ADVERT_RESULTS, Config.SOCIAL_SCIENCE_AVDERTS);
                intent.putExtra(Config.TITLE, getString(R.string.social_science_adverts_title));
                startActivity(intent);
                break;

            case R.id.nav_create_advert:
                intent = new Intent(this, CreateAdvert.class);
                startActivity(intent);
                break;

            case R.id.nav_logout:
                logout();
                break;

            // TODO default return all live ads
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void setUser(User currentUser)
    {
        SharedPreferences userPrefs = getSharedPreferences(Config.SHARED_PREFS_TAG, MODE_PRIVATE);

        SharedPreferences.Editor editor = userPrefs.edit();
        editor.putString(Config.USERNAME_TAG, currentUser.getUsername());
        editor.putString(Config.USER_ID_TAG, currentUser.get_id());
        editor.putString(Config.USER_EMAIL_TAG, currentUser.getEmail());

        String userAdverts = new Gson().toJson(currentUser.getAdverts());
        editor.putString(Config.USER_ADVERTS_TAG, userAdverts);
        editor.putBoolean(Config.USER_ADMIN_LEVEL_TAG, currentUser.isAdmin());
        editor.apply();
    }

    protected User getUser()
    {
        SharedPreferences userPrefs = getSharedPreferences(Config.SHARED_PREFS_TAG, MODE_PRIVATE);
//        List<Advert> adverts = new Gson().fromJson(userPrefs.getString(USER_ADVERTS, "Error"), new Advert);
        return new User(userPrefs.getString(Config.USERNAME_TAG, "Error fetching Username"), userPrefs.getString(Config.USER_ID_TAG, "Error fetching ID"), userPrefs.getString(Config.USER_EMAIL_TAG, "Error fetching email"), userPrefs.getBoolean(Config.USER_ADMIN_LEVEL_TAG, false));
    }

    protected void LoadCurrentView(int layout)
    {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layout, null, false);
        drawer.addView(contentView, 0);
    }

    protected void SetupNav()
    {
        User user = getUser();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_nav_drawer);

        TextView username = (TextView) headerView.findViewById(R.id.nav_drawer_username);
        TextView email = (TextView) headerView.findViewById(R.id.nav_drawer_email);

        username.setText(user.getUsername());
        email.setText(user.getEmail());
    }

    private void logout()
    {
        SharedPreferences userPrefs = getSharedPreferences(Config.SHARED_PREFS_TAG, MODE_PRIVATE);

        if(userPrefs.contains(Config.USERNAME_TAG))
        {
            SharedPreferences.Editor editor = userPrefs.edit();
            editor.clear();
            editor.apply();

            Intent logout = new Intent(NavDrawer.this, MainActivity.class);
            logout.putExtra("Finish", true);
            logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(logout);
            finish();
        }
    }
}
