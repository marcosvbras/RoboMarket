package com.marcosvbras.robomarket.utils;

import android.databinding.BaseObservable;

public class ErrorObservable extends BaseObservable {

    private String errorString;
    private Integer errorResource;

    public ErrorObservable() {}

    public ErrorObservable(String errorString) {
        this.errorString = errorString;
        notifyChange();
    }

    public ErrorObservable(Integer errorResource) {
        this.errorResource = errorResource;
        notifyChange();
    }

    public String get() {
        return errorString;
    }

    public Integer getIntError() { return errorResource; }

    public void set(String errorString) {
        this.errorString = errorString;
        notifyChange();
    }

    public void set(Integer errorResource) {
        this.errorResource = errorResource;
        notifyChange();
    }

    public void clear() {
        errorResource = null;
        errorString = null;
        notifyChange();
    }

    public boolean hasErrorSetted() {
        return errorString != null || errorResource != null;
    }
}
