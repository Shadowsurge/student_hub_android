package com.example.nathan.studenthub.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.MenuItem;

/**
 * Created by Nathan on 02/05/2017.
 *
 * This class is responsible for displaying the About Fragment in the University Services section of the app.
 * It uses various methods from its parent class to track the data requested and load it into the view
 */

public class AboutFragment extends UniversityServicesFragment
{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.data = GetData((MenuItem) getArguments().get(ContentActivity.MENU_RESOURCE), FragmentPage.ABOUT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.page_one, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.aboutImageView);

        SetImage(imageView, (MenuItem) getArguments().get(ContentActivity.MENU_RESOURCE));
        CreateTextViews(new TextView[this.data.length],(LinearLayout) view.findViewById(R.id.about_layout));
        return view;
    }

    private void SetImage(ImageView image, MenuItem service)
    {
        switch(service.getTitle())
        {
            case "Chaplaincy":
                image.setImageResource(R.drawable.chaplaincy_image);
                break;

            case "Counselling":
                image.setImageResource(R.drawable.counselling_image);
                break;

            case "Disability":
                image.setImageResource(R.drawable.disability_image);
                break;

            case "Careers":
                image.setImageResource(R.drawable.castle_image);
                break;

            case "Castle":
                image.setImageResource(R.drawable.castle_image);
                break;

            case "Enquiry":
                image.setImageResource(R.drawable.enquiry_image);
                break;

            case "Sports":
                image.setImageResource(R.drawable.sports_image);
                break;
        }
    }
}
