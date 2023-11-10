package com.example.harwaqt;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class TermAndConditionsActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_and_conditions);
        WebView webView = findViewById(R.id.termAndConditionWebView);

        // Enable JavaScript for the WebView if your Term & Conditions page requires it
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Load the Term & Conditions URL
        webView.loadUrl("https://www.freeprivacypolicy.com/live/d0584f12-7390-426c-9a88-151a9876adaa");

        // Set a WebViewClient to handle page navigation within the WebView
        webView.setWebViewClient(new WebViewClient());
    }
}