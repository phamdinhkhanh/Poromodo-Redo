package com.example.myapplication.networks.jsonmodel;

import com.google.gson.annotations.SerializedName;

public class LoginBodyJson {
    @SerializedName("password")
    private String password;
    @SerializedName("username")
    private String username;

    public LoginBodyJson(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return "LoginBodyJson{"+
                "username='"+username+'\''+
                ",password='"+password+'\''+
                '}';
    }
}
