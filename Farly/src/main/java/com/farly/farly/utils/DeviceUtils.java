package com.farly.farly.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class DeviceUtils {
    public static boolean isTablet(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager == null) {
            return false;
        }
        return manager.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE;
    }
}
