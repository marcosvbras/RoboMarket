package com.marcosvbras.robomarket.business.api

import com.marcosvbras.robomarket.BuildConfig
import com.marcosvbras.robomarket.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIService {

    private val retrofit: Retrofit

    init {
        val builder = OkHttpClient.Builder()
                .addInterceptor(RequestInterceptor())

        if(BuildConfig.DEBUG) {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logInterceptor)
        }

        retrofit = Retrofit.Builder()
                .baseUrl(Constants.Api.BASE_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build()

        service = retrofit.create(APIEndpoints::class.java)
    }

    companion object {
        private var service: APIEndpoints? = null

        fun getService(): APIEndpoints? {
            if(service == null)
                APIService()

            return service
        }
    }

}