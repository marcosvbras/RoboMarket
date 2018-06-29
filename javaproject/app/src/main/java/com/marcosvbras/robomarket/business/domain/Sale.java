package com.marcosvbras.robomarket.business.domain;

import java.util.List;

public class Sale {

    private String objectId;
    private String createdAt;
    private String updatedAt;
    private String userId;
    private List<ItemSale> robots;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ItemSale> getRobots() {
        return robots;
    }

    public void setRobots(List<ItemSale> robots) {
        this.robots = robots;
    }
}
