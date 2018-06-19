package com.marcosvbras.robomarket.business.api;

import com.marcosvbras.robomarket.BuildConfig;
import com.marcosvbras.robomarket.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {

    private static APIEndpoints service;
    private Retrofit retrofit;

    public static APIEndpoints getService() {
        if(service == null)
            new APIService();

        return service;
    }

    private APIService() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor());

        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logInterceptor);
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.BASE_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        service = retrofit.create(APIEndpoints.class);
    }
}
