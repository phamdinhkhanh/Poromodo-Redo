package com.example.myapplication.settings;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPrefs {
    private static SharedPrefs instance;
    private String accessToken;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedPrefs(SharedPreferences sharedPreferences, Gson gson, String accessToken) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
        this.accessToken = accessToken;
    }

    public static SharedPrefs getInstance(){
        return instance;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
