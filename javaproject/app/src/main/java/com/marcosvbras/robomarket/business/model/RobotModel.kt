package com.marcosvbras.robomarket.business.model

import android.text.TextUtils
import com.marcosvbras.robomarket.business.api.APIService
import com.marcosvbras.robomarket.business.domain.Robot
import com.marcosvbras.robomarket.business.domain.User
import com.marcosvbras.robomarket.business.response.ListRobotResponse
import com.marcosvbras.robomarket.utils.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RobotModel {

    private val robotList = "{\"userId\": \"{userId}\"}"
    private val robotFilter = "{\"model\":{\"\$regex\":\"^{myQuery}\", \"\$options\": \"i\"}, \"userId\": \"{userId}\"}"

    fun listRobots(userId: String, query: String?, skip: Int): Observable<ListRobotResponse> {

        val whereParameter = if (TextUtils.isEmpty(query))
            robotList.replace("{userId}", userId)
        else
            robotFilter.replace("{userId}", userId).replace("{myQuery}", query!!)

        return APIService.getService()
                .listRobots(
                        whereParameter,
                        Constants.Api.DEFAULT_ROBOT_ORDER,
                        Constants.Api.DEFAULT_ITEM_PAGINATION,
                        skip
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun createRobot(robot: Robot): Observable<Robot> {
        return APIService.getService().createRobot(robot)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateRobot(objectId: String, robot: Robot): Observable<Robot> {
        return APIService.getService().updateRobot(objectId, robot)
                .flatMap { APIService.getService().getRobot(objectId) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteRobot(objectId: String): Observable<Void> {
        return APIService.getService().deleteRobot(objectId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}