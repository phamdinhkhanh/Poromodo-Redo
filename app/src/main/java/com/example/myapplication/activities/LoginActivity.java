package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.networks.LoginServices;
import com.example.myapplication.networks.jsonmodel.LoginBodyJson;
import com.example.myapplication.networks.jsonmodel.LoginRespondJson;
import com.example.myapplication.settings.SharedPrefs;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btRegister;
    private Button btLogin;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = this.findViewById(R.id.et_username);
        etPassword = this.findViewById(R.id.et_password);
        btLogin = this.findViewById(R.id.bt_login);
        btRegister = this.findViewById(R.id.bt_register);

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    attemptLogin();
                    return true;
                };
                return false;
            }
        });
    }

    private void attemptLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        sendLogin(username, password);
    }

    private void sendLogin(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Tao ra mot loginServices tu retrofit gom lenh POST, CallRespond
        LoginServices loginServices = retrofit.create(LoginServices.class);
        //data & format
        //format <> mediatype
        //data <> json
        //Tao kieu
        MediaType jsonType = MediaType.parse("application/json");
        //Chuyen LoginBodyJson ve dang String loginJson.
        String longinJson = new Gson().toJson(new LoginBodyJson(username, password));
        //Tao Json Respond request cau longinJson
        RequestBody loginBody = RequestBody.create(jsonType, longinJson);
        loginServices
                .login(loginBody)
                .enqueue(new Callback<LoginRespondJson>() {
                    @Override
                    public void onResponse(Call<LoginRespondJson> call, Response<LoginRespondJson> response) {
                        LoginRespondJson loginRespondJson = response.body();
                        if (loginRespondJson == null) {
                            Log.d(TAG, "onRespond: Couldn't parse body");
                        } else {
                            Log.d(TAG, String.format("onRespond, oh yeah: %s", loginRespondJson));
                            if (response.code() == 200) {
                                onLoginSuccess();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginRespondJson> call, Throwable t) {
                        Log.d(TAG, String.format("onFailure:%s", t));
                    }
                });

    }

    private void onLoginSuccess() {
        if (SharedPrefs.getInstance().getAccessToken() != null){
            gotoTaskActivity();
        }
    }

    private void skipLoginAsPossible() {
        if (SharedPrefs.getInstance().getAccessToken() != null) {
            gotoTaskActivity();
        }
    }

    private void gotoTaskActivity() {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }
}

