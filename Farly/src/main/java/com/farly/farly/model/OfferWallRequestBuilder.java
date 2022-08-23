package com.farly.farly.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

public class OfferWallRequestBuilder {
    @NonNull
    private String userId;
    @Nullable
    private String zipCode;
    @Nullable
    private Integer userAge;
    @Nullable
    private Gender userGender;
    @Nullable
    private Date userSignupDate;
    @Nullable
    private String[] callbackParameters;

    public OfferWallRequestBuilder setUserId(@NonNull String userId) {
        this.userId = userId;
        return this;
    }

    public OfferWallRequestBuilder setZipCode(@Nullable String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public OfferWallRequestBuilder setUserAge(@Nullable Integer userAge) {
        this.userAge = userAge;
        return this;
    }

    public OfferWallRequestBuilder setUserGender(@Nullable Gender userGender) {
        this.userGender = userGender;
        return this;
    }

    public OfferWallRequestBuilder setUserSignupDate(@Nullable Date userSignupDate) {
        this.userSignupDate = userSignupDate;
        return this;
    }

    public OfferWallRequestBuilder setCallbackParameters(@Nullable String[] callbackParameters) {
        this.callbackParameters = callbackParameters;
        return this;
    }

    public OfferWallRequest build() {
        return new OfferWallRequest(userId, zipCode, userAge, userGender, userSignupDate, callbackParameters);
    }
}