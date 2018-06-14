package com.marcosvbras.robomarket.model.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

//        if(accessToken != null && client != null && uid != null) {
//            Request modifiedRequest = request.newBuilder()
//                    .header("Content-Type", "application/json")
//                    .header(Constants.ACCESS_TOKEN_KEY, accessToken)
//                    .header(Constants.CLIENT_KEY, client)
//                    .header(Constants.UID_KEY, uid)
//                    .method(request.method(), request.body())
//                    .build();
//            request = modifiedRequest;
//        }

        Response response = chain.proceed(request);

//        if(response.code() == Constants.OK && response.headers() != null) {
//            uid = response.headers().get(Constants.UID_KEY);
//            accessToken = response.headers().get(Constants.ACCESS_TOKEN_KEY);
//            client = response.headers().get(Constants.CLIENT_KEY);
//            EnterpriseApp.getInstance().writeCredentials(accessToken, client, uid);
//        }

        return response;
    }
}
