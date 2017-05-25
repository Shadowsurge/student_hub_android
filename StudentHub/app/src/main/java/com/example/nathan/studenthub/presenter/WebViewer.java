package com.example.nathan.studenthub.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.nathan.studenthub.R;

/**
 * Created by Nathan on 03/05/2017.
 */

public class WebViewer extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);
        setContentView(webView);

        Intent intent = getIntent();
        webView.loadUrl(intent.getStringExtra("WEBPAGE"));
    }
}
