package com.example.nathan.studenthub.model;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nathan.studenthub.R;

/**
 * Created by Nathan on 24/05/2017.
 */

public class CustomAlertDialog
{
    private final Context callback;

    public CustomAlertDialog(Context callback)
    {
        this.callback = callback;
    }

    public void showDialog(String[] msg)
    {
        LayoutInflater inflater = (LayoutInflater) callback.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.content_home_help_popup, null);

        AlertDialog alertDialog = new AlertDialog.Builder(callback, android.R.style.Theme_Material_NoActionBar_Fullscreen)
                .create();

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.helpLayout);

        CustomTextViewParams customTextViewParams = new CustomTextViewParams(callback);

        for(String item : msg)
        {
            TextView textview = new TextView(callback);
            textview.setTextSize(14);

            if(item.contains("id=helpHeader"))
            {
                textview.setTextColor(callback.getResources().getColor(R.color.colorAccent));
            }
            else
            {
                textview.setTextColor(Color.WHITE);
            }

            // Determine what API level user has. Parse html accordingly
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            {
                textview.setText(Html.fromHtml(item,Html.FROM_HTML_MODE_LEGACY));
            }
            else
            {
                textview.setText(Html.fromHtml(item));
            }
            textview.setLayoutParams(customTextViewParams.GetParams());
            layout.addView(textview);
        }

        alertDialog.setView(view);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,
                                        int which)
                    {
                        dialog.dismiss();
                    }
                });

        TextView title = CreateTitleTextView();
        alertDialog.setCustomTitle(title);
        alertDialog.show();
    }

    private TextView CreateTitleTextView()
    {
        TextView title = new TextView(callback);
        title.setText("Help Section");
        title.setBackgroundColor(callback.getResources().getColor(R.color.materialGray));
        title.setGravity(Gravity.CENTER);
        title.setTextSize(26);
        title.setPadding(20,20,20,60);
        title.setTextColor(callback.getResources().getColor(R.color.colorAccent));

        return title;
    }
}
