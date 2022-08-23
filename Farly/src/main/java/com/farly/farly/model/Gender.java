package com.farly.farly.model;

public enum Gender {
    MALE("m"), FEMALE("f");

    private final String rawValue;

    Gender(String rawValue) {
        this.rawValue = rawValue;
    }

    public String getRawValue() {
        return rawValue;
    }
}
