package com.example.survey.model;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String value;

    private Role(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }
}