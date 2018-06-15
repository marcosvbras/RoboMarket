package com.marcosvbras.robomarket.model.api;

import com.marcosvbras.robomarket.model.domains.User;
import com.marcosvbras.robomarket.utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIEndpoints {

    // User Endpoints

    @GET(Constants.Api.LOGIN_ENDPOINT)
    Observable<User> login(@Query("username") String username, @Query("password") String password);

    @POST(Constants.Api.USER_CREATION_ENDPOINT)
    Observable<User> signup(@Body User user);

    @PUT(Constants.Api.USER_ACTIONS_ENDPOINT)
    Observable<User> updateUser(@Body User user);

    @GET(Constants.Api.USER_ACTIONS_ENDPOINT)
    Observable<User> getUser(@Path("objectId") String userId);

    /*
    * Returns an User of the current Session Token or 209 if the Session Token isn't valid.
    * */
    @GET(Constants.Api.AUTHENTICATED_USER_ENDPOINT)
    Observable<User> getUserByToken();

    /*
    * Returns an empty JSON object if successful.
    * */
    @POST(Constants.Api.PASSWORD_RESET_ENDPOINT)
    Observable<Void> resetPassword(@Body User user);

    /*
    * Requires logged User's Session Token.
    * */
    @DELETE(Constants.Api.USER_ACTIONS_ENDPOINT)
    Observable<Void> deleteUser(@Path("objectId") String userId);
}