package com.example.nathan.studenthub.presenter;

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

public class HomeFeed extends NavDrawer implements RecyclerItemClickListener.OnClickListener, ParseDownloadData.OnDataReady
{
    private HomeRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.LoadCurrentView(R.layout.activity_home_feed);
        super.SetupNav();

        CreateRecycler();
        LoadData();
    }

    private void CreateRecycler()
    {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.homeRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // attach the onclick listener
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, this));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getResources().getDrawable(R.drawable.home_feed_divider));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerViewAdapter = new HomeRecyclerViewAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void LoadData()
    {
        ParseDownloadData getData = new ParseDownloadData(this, Config.GET_BLOG_URL, Config.BLOG_DATA);
        getData.execute();
    }

    @Override
    public void onDataReady(List<Object> data, int status)
    {
        if(status == Config.DOWNLOAD_SUCCESS)
        {
            recyclerViewAdapter.loadData(data);
        }
        else
        {
            Toast.makeText(this, "Error fetching home feed, please refresh your device", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(View view, int position)
    {

    }

    @Override
    public void onItemLongClick(View view, int position)
    {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        return super.dispatchTouchEvent(ev);
    }
}
