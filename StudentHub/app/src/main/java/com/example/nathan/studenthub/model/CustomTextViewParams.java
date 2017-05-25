package com.example.nathan.studenthub.model;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Nathan on 24/05/2017.
 */

public class CustomTextViewParams
{
//    private final OnParamsReady callback;
    private final Context callback;

    public CustomTextViewParams(Context callback)
    {
        this.callback = callback;
    }

    private int GetMargin()
    {
        int margin = 16;
        float dimensions = callback.getResources().getDisplayMetrics().density;
        return (int) (margin * dimensions);
    }

    public ViewGroup.LayoutParams GetParams()
    {
        int margin = GetMargin();
        int offset = margin * 2;

        // SET LAYOUT PARAMS ON TEXTVIEW
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin, margin / 6, margin, margin - offset);
        return params;
    }
}
