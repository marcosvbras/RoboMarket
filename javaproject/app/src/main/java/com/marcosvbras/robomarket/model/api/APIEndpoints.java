package com.marcosvbras.robomarket.model.api;

import com.marcosvbras.robomarket.model.domains.User;
import com.marcosvbras.robomarket.utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIEndpoints {

    @GET(Constants.Api.LOGIN_ENDPOINT)
    Observable<User> signin(@Query("username") String username, @Query("password") String password);

}
