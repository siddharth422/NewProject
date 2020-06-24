package com.ecaresoftech.newproject.interfaces;

import com.ecaresoftech.newproject.poja.LoginResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Response_Api {



    @POST("/web/user/login")
    Call<LoginResponse> Login(@Query("_format") String value,
                              @HeaderMap HashMap<String, String> hashMap,
                              @Body JsonObject jsonObject);
    @POST
    Call<ResponseBody> Logout(@Url String url,
                              @HeaderMap Map<String, String> map);


}