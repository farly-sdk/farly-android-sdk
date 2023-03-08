package com.farly.farly.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

public class OfferWallRequest {
    @NonNull
    private String userId;
    @Nullable
    private String zipCode;
    @Nullable
    private String countryCode;
    @Nullable
    private Integer userAge;
    @Nullable
    private Gender userGender;
    @Nullable
    private Date userSignupDate;
    @Nullable
    private String[] callbackParameters;

    public OfferWallRequest(@NonNull String userId) {
        this.userId = userId;
    }

    public OfferWallRequest(@NonNull String userId, @Nullable String zipCode, @Nullable String countryCode, @Nullable Integer userAge, @Nullable Gender userGender, @Nullable Date userSignupDate, @Nullable String[] callbackParameters) {
        this.userId = userId;
        this.zipCode = zipCode;
        this.countryCode = countryCode;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userSignupDate = userSignupDate;
        this.callbackParameters = callbackParameters;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(@Nullable String zipCode) {
        this.zipCode = zipCode;
    }

    @Nullable
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(@Nullable String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(@Nullable Integer userAge) {
        this.userAge = userAge;
    }

    public Gender getUserGender() {
        return userGender;
    }

    public void setUserGender(@Nullable Gender userGender) {
        this.userGender = userGender;
    }

    public Date getUserSignupDate() {
        return userSignupDate;
    }

    public void setUserSignupDate(@Nullable Date userSignupDate) {
        this.userSignupDate = userSignupDate;
    }

    public String[] getCallbackParameters() {
        return callbackParameters;
    }

    public void setCallbackParameters(@Nullable String[] callbackParameters) {
        this.callbackParameters = callbackParameters;
    }
}
