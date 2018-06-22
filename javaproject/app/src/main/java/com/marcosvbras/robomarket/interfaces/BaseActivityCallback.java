package com.marcosvbras.robomarket.interfaces;

import android.os.Bundle;

public interface BaseActivityCallback {

    void showDialogMessage(String message);

    void showDialogMessage(int message);

    void openActivity(Class<?> activity, boolean finishCurrentActivity);

    void openActivity(Class<?> activity, Bundle bundle, boolean finishCurrentActivity);

    void openActivityForResult(Class<?> activity, Bundle bundle, int requestCode);

    void setToolbar(int viewId, boolean displayHomeAsUpEnabled);

    void finishCurrentActivity();

}