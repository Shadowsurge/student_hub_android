package com.example.nathan.studenthub.presenter;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.CustomTextViewParams;
import com.example.nathan.studenthub.model.MenuItem;

import java.util.List;

/**
 * Created by Nathan on 11/05/2017.
 */

public class MentalHealthInformationFragment extends Fragment
{
    protected String[] data;
    protected TextView[] textViews;
    protected List<Integer> IDs;

    protected boolean isTableOpen = false;

    protected void CreateTextViews(TextView[] textViews, LinearLayout layout,List<Integer> ids, final ScrollView scrollView)
    {
        CustomTextViewParams customTextViewParams = new CustomTextViewParams(getActivity());

        for(int i = 0; i < data.length; i++)
        {
            // Create textview and set text size
            textViews[i] = new TextView(getActivity());
            textViews[i].setTextSize(14);

            // Determine what API level user has. Parse html accordingly
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            {
                textViews[i].setText(Html.fromHtml(data[i],Html.FROM_HTML_MODE_LEGACY));
            }
            else
            {
                textViews[i].setText(Html.fromHtml(data[i]));
            }

            if(data[i].contains("id=backToTop"))
            {
                textViews[i].setGravity(Gravity.CENTER);
                textViews[i].setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                textViews[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scrollView.smoothScrollTo(0,0);
                    }
                });
            }
            else if(data[i].contains("id=headerTag"))
            {
                textViews[i].setId(i);
                ids.add(textViews[i].getId());
            }

            // Add layout params to the textview and add it to the view
            textViews[i].setLayoutParams(customTextViewParams.GetParams());
            layout.addView(textViews[i]);
        }
    }

    protected void CreateTableOfContents(final TextView[] links, final List<Integer> ids, final ScrollView scrollView)
    {
        if(data[0].contains("id=toggleTable"))
        {
            links[0].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    ToggleTable(links, ids);
                }
            });
        }

        for(int i = 0; i < ids.size(); i++)
        {
            if(links[i] != null)
            {
                links[i].setGravity(Gravity.CENTER);
                links[i].setTextColor(getResources().getColor(android.R.color.holo_blue_dark));

                final int j = i;

                if(data[i].contains("id=contentAnchor"))
                {
                    links[i].setVisibility(View.GONE);
                    links[i].setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v) {
                            scrollView.smoothScrollTo(0, (int) links[ids.get(j - 1)].getY());
                        }
                    });
                }
            }
        }

        // Set the last link. Done here as a glitch occurs that hasn't been fixed.
        // TODO - Discover why loop isn't activating the last element as an anchor link
        links[ids.size()].setGravity(Gravity.CENTER);
        links[ids.size()].setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        links[ids.size()].setVisibility(View.GONE);
        links[ids.size()].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0, (int) links[ids.get(ids.size() - 1)].getY());
            }
        });
    }

    private void ToggleTable(final TextView[] textViews, List<Integer> ids)
    {
        if(!isTableOpen)
        {
            for(int i = 1; i < ids.size(); i++)
            {
                textViews[i].setVisibility(View.VISIBLE);
            }
            textViews[ids.size()].setVisibility(View.VISIBLE);
            isTableOpen = true;
        }
        else
        {
            for(int i = 1; i < ids.size(); i++)
            {
                textViews[i].setVisibility(View.GONE);
            }
            textViews[ids.size()].setVisibility(View.GONE);
            isTableOpen = false;
        }
    }

    protected String[] GetData(MenuItem service, FragmentPage page)
    {
        switch(page)
        {
            case ABOUT:
                return loadAboutData(service);

            case TREATMENT:
                return loadTreatmentData(service);

            default:
                return null;
        }
    }

    private String[] loadAboutData(MenuItem service)
    {
        switch(service.getTitle())
        {
            case "Anger":
                return getResources().getStringArray(R.array.anger_about);

            case "Anxiety":
                return getResources().getStringArray(R.array.anxiety_about);

            case "Depression":
                return getResources().getStringArray(R.array.depression_about);

            case "Friendship":
                return getResources().getStringArray(R.array.friendship_about);

            case "Homesickness":
                return getResources().getStringArray(R.array.homesickness_about);

            case "Loneliness":
                return getResources().getStringArray(R.array.loneliness_about);

            case "Loss of a loved one":
                return getResources().getStringArray(R.array.losing_a_loved_one_about);

            case "Perfectionism":
                return getResources().getStringArray(R.array.perfectionism_about);

            case "Procrastination":
                return  getResources().getStringArray(R.array.procrastination_about);

            case "Self-Harm":
                return getResources().getStringArray(R.array.self_harm_about);

            case "Stress":
                return getResources().getStringArray(R.array.stress_about);

            default:
                return null;
        }
    }

    private String[] loadTreatmentData(MenuItem service)
    {
        switch(service.getTitle())
        {
            case "Anger":
                return getResources().getStringArray(R.array.anger_treatment);

            case "Anxiety":
                return getResources().getStringArray(R.array.anxiety_treatment);

            case "Depression":
                return getResources().getStringArray(R.array.depression_treatment);

            case "Homesickness":
                return getResources().getStringArray(R.array.homesickness_treatment);

            default:
                return null;
        }
    }
}
