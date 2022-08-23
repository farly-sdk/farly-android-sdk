package com.farly.farly.utils;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.Nullable;

import com.farly.farly.Farly;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class HTTPRequestTask extends AsyncTask<String, Integer, String> {

    private final HTTPRequestTaskCompletionHandler completionHandler;

    public HTTPRequestTask(HTTPRequestTaskCompletionHandler completionHandler) {
        this.completionHandler = completionHandler;
    }

    @Override
    protected String doInBackground(String... params) {
        String _url = params[0];
        try {
            URL url = new URL(_url);
            Log.d(Farly.LOG_TAG, "calling url : " + url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            try {
                httpURLConnection.setDoInput(true);

                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuilder textBuilder = new StringBuilder();
                try (Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")))) {
                    int c = 0;
                    while ((c = reader.read()) != -1) {
                        textBuilder.append((char) c);
                    }
                }

                // to log the response code of your request
                Log.d(Farly.LOG_TAG, "MyHttpRequestTask doInBackground : " + httpURLConnection.getResponseCode());
                // to log the response message from your server after you have tried the request.
                Log.d(Farly.LOG_TAG, "MyHttpRequestTask doInBackground : " + httpURLConnection.getResponseMessage());

                return textBuilder.toString();
            } catch (Exception e) {
                Log.e(Farly.LOG_TAG, "", e);
            } finally {
                // this is done so that there are no open connections left when this task is going to complete
                httpURLConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        this.completionHandler.onComplete(s);
    }

    public interface HTTPRequestTaskCompletionHandler {
        void onComplete(@Nullable String result);
    }
}