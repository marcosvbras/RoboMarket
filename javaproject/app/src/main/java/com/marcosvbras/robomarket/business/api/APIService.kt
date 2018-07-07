package com.marcosvbras.robomarket.business.api

import com.marcosvbras.robomarket.BuildConfig
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
                .baseUrl(BASE_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build()

        service = retrofit.create(APIEndpoints::class.java)
    }

    companion object {
        const val BASE_API_URL = "https://parseapi.back4app.com/"
        const val LOGIN_ENDPOINT = "login"
        const val LOGOUT_ENDPOINT = "logout"
        const val USER_ROOT_ENDPOINT = "users"
        const val USER_ACTIONS_ENDPOINT = "users/{objectId}"
        const val AUTHENTICATED_USER_ENDPOINT = "users/me"
        const val PASSWORD_RESET_ENDPOINT = "requestPasswordReset"
        const val ROBOT_ROOT_ENDPOINT = "classes/Robot"
        const val SALE_ROOT_ENDPOINT = "classes/Sale"
        const val ROBOT_ACTIONS_ENDPOINT = "classes/Robot/{objectId}"
        const val SALE_ACTIONS_ENDPOINT = "classes/Robot/{objectId}"
        const val HEADER_APP_ID = "X-Parse-Application-Id"
        const val HEADER_REST_API_KEY = "X-Parse-REST-API-Key"
        const val HEADER_SESSION_TOKEN = "X-Parse-Session-Token"
        const val APP_ID = "D8MjOxsJpDNPaLdBaxvRU6Afm1xMGF9TTY5TQArd"
        const val REST_API_KEY = "kv9fEiX8x7RlrxNOcvqKaLXYZg7qBlDh1lCzHVbY"
        const val DEFAULT_ITEM_PAGINATION = 10
        const val DEFAULT_ITEM_SKIP = 10
        const val DEFAULT_ROBOT_ORDER = "-updatedAt"

        private var service: APIEndpoints? = null

        fun getService(): APIEndpoints? {
            if(service == null)
                APIService()

            return service
        }
    }

}