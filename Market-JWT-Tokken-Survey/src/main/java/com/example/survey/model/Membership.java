package com.example.survey.model;

public enum Membership {
    FREE("FREE_MEMBERSHIP"),
    STANDARD("STANDARD_MEMBERSHIP"),
    PREMIUM("PREMIUM_MEMBERSHIP");

    private String value;

    private Membership(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }
}