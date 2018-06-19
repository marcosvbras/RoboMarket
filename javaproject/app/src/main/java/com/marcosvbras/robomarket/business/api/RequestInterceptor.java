package com.marcosvbras.robomarket.business.api;

import com.marcosvbras.robomarket.app.RoboApplication;
import com.marcosvbras.robomarket.utils.Constants;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        // Setting Back4App Credentials
        Request request = chain.request().newBuilder()
                .header(Constants.Api.HEADER_APP_ID, Constants.Api.APP_ID)
                .header(Constants.Api.HEADER_REST_API_KEY, Constants.Api.REST_API_KEY)
                .method(chain.request().method(), chain.request().body())
                .build();

        HashMap<String, String> credentials = RoboApplication.getInstance().getUserCredentials();
        String sessionToken = credentials.get(Constants.Preferences.SESSION_TOKEN_KEY);

        // Setting user session token if it exists
        if(sessionToken != null) {
            request = request.newBuilder()
                    .header(Constants.Api.HEADER_SESSION_TOKEN, sessionToken)
                    .method(request.method(), request.body())
                    .build();
        }

        return chain.proceed(request);
    }
}
