package com.example.nathan.studenthub.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.*;
import java.util.ArrayList;
import java.util.List;

public class AdvertListing extends NavDrawer implements ParseDownloadData.OnDataReady
        , RecyclerItemClickListener.OnClickListener
{
    private ListRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.LoadCurrentView(R.layout.activity_recycler_listing);
        super.SetupNav();
    }

    @Override
    protected void onResume()
    {

        super.onResume();
        Intent intent = getIntent();

        this.setTitle(intent.getStringExtra(Config.TITLE));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.placeholderRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // attach the onclick listener
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, this));

        recyclerViewAdapter = new ListRecyclerViewAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(recyclerViewAdapter);

        String url = intent.getStringExtra(Config.LIVE_ADVERT_RESULTS);
        ParseDownloadData getData = new ParseDownloadData(this, url, Config.ADVERT_DATA);
        getData.execute();
    }

    // pass data to the recycler view
    @Override
    public void onDataReady(List<Object> adverts, int status)
    {
        if(status == Config.DOWNLOAD_SUCCESS)
        {
            recyclerViewAdapter.loadData(adverts);
        }
        else
        {
            Toast.makeText(this, "Data download failed", Toast.LENGTH_LONG).show();
        }
    }

    // ON ITEM CLICK LISTENER METHODS
    @Override
    public void onItemClick(View view, int position)
    {
        Intent intent = new Intent(AdvertListing.this, SpecificAdvertDetails.class);

        // serialize and send the photo object to the other activity.
        intent.putExtra(Config.ADVERT_TAG, recyclerViewAdapter.getAdvert(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position)
    {
        Toast.makeText(this, "Long tap at position " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        return super.dispatchTouchEvent(ev);
    }
}

