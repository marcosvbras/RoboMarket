package com.marcosvbras.robomarket.business.api

import com.marcosvbras.robomarket.business.beans.*
import io.reactivex.Observable
import retrofit2.http.*

interface APIEndpoints {

    @GET(APIService.LOGIN_ENDPOINT)
    fun login(@Query("username") username: String, @Query("password") password: String): Observable<User>

    /*
    * Returns a Session Token within Header and createdAt, objectId and sessionToken within Body.
    * */
    @POST(APIService.USER_ROOT_ENDPOINT)
    fun signUp(@Body user: User): Observable<User>

    @PUT(APIService.USER_ACTIONS_ENDPOINT)
    fun updateUser(@Body user: User, @Path("objectId") userId: String): Observable<UpdateResponse>

    @GET(APIService.USER_ACTIONS_ENDPOINT)
    fun getUser(@Path("objectId") userId: String): Observable<User>

    /*
    * Returns an User of the current Session Token or 209 if the Session Token isn't valid.
    * */
    @GET(APIService.AUTHENTICATED_USER_ENDPOINT)
    fun getUserByToken(): Observable<User>

    /*
    * Returns an empty JSON object if successful.
    * */
    @POST(APIService.PASSWORD_RESET_ENDPOINT)
    fun resetPassword(@Body user: User): Observable<Void>

    /*
    * Requires logged User's Session Token.
    * */
    @DELETE(APIService.USER_ACTIONS_ENDPOINT)
    fun deleteUser(@Path("objectId") userId: String): Observable<Void>

    // Robots Endpoints

    @GET(APIService.ROBOT_ROOT_ENDPOINT)
    fun listRobots(@Query("where") condition: String, @Query("order") order: String,
                            @Query("limit") limit: Int, @Query("skip") skip: Int): Observable<ListResponseOf<Robot>>

    @GET(APIService.ROBOT_ACTIONS_ENDPOINT)
    fun getRobot(@Path("objectId") objectId: String): Observable<Robot>

    @PUT(APIService.ROBOT_ACTIONS_ENDPOINT)
    fun updateRobot(@Path("objectId") objectId: String, @Body robot: Robot): Observable<Robot>

    @DELETE(APIService.ROBOT_ACTIONS_ENDPOINT)
    fun deleteRobot(@Path("objectId") objectId: String): Observable<Void>

    @POST(APIService.ROBOT_ROOT_ENDPOINT)
    fun createRobot(@Body robot: Robot): Observable<Robot>


    @GET(APIService.SALE_ROOT_ENDPOINT)
    fun listSales(@Query("where") condition: String, @Query("order") order: String,
                           @Query("limit") limit: Int, @Query("skip") skip: Int): Observable<ListResponseOf<Sale>>

    @GET(APIService.SALE_ACTIONS_ENDPOINT)
    fun getSale(@Path("objectId") objectId: String): Observable<Sale>

    @POST(APIService.SALE_ROOT_ENDPOINT)
    fun createSale(@Body sale: Sale): Observable<Sale>
}