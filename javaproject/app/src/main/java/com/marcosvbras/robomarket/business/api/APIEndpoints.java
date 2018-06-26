package com.marcosvbras.robomarket.business.api;

import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.business.domain.User;
import com.marcosvbras.robomarket.business.response.ListRobotResponse;
import com.marcosvbras.robomarket.business.response.UpdateResponse;
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

    /*
    * Returns a Session Token within Header and createdAt, objectId and sessionToken within Body.
    * */
    @POST(Constants.Api.USERS_ROOT_ENDPOINT)
    Observable<User> signup(@Body User user);

    @PUT(Constants.Api.USER_ACTIONS_ENDPOINT)
    Observable<UpdateResponse> updateUser(@Body User user, @Path("objectId") String userId);

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

    // Robots Endpoints

    @GET(Constants.Api.ROBOTS_ROOT_ENDPOINT)
    Observable<ListRobotResponse> listRobots(@Query("where") String condition, @Query("order") String order,
                                             @Query("limit") int limit, @Query("skip") int skip);

    @GET(Constants.Api.ROBOT_ACTIONS_ENDPOINT)
    Observable<Robot> getRobot(@Path("objectId") String objectId);

    @PUT(Constants.Api.ROBOT_ACTIONS_ENDPOINT)
    Observable<Robot> updateRobot(@Path("objectId") String objectId, @Body Robot robot);

    @PUT(Constants.Api.ROBOT_ACTIONS_ENDPOINT)
    Observable<Robot> deleteRobot(@Path("objectId") String objectId);

    @POST(Constants.Api.ROBOTS_ROOT_ENDPOINT)
    Observable<Robot> createRobot(@Body Robot robot);

}
