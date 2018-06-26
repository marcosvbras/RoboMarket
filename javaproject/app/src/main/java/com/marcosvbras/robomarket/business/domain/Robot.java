package com.marcosvbras.robomarket.business.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Robot implements Parcelable {

    private String objectId;
    private String updatedAt;
    private String createdAt;
    private String model;
    private String color;
    private int year;
    private int price;
    private String manufacturer;
    private int quantity;
    private String imageUrl;
    private String userId;

    public Robot(){}

    public Robot(Parcel in) {
        objectId = in.readString();
        updatedAt = in.readString();
        createdAt = in.readString();
        model = in.readString();
        color = in.readString();
        year = in.readInt();
        price = in.readInt();
        manufacturer = in.readString();
        quantity = in.readInt();
        imageUrl = in.readString();
        userId = in.readString();
    }

    public static final Creator<Robot> CREATOR = new Creator<Robot>() {
        @Override
        public Robot createFromParcel(Parcel in) {
            return new Robot(in);
        }

        @Override
        public Robot[] newArray(int size) {
            return new Robot[size];
        }
    };

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(objectId);
        dest.writeString(updatedAt);
        dest.writeString(createdAt);
        dest.writeString(model);
        dest.writeString(color);
        dest.writeInt(year);
        dest.writeInt(price);
        dest.writeString(manufacturer);
        dest.writeInt(quantity);
        dest.writeString(imageUrl);
        dest.writeString(userId);
    }
}
