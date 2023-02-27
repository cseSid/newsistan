package com.sid.newsistan.apiService;

import com.sid.newsistan.dataClass.News;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private static RetrofitClient instance = null;
    private NewsService myApi;


    OkHttpClient client = new OkHttpClient();

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsService.Companion.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
        myApi = retrofit.create(NewsService.class);
    }


    private OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .addInterceptor(new HeaderInterceptor());
        return builder.build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public NewsService getMyApi() {
        return myApi;
    }
}




