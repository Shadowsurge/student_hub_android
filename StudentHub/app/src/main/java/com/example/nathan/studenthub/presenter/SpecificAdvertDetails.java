package com.example.nathan.studenthub.presenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.Advert;
import com.example.nathan.studenthub.model.Config;

public class SpecificAdvertDetails extends NavDrawer
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.LoadCurrentView(R.layout.activity_specific_advert_details);
        super.SetupNav();

        setAdvertDetails();
    }

    private void setAdvertDetails()
    {
        Intent intent = getIntent();
        final Advert advert = (Advert) intent.getSerializableExtra(Config.ADVERT_TAG);

        if(advert != null)
        {
            TextView title = (TextView) findViewById(R.id.advert_title);
            title.setText(advert.getTitle());

            TextView content = (TextView) findViewById(R.id.advert_content);
            content.setText(advert.getContent());

            TextView datePosted = (TextView) findViewById(R.id.advert_date);
            datePosted.setText(getString(R.string.advert_date_posted) + advert.getDatePosted().substring(0, 10));

            TextView contact = (TextView) findViewById(R.id.advertContactDetails);
            contact.setText("Contact " + advert.getUsername() + " via:");

            Button emailButton = (Button) findViewById(R.id.advertEmailButton);
            emailButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", advert.getEmail(), null));
                    intent.putExtra(Intent.EXTRA_SUBJECT, advert.getTitle());
                    intent.putExtra(Intent.EXTRA_TEXT, "Hi " + advert.getUsername() + ",\n\n");
                    startActivity(Intent.createChooser(intent, "Send email..."));
                }
            });

            Button callButton = (Button) findViewById(R.id.advertCallButton);
            Button smsButton = (Button) findViewById(R.id.advertSMSButton);

            if (!advert.getPhone().equals("No phone number supplied"))
            {
                callButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel: " + advert.getPhone()));
                        startActivity(intent);
                    }
                });

                smsButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", advert.getPhone(), null));
                        startActivity(intent);
                    }
                });
            }
            else
            {
                callButton.setVisibility(View.INVISIBLE);
                smsButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
