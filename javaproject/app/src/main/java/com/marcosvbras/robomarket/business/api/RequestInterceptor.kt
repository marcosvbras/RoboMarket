package com.marcosvbras.robomarket.business.api

import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.app.SESSION_TOKEN_PREF
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()?.newBuilder()
                ?.header(APIService.HEADER_APP_ID, APIService.APP_ID)
                ?.header(APIService.HEADER_REST_API_KEY, APIService.REST_API_KEY)
                ?.method(chain.request().method(), chain.request()?.body())
                ?.build()

        val credentials = App.instance.userCredentials
        val sessionToken = credentials[SESSION_TOKEN_PREF]

        if(sessionToken != null) {
            request = request?.newBuilder()
                    ?.header(APIService.HEADER_SESSION_TOKEN, sessionToken)
                    ?.method(request.method(), request.body())
                    ?.build()
        }

        return chain.proceed(request)
    }
}