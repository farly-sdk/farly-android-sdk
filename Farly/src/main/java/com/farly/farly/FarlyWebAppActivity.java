package com.farly.farly;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.farly.farly.databinding.ActivityFarlyWebAppBinding;

public class FarlyWebAppActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "com.farly.farly.webview.URL";

    private ActivityFarlyWebAppBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFarlyWebAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_URL);

        binding.webView.loadUrl(url);
    }
}