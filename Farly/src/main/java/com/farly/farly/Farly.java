package com.farly.farly;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.WorkerThread;

import com.farly.farly.jsonmodel.FeedItem;
import com.farly.farly.jsonmodel.JsonParser;
import com.farly.farly.model.OfferWallRequest;
import com.farly.farly.utils.AeSimpleSHA1;
import com.farly.farly.utils.DeviceUtils;
import com.farly.farly.utils.HTTPRequestTask;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class Farly {

    private final static Farly instance = new Farly();
    public static final String LOG_TAG = "Farly";

    private String apiKey;
    private String apiDomain = "www.farly.io";
    private String offerwallDomain = "offerwall.farly.io";
    private String publisherId;

    private Farly() {
    }

    public static Farly getInstance() {
        return instance;
    }

    private void checkConfig() throws Exception {
        if (TextUtils.isEmpty(apiKey)) {
            throw new Exception("Farly: missing apiKey");
        }
        if (TextUtils.isEmpty(apiDomain)) {
            throw new Exception("Farly: missing apiDomain");
        }
        if (TextUtils.isEmpty(publisherId)) {
            throw new Exception("Farly: missing publisherId");
        }
    }

    private enum Endpoint {
        API_FEED_V2("/api/feed/v2"), HOSTED_WALL("/offers");

        private final String endpoint;

        Endpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getEndpoint() {
            return endpoint;
        }
    }

    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, "utf-8");
    }

    private String getUrl(Context context, OfferWallRequest request, Endpoint endpoint) throws Exception {
        String url = "https://" + (endpoint == Endpoint.HOSTED_WALL ? this.offerwallDomain : this.apiDomain) + endpoint.getEndpoint();
        Map<String, String> queryParams = getQueryParams(context, request);

        StringBuilder urlStringBuilder = new StringBuilder(url);

        Set<Map.Entry<String, String>> entrySet = queryParams.entrySet();
        String prefix = "?";
        for (Map.Entry<String, String> entry : entrySet) {
            String value = entry.getValue();
            if (TextUtils.isEmpty(value)) {
                continue;
            }
            urlStringBuilder.append(prefix)
                    .append(entry.getKey())
                    .append("=")
                    .append(encodeValue(value));
            prefix = "&";
        }
        String finalUrl = urlStringBuilder.toString();

        Log.d(LOG_TAG, "Using offerwal url " + finalUrl);

        return finalUrl;
    }

    /**
     * Return the formatted OfferWall url.
     * Convenient if you want to display the offer wall in your own webview
     * Warning: do not call this from the main thread, it will crash. Use getHostedOfferWallUrlFromMainThread if needed
     *
     * @param context
     * @param request
     * @return
     * @throws Exception
     */
    @WorkerThread
    public String getHostedOfferWallUrl(Context context, OfferWallRequest request) throws Exception {
        return this.getUrl(context, request, Endpoint.HOSTED_WALL);
    }

    public interface OfferWallUrlCompletionHandler {
        /**
         * Called with url when request is done.
         *
         * @param url the url you can use in your own webview
         */
        void onComplete(String url);

        void onError(Exception e);
    }

    /**
     * Get the formatted OfferWall url in background, and call the completionHandler when done
     * Convenient if you want to display the offer wall in your own webview
     * Unlike getHostedOfferWallUrl, you can call this method from the main thread.
     *
     * @param context
     * @param request
     * @param handler
     */
    public void getHostedOfferWallUrlFromMainThread(Context context, OfferWallRequest request, OfferWallUrlCompletionHandler handler) {
        new Thread() {
            @Override
            public void run() {
                try {
                    String url = Farly.this.getUrl(context, request, Endpoint.HOSTED_WALL);
                    handler.onComplete(url);
                } catch (Exception e) {
                    handler.onError(e);
                }
            }
        }.start();
    }

    public enum OfferWallPresentationMode {
        WEB_VIEW, BROWSER;
    }

    /**
     * Show the OfferWall for you, either in the device browser or an embedded WebView
     *
     * @param context
     * @param request
     * @param presentationMode BROWSER or WEB_VIEW
     */
    public void showOfferWall(Context context, OfferWallRequest request, OfferWallPresentationMode presentationMode) {
        try {
            checkConfig();
        } catch (Exception e) {
            Log.e(LOG_TAG, "", e);
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    showOfferWall(context, request, presentationMode);
                }
            }.start();
            return;
        }

        try {
            String url = this.getHostedOfferWallUrl(context, request);
            switch (presentationMode) {
                case BROWSER:
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(browserIntent);
                    break;
                case WEB_VIEW:
                    Intent intent = new Intent(context, FarlyWebAppActivity.class);
                    intent.putExtra(FarlyWebAppActivity.EXTRA_URL, url);
                    context.startActivity(intent);
                    break;
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "", e);
        }
    }

    /**
     * Lets you fetch available offers programmatically. You will be responsible to display them in your own UI.
     *
     * @param context
     * @param request
     * @param completionHandler
     */
    public void getOfferWall(Context context, OfferWallRequest request, OfferWallRequestCompletionHandler completionHandler) {
        try {
            checkConfig();
        } catch (Exception e) {
            Log.e(LOG_TAG, "", e);
            completionHandler.onError(e);
            return;
        }

        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    getOfferWall(context, request, completionHandler);
                }
            }.start();
            return;
        }

        try {
            String url = this.getUrl(context, request, Endpoint.API_FEED_V2);
            new HTTPRequestTask(result -> {
                if (TextUtils.isEmpty(result)) {
                    completionHandler.onComplete(new ArrayList<>());
                    return;
                }
                try {
                    List<FeedItem> feed = JsonParser.parseFeedFromResponse(result);
                    completionHandler.onComplete(feed);
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Error parsing response: " + result, e);
                    completionHandler.onError(e);
                }

            }).execute(url);
        } catch (Exception e) {
            Log.e(LOG_TAG, "", e);
            completionHandler.onError(e);
        }
    }

    private Map<String, String> getQueryParams(Context context, OfferWallRequest request) throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("pubid", this.publisherId);

        int timestamp = Math.round(new Date().getTime() / 1000f);
        queryParams.put("timestamp", timestamp + "");
        queryParams.put("hash", AeSimpleSHA1.SHA1(timestamp + this.apiKey));

        queryParams.put("device", "android");
        queryParams.put("devicemodel", Build.MODEL);
        queryParams.put("os_version", Build.VERSION.RELEASE);
        queryParams.put("is_tablet", DeviceUtils.isTablet(context) ? "1" : "0");
        String language = Locale.getDefault().getLanguage();
        Locale[] availableLocales = Locale.getAvailableLocales();
        if (TextUtils.isEmpty(language)) {
            for (Locale availableLocale : availableLocales) {
                if (!TextUtils.isEmpty(language)) {
                    break;
                }
                language = availableLocale.getLanguage();
            }
        }
        queryParams.put("country", request.getCountryCode());
        queryParams.put("locale", language.startsWith("fr") ? "fr" : "en");

        queryParams.put("userid", request.getUserId());
        queryParams.put("zip", request.getZipCode());
        if (request.getUserGender() != null) {
            queryParams.put("user_gender", request.getUserGender().getRawValue());
        }
        if (request.getUserAge() != null) {
            queryParams.put("user_age", request.getUserAge() + "");
        }
        if (request.getUserSignupDate() != null) {
            queryParams.put("user_signup_timestamp", Math.round(request.getUserSignupDate().getTime() / 1000f) + "");
        }

        queryParams.put("from", "wallv2");

        String[] callbackParameters = request.getCallbackParameters();
        if (callbackParameters != null) {
            for (int i = 0; i < callbackParameters.length; i++) {
                queryParams.put("pub" + i, callbackParameters[i]);
            }
        }

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String plmn = telephonyManager.getSimOperator();
        if (!TextUtils.isEmpty(plmn)) {
            String mcc = plmn.substring(0, 3);
            String mnc = plmn.substring(3);
            queryParams.put("carrier", mcc + "-" + mnc);
        }

        AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
        if (!adInfo.isLimitAdTrackingEnabled()) {
            String gaId = adInfo.getId();
            if (gaId != null && !TextUtils.isEmpty(gaId)) {
                queryParams.put("gaid", gaId);
                queryParams.put("gaidsha1", AeSimpleSHA1.SHA1(gaId));
            }
        }

        return queryParams;
    }

    public interface OfferWallRequestCompletionHandler {
        /**
         * Called with offers when request is done. **Warning** this won't be on the main thread
         *
         * @param feed the offers available for this user
         */
        void onComplete(List<FeedItem> feed);

        void onError(Exception e);
    }

    // getters and setters

    @SuppressWarnings("UnusedReturnValue")
    public Farly setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Farly setPublisherId(String publisherId) {
        this.publisherId = publisherId;
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Farly setApiDomain(String apiDomain) {
        this.apiDomain = apiDomain;
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Farly setOfferwallDomain(String offerwallDomain) {
        this.offerwallDomain = offerwallDomain;
        return this;
    }
}
