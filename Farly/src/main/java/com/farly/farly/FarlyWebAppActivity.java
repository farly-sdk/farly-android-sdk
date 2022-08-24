package com.farly.farly;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;

import androidx.appcompat.app.AppCompatActivity;

import com.farly.farly.databinding.ActivityFarlyWebAppBinding;

public class FarlyWebAppActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "com.farly.farly.webview.URL";

    private ActivityFarlyWebAppBinding binding;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFarlyWebAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_URL);

        WebSettings webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        binding.webView.loadUrl(url);
    }
}