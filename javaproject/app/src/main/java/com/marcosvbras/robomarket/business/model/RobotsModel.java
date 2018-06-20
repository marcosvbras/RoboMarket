package com.marcosvbras.robomarket.business.model;

import com.marcosvbras.robomarket.business.api.APIService;
import com.marcosvbras.robomarket.business.domain.User;
import com.marcosvbras.robomarket.business.response.ListRobotResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RobotsModel {

    private String robotList = "{\"advertiser\": {\"__type\":\"Pointer\", \"className\":\"User\",\"objectId\":\"{objectId}\"}}";

    public Observable<ListRobotResponse> listRobots(User user) {
        return APIService.getService()
                .listRobots(robotList.replace("{objectId}", user.getObjectId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
