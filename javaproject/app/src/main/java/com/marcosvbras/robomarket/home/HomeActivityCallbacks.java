package com.marcosvbras.robomarket.home;

import android.support.v4.app.Fragment;

import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

public interface HomeActivityCallbacks extends BaseActivityCallback {
    void replaceFragment(Fragment fragment);
}
