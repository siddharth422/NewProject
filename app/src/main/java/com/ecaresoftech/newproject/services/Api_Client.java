package com.ecaresoftech.newproject.services;

import android.content.Context;
import android.content.SharedPreferences;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api_Client {
    public static Retrofit retrofit;
    public static Retrofit getClient( Context context){
        String BASE_URL = "http://demo3.appecare.com";
//        File httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
//        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .cache(cache)
//                .build();
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
               //     .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;

    }
}
