package com.example.nathan.studenthub.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.MenuItem;

/**
 * Created by Nathan on 02/05/2017.
 *
 *
 */

public class ServicesFragment extends UniversityServicesFragment
{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.data = GetData((MenuItem) getArguments().get(ContentActivity.MENU_RESOURCE), FragmentPage.SERVICES);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.page_two, container, false);
        CreateTextViews(new TextView[this.data.length],(LinearLayout) view.findViewById(R.id.services_layout));
        return view;
    }
}
