package com.example.myapplication.networks.jsonmodel;

import com.google.gson.annotations.SerializedName;

public class LoginRespondJson {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("token")
    private String token;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public LoginRespondJson(int code, String message, String token) {
        this.code = code;
        this.message = message;
        this.token = token;
    }

    public String toString(){
        return "LoginRespondJson{"+
                "code="+code+'\''+
                ",message="+message+'\''+
                ",token="+token+'\'' +
                '}';
    }
}
