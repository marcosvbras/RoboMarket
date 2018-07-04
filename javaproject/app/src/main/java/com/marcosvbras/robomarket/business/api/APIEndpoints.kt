package com.marcosvbras.robomarket.business.api

import com.marcosvbras.robomarket.business.domain.Robot
import com.marcosvbras.robomarket.business.domain.Sale
import com.marcosvbras.robomarket.business.domain.User
import com.marcosvbras.robomarket.business.response.ListRobotResponse
import com.marcosvbras.robomarket.business.response.ListSaleResponse
import com.marcosvbras.robomarket.business.response.UpdateResponse
import com.marcosvbras.robomarket.utils.Constants
import io.reactivex.Observable
import retrofit2.http.*

interface APIEndpoints {

    @GET(Constants.Api.LOGIN_ENDPOINT)
    fun login(@Query("username") username: String, @Query("password") password: String): Observable<User>

    /*
    * Returns a Session Token within Header and createdAt, objectId and sessionToken within Body.
    * */
    @POST(Constants.Api.USER_ROOT_ENDPOINT)
    fun signUp(@Body user: User): Observable<User>

    @PUT(Constants.Api.USER_ACTIONS_ENDPOINT)
    fun updateUser(@Body user: User, @Path("objectId") userId: String): Observable<UpdateResponse>

    @GET(Constants.Api.USER_ACTIONS_ENDPOINT)
    fun getUser(@Path("objectId") userId: String): Observable<User>

    /*
    * Returns an User of the current Session Token or 209 if the Session Token isn't valid.
    * */
    @GET(Constants.Api.AUTHENTICATED_USER_ENDPOINT)
    fun getUserByToken(): Observable<User>

    /*
    * Returns an empty JSON object if successful.
    * */
    @POST(Constants.Api.PASSWORD_RESET_ENDPOINT)
    fun resetPassword(@Body user: User): Observable<Void>

    /*
    * Requires logged User's Session Token.
    * */
    @DELETE(Constants.Api.USER_ACTIONS_ENDPOINT)
    fun deleteUser(@Path("objectId") userId: String): Observable<Void>

    // Robots Endpoints

    @GET(Constants.Api.ROBOT_ROOT_ENDPOINT)
    fun listRobots(@Query("where") condition: String, @Query("order") order: String,
                            @Query("limit") limit: Int, @Query("skip") skip: Int): Observable<ListRobotResponse>

    @GET(Constants.Api.ROBOT_ACTIONS_ENDPOINT)
    fun getRobot(@Path("objectId") objectId: String): Observable<Robot>

    @PUT(Constants.Api.ROBOT_ACTIONS_ENDPOINT)
    fun updateRobot(@Path("objectId") objectId: String, @Body robot: Robot): Observable<Robot>

    @DELETE(Constants.Api.ROBOT_ACTIONS_ENDPOINT)
    fun deleteRobot(@Path("objectId") objectId: String): Observable<Void>

    @POST(Constants.Api.ROBOT_ROOT_ENDPOINT)
    abstract fun createRobot(@Body robot: Robot): Observable<Robot>


    @GET(Constants.Api.SALE_ROOT_ENDPOINT)
    fun listSales(@Query("where") condition: String, @Query("order") order: String,
                           @Query("limit") limit: Int, @Query("skip") skip: Int): Observable<ListSaleResponse>

    @GET(Constants.Api.SALE_ACTIONS_ENDPOINT)
    fun getSale(@Path("objectId") objectId: String): Observable<Sale>

    @POST(Constants.Api.SALE_ROOT_ENDPOINT)
    fun createSale(@Body sale: Sale): Observable<Sale>
}