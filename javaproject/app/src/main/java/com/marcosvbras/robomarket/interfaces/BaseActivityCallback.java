package com.marcosvbras.robomarket.interfaces;

import android.content.Intent;
import android.os.Bundle;

import com.marcosvbras.robomarket.DialogActions;

public interface BaseActivityCallback {

    void showDialogMessage(String message);

    void showDialogMessage(int message);

    void openActivity(Class<?> activity, boolean finishCurrentActivity);

    void openActivity(Class<?> activity, Bundle bundle, boolean finishCurrentActivity);

    void openActivityForResult(Class<?> activity, Bundle bundle, int requestCode);

    void setToolbar(int viewId, boolean displayHomeAsUpEnabled);

    void finishCurrentActivity();

    void setActivityResult(int resultCode);

    void setActivityResult(int resultCode, Intent intent);

    void setActivityResult(int resultCode, Bundle bundle);

    void showCustomAlertDialog(Object object, DialogActions actions);

}