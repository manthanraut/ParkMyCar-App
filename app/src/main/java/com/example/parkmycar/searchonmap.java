package com.example.parkmycar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class searchonmap extends AppCompatActivity {
    private WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchonmap);
        wv1=(WebView)findViewById(R.id.webView);
        WebSettings settings = wv1.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setDomStorageEnabled(true);
        wv1.setWebViewClient(new WebViewClient());
        wv1.loadUrl("https://searchlocation.herokuapp.com/");
    }

}