package com.marcosvbras.robomarket.business.model;

import android.text.TextUtils;

import com.marcosvbras.robomarket.business.api.APIService;
import com.marcosvbras.robomarket.business.domain.Robot;
import com.marcosvbras.robomarket.business.domain.User;
import com.marcosvbras.robomarket.business.response.ListRobotResponse;
import com.marcosvbras.robomarket.utils.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RobotModel {

    private String robotList = "{\"userId\": \"{userId}\"}";
    private String robotFilter = "{\"model\":{\"$regex\":\"^{myQuery}\", \"$options\": \"i\"}, \"userId\": \"{userId}\"}";

    public Observable<ListRobotResponse> listRobots(User user, String query, int skip) {
        String whereParameter;

        if(TextUtils.isEmpty(query))
            whereParameter = robotList.replace("{userId}", user.getObjectId());
        else
            whereParameter = robotFilter.replace("{userId}", user.getObjectId())
                .replace("{myQuery}", query);

        return APIService.getService()
                .listRobots(
                        whereParameter,
                        Constants.Api.DEFAULT_ROBOT_ORDER,
                        Constants.Api.DEFAULT_ROBOT_PAGINATION,
                        skip
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Robot> createRobot(Robot robot) {
        return APIService.getService().createRobot(robot)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Robot> updateRobot(String objectId, Robot robot) {
        return APIService.getService().updateRobot(objectId, robot)
                .flatMap(robotResponse -> APIService.getService().getRobot(objectId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Void> deleteRobot(String objectId) {
        return APIService.getService().deleteRobot(objectId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
