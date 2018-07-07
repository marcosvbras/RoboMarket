package com.marcosvbras.robomarket.business.api

import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()?.newBuilder()
                ?.header(Constants.Api.HEADER_APP_ID, Constants.Api.APP_ID)
                ?.header(Constants.Api.HEADER_REST_API_KEY, Constants.Api.REST_API_KEY)
                ?.method(chain.request().method(), chain.request()?.body())
                ?.build()

        val credentials = App.getInstance().userCredentials
        val sessionToken = credentials[Constants.Preferences.SESSION_TOKEN_KEY]

        if(sessionToken != null) {
            request = request?.newBuilder()
                    ?.header(Constants.Api.HEADER_SESSION_TOKEN, sessionToken)
                    ?.method(request.method(), request.body())
                    ?.build()
        }

        return chain.proceed(request)
    }
}