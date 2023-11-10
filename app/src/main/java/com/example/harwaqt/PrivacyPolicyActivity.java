package com.example.harwaqt;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        WebView webView = findViewById(R.id.privacyPolicyWebView);

        // Enable JavaScript for the WebView if your privacy policy page requires it
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Load the privacy policy URL
        webView.loadUrl("https://www.freeprivacypolicy.com/live/41241b5d-841b-4e0b-819d-feac482a1a4a");

        // Set a WebViewClient to handle page navigation within the WebView
        webView.setWebViewClient(new WebViewClient());
    }

}