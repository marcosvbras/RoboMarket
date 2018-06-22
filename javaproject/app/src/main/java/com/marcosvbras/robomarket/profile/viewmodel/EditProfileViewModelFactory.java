package com.marcosvbras.robomarket.profile.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;

import javax.inject.Inject;

public class EditProfileViewModelFactory implements ViewModelProvider.Factory {

    private BaseActivityCallback activityCallback;

    @Inject
    public EditProfileViewModelFactory(@NonNull BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EditProfileViewModel(activityCallback);
    }
}
