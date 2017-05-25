package com.example.nathan.studenthub.presenter;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.MenuItem;

/**
 * Created by Nathan on 08/05/2017.
 */

public abstract class UniversityServicesFragment extends Fragment
{
    String[] data;

    protected int GetMargin()
    {
        int margin = 16;
        float dimensions = getContext().getResources().getDisplayMetrics().density;
        return (int) (margin * dimensions);
    }

    protected ViewGroup.LayoutParams GetParams()
    {
        int margin = GetMargin();
        int offset = margin * 2;

        // SET LAYOUT PARAMS ON TEXTVIEW
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin, margin / 6, margin, margin - offset);
        return params;
    }

    protected void CreateTextViews(final TextView[] textViews, LinearLayout layout)
    {
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

            // Determine if the current data item is a link.
            // If yes, set it to center, set a color and activate clickable links
            if(data[i].contains("<a href"))
            {
                textViews[i].setGravity(Gravity.CENTER);
                textViews[i].setLinkTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                textViews[i].setMovementMethod(LinkMovementMethod.getInstance());
            }

            if(data[i].contains("id=phoneTag"))
            {
                final int j = i;
                textViews[i].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel: " + data[j].substring(data[j].indexOf(">") + 1, data[j].lastIndexOf("<"))));
                        startActivity(intent);
                    }
                });
            }

            // Check if current data item is an email
            // If yes, set it to center, set color and set an intent listener
            if(data[i].contains("id=emailTag"))
            {
                final int j = i;
                textViews[i].setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                textViews[i].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        String email = data[j].substring(data[j].indexOf(">") + 1, data[j].lastIndexOf("<"));
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", email, null));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                        intent.putExtra(Intent.EXTRA_TEXT, "Enter your message");
                        startActivity(Intent.createChooser(intent, "Send email..."));
                    }
                });
            }

            // Add layout params to the textview and add it to the view
            textViews[i].setLayoutParams(GetParams());
            layout.addView(textViews[i]);
        }
    }

    protected String[] GetData(MenuItem service, FragmentPage page)
    {
        switch(page)
        {
            case ABOUT:
                return loadAboutData(service);

            case SERVICES:
                return loadServicesData(service);

            case CONTACT:
                return loadContactData(service);

            default:
                return null;
        }
    }

    private String[] loadAboutData(MenuItem service)
    {
        switch(service.getTitle())
        {
            case "Chaplaincy":
                return getResources().getStringArray(R.array.chaplaincy_about);

            case "Counselling":
                return getResources().getStringArray(R.array.counselling_about);

            case "Disability":
                return getResources().getStringArray(R.array.disability_about);

            case "Careers":
                return getResources().getStringArray(R.array.careers_about);

            case "Castle":
                return getResources().getStringArray(R.array.castle_about);

            case "Enquiry":
                return getResources().getStringArray(R.array.enquiry_about);

            case "Sports":
                return getResources().getStringArray(R.array.sport_about);

            case "Stay On Course":
                return getResources().getStringArray(R.array.stay_on_course_about);

            case "Student Funding":
                return getResources().getStringArray(R.array.student_funding_about);

            case "Nightline":
                return getResources().getStringArray(R.array.nightline_about);

            default:
                return null;
        }
    }

    private String[] loadServicesData(MenuItem service)
    {
        switch(service.getTitle())
        {
            case "Chaplaincy":
                return getResources().getStringArray(R.array.chaplaincy_services);

            case "Counselling":
                return getResources().getStringArray(R.array.counselling_services);

            case "Disability":
                return getResources().getStringArray(R.array.disability_services);

            case "Careers":
                return getResources().getStringArray(R.array.careers_services);

            case "Castle":
                return getResources().getStringArray(R.array.castle_services);

            case "Enquiry":
                return getResources().getStringArray(R.array.enquiry_services);

            case "Sports":
                return getResources().getStringArray(R.array.sport_services);

            case "Stay On Course":
                return getResources().getStringArray(R.array.stay_on_course_services);

            case "Student Funding":
                return getResources().getStringArray(R.array.student_funding_services);

            case "Nightline":
                return getResources().getStringArray(R.array.nightline_services);

            default:
                return null;
        }
    }

    private String[] loadContactData(MenuItem service)
    {
        switch(service.getTitle())
        {
            case "Chaplaincy":
                return getResources().getStringArray(R.array.chaplaincy_contact);

            case "Counselling":
                return getResources().getStringArray(R.array.counselling_contact);

            case "Disability":
                return getResources().getStringArray(R.array.disability_contact);

            case "Careers":
                return getResources().getStringArray(R.array.careers_contact);

            case "Castle":
                return getResources().getStringArray(R.array.castle_contact);

            case "Enquiry":
                return getResources().getStringArray(R.array.enquiry_contact);

            case "Sports":
                return getResources().getStringArray(R.array.sport_contact);

            case "Stay On Course":
                return getResources().getStringArray(R.array.stay_on_course_contact);

            case "Student Funding":
                return getResources().getStringArray(R.array.student_funding_contact);

            case "Nightline":
                return getResources().getStringArray(R.array.nightline_contact);

            default:
                return null;
        }
    }
}
