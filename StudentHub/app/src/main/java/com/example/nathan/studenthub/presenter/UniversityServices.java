package com.example.nathan.studenthub.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.MenuItem;
import com.example.nathan.studenthub.model.MenuRecyclerViewAdapter;
import com.example.nathan.studenthub.model.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class UniversityServices extends NavDrawer implements RecyclerItemClickListener.OnClickListener
{
    private MenuRecyclerViewAdapter recyclerViewAdapter;

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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.placeholderRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // attach the onclick listener
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, this));

        recyclerViewAdapter = new MenuRecyclerViewAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(recyclerViewAdapter);
        CreateMenuItems();
    }

    private void CreateMenuItems()
    {
        List<Object> buttons = new ArrayList<>();

        String[] universityServiceTitles = getResources().getStringArray(R.array.university_service_menu_titles);

        for(String title : universityServiceTitles)
        {
            MenuItem item = new MenuItem(title, R.drawable.enquiry_menu_icon, true);
            buttons.add(item);
        }
        recyclerViewAdapter.loadData(buttons);
    }

    @Override
    public void onItemClick(View view, int position)
    {
        Intent intent = new Intent(UniversityServices.this, ContentActivity.class);
        intent.putExtra(ContentActivity.MENU_RESOURCE, recyclerViewAdapter.getMenuItem(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position)
    {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
