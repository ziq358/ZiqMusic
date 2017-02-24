package com.xogrp.john.ziqtoollibrary.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xogrp.john.ziqtoollibrary.common.NetworkCommon;
import com.xogrp.john.ziqtoollibrary.model.JohTtest;
import com.xogrp.john.ziqtoollibrary.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by john on 06/02/2017.
 */

public class OkHttpNetWorkUtil {

    private static final String TAG = "ziq";

    static User user;
    static JohTtest johTtest;
    public static void login(String email, String password){
        JSONObject loginJsonObject = new JSONObject();
        JSONObject loginSessionsObject = new JSONObject();
        try {
            loginSessionsObject.put("email", email);
            loginSessionsObject.put("password", password);
            loginJsonObject.put("sessions", loginSessionsObject);
        } catch (JSONException e) {
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        RequestBody requestBody = RequestBody.create(null,loginJsonObject.toString());
        Request request = new Request.Builder()
                .url(NetworkCommon.login_url)
                .addHeader("User-Agent","XOXO/PlannerApp")
                .addHeader("Content-Type","application/json")
                .addHeader("Accept", "application/json")
                .addHeader("X-Api-Version","v1")
                .post(requestBody)
                .build();
//        try {
//            //同步
//            Response response = okHttpClient.newCall(request).execute();
//            if(response.isSuccessful()){
//                Log.e(TAG, "login: successful!");
//                Log.e(TAG, "response: "+response.body().string());
//                Gson gson = new GsonBuilder().create();
//                user = gson.fromJson(response.body().string(), User.class);
//            }else{
//                Log.e(TAG, "login: failed!");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //异步
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "login: failed!");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "login: successful!");
//                Log.e(TAG, "response: "+response.body().string());// response.body().string()只读一次
                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                johTtest = gson.fromJson("{\"name\":\"怪盗kidou\",\"age\":24,\"emailAddress\":\"ikidou@example.com\"}", JohTtest.class);
                user = gson.fromJson(response.body().string(), User.class);
            }
        });
        Log.e(TAG, "login: finished");
    }
}
