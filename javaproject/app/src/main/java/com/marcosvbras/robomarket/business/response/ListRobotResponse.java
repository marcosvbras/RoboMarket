package com.marcosvbras.robomarket.business.response;

import com.google.gson.annotations.SerializedName;
import com.marcosvbras.robomarket.business.domain.Robot;

import java.util.List;

public class ListRobotResponse {

    @SerializedName("results")
    private List<Robot> listRobots;

    public List<Robot> getListRobots() {
        return listRobots;
    }

    public void setListRobots(List<Robot> listRobots) {
        this.listRobots = listRobots;
    }
}
