package com.example.nathan.studenthub.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.MenuItem;
import java.util.ArrayList;


/**
 * Created by Nathan on 11/05/2017.
 */

public class MentalHealthTreatmentFragment extends MentalHealthInformationFragment
{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.data = super.GetData((MenuItem) getArguments().get(ContentActivity.MENU_RESOURCE), FragmentPage.TREATMENT);

        if(this.data != null)
        {
            this.textViews = new TextView[data.length];
            this.IDs = new ArrayList<>();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if(this.data != null)
        {
            View view = inflater.inflate(R.layout.page_three, container, false);

            super.CreateTextViews(this.textViews,(LinearLayout) view.findViewById(R.id.contact_layout),this.IDs, (ScrollView) view.findViewById(R.id.page_three_scrollView));
            super.CreateTableOfContents(this.textViews, this.IDs, (ScrollView) view.findViewById(R.id.page_three_scrollView));
            return view;
        }
        ((ContentActivity) getActivity()).getCurrent().RemoveTab(1);
        return null;
    }
}
