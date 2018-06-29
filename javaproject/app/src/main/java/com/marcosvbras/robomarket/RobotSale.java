package com.marcosvbras.robomarket;

import com.marcosvbras.robomarket.business.domain.Robot;

public class RobotSale {

    private Robot robot;
    private Integer itemQuantity;

    public RobotSale(Robot robot, Integer itemQuantity) {
        this.robot = robot;
        this.itemQuantity = itemQuantity;
    }

    public RobotSale() { }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
