package com.marcosvbras.robomarket.business.domain;

public class ItemSale {

    private int quantity;
    private int unitPrice;
    private String robotId;

    public ItemSale() {}

    public ItemSale(int quantity, int unitPrice, String robotId) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.robotId = robotId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }
}
