package com.example.myapplication.networks;

import com.example.myapplication.networks.jsonmodel.LoginRespondJson;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginServices {
    @POST("login")
    Call<LoginRespondJson> login(@Body RequestBody body);
}
