package com.marcosvbras.robomarket.views.interfaces;

import android.os.Bundle;

public interface BaseActivityCallback {

    void showErrorDialog(String message);
    void showErrorDialog(int message);
    void openActivity(Class<?> activity, boolean finishCurrentActivity);
    void openActivity(Class<?> activity, Bundle bundle, boolean finishCurrentActivity);
    void setToolbar(int viewId, boolean displayHomeAsUpEnabled);

}
