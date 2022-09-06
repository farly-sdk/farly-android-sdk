package com.farly.farly.jsonmodel;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.farly.farly.Farly;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static List<FeedItem> parseFeedFromResponse(String response) throws JSONException {
        JSONArray jObject = new JSONArray(response);
        List<FeedItem> items = new ArrayList<>();
        int length = jObject.length();
        for (int i = 0; i < length; i++) {
            JSONObject object = jObject.getJSONObject(i);
            FeedItem feedItem = parseFeedItem(object);
            if (feedItem == null) {
                continue;
            }
            items.add(feedItem);
        }

        return items;
    }

    @Nullable
    private static String getOptionalString(JSONObject jsonObject, String key) {
        try {
            String value = jsonObject.getString(key);
            if (TextUtils.isEmpty(value)) {
                return null;
            }
            return value;
        } catch (JSONException e) {
            return null;
        }
    }

    @Nullable
    private static Double getOptionalDouble(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getDouble(key);
        } catch (JSONException e) {
            return null;
        }
    }

    private static Action parseAction(JSONObject jsonObject) {
        if (!jsonObject.has("id")) {
            return null;
        }
        return new ActionBuilder()
                .setId(getOptionalString(jsonObject, "id"))
                .setAmount(getOptionalDouble(jsonObject, "amount"))
                .setText(getOptionalString(jsonObject, "text"))
                .setHtml(getOptionalString(jsonObject, "html"))
                .createAction();
    }

    private static FeedItem parseFeedItem(JSONObject jsonObject) {
        if (!jsonObject.has("id")) {
            return null;
        }

        List<Action> actions = new ArrayList<>();
        try {
            JSONArray actionJsonArray = jsonObject.getJSONArray("actions");
            int length = actionJsonArray.length();
            for (int i = 0; i < length; i++) {
                Action action = parseAction(actionJsonArray.getJSONObject(i));
                if (action != null) {
                    actions.add(action);
                }
            }
        } catch (JSONException e) {
            Log.e(Farly.LOG_TAG, "", e);
        }

        return new FeedItemBuilder()
                .setId(getOptionalString(jsonObject, "id"))
                .setName(getOptionalString(jsonObject, "name"))
                .setDevName(getOptionalString(jsonObject, "devname"))
                .setLink(getOptionalString(jsonObject, "link"))
                .setIcon(getOptionalString(jsonObject, "icone"))
                .setSmallDescription(getOptionalString(jsonObject, "small_description"))
                .setSmallDescriptionHTML(getOptionalString(jsonObject, "small_description_html"))
                .setActions(actions)
                .createFeedItem();
    }
}
