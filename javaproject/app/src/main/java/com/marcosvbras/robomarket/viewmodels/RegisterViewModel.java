package com.marcosvbras.robomarket.viewmodels;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.RoboApplication;
import com.marcosvbras.robomarket.model.api.APIService;
import com.marcosvbras.robomarket.model.domains.User;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.utils.ErrorObservable;
import com.marcosvbras.robomarket.views.activities.HomeActivity;
import com.marcosvbras.robomarket.views.interfaces.BaseActivityCallback;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private Disposable disposable;
    private int currentRobotId = 1;
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> confirmPassword = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> genre = new ObservableField<>();
    public ObservableField<String> birth = new ObservableField<>();
    public ObservableField<String> avatarUrl = new ObservableField<>();
    public ErrorObservable emailFieldError = new ErrorObservable();
    public ErrorObservable passwordFieldError = new ErrorObservable();
    public ErrorObservable confirmPasswordFieldError = new ErrorObservable();
    public ErrorObservable usernameFieldError = new ErrorObservable();
    public ErrorObservable nameFieldError = new ErrorObservable();

    public RegisterViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        avatarUrl.set(Constants.Other.ROBOHASH_API + currentRobotId + Constants.Other.ROBOHASH_SET_2_PARAM);
    }

    @SuppressLint("CheckResult")
    public void signup() {
        if(isFormValid()) {
            if(disposable != null && !disposable.isDisposed())
                disposable.dispose();

            User newUser = new User();
            newUser.setBirth(birth.get());
            newUser.setAvatarUrl(avatarUrl.get());
            newUser.setEmail(email.get());
            newUser.setGenre(genre.get());
            newUser.setName(name.get());
            newUser.setPassword(password.get());
            newUser.setPhone(phone.get());
            newUser.setUsername(username.get());

            APIService.getService().signup(newUser)
                    .flatMap(userResponse -> {
                        RoboApplication.getInstance().writeUserCredentials(
                                userResponse.getObjectId(), userResponse.getSessionToken());
                        return APIService.getService().getUser(userResponse.getObjectId());
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(d -> {
                        isLoading.set(true);
                        disposable = d;
                    })
                    .subscribe(next -> RoboApplication.getInstance().setUser(next), error -> {
                        isLoading.set(false);
                        disposable.dispose();
                        disposable = null;
                        activityCallback.showErrorDialog(error.getMessage());
                    }, () -> {
                        isLoading.set(false);
                        disposable.dispose();
                        disposable = null;
                        activityCallback.openActivity(HomeActivity.class, true);
                    });
        }
    }

    public void signin() {
        activityCallback.finishCurrentActivity();
    }

    private boolean isFormValid() {
        if(TextUtils.isEmpty(name.get())) {
            nameFieldError.set(R.string.required_field);
            return false;
        }

        if(TextUtils.isEmpty(email.get())) {
            emailFieldError.set(R.string.required_field);
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()) {
            emailFieldError.set(R.string.invalid_email_format);
            return false;
        }

        if(TextUtils.isEmpty(username.get())) {
            usernameFieldError.set(R.string.required_field);
            return false;
        }

        if(TextUtils.isEmpty(password.get())) {
            passwordFieldError.set(R.string.required_field);
            return false;
        }

        if(TextUtils.isEmpty(confirmPassword.get())) {
            confirmPasswordFieldError.set(R.string.required_field);
            return false;
        }

        if(!password.get().equals(confirmPassword.get())) {
            confirmPasswordFieldError.set(R.string.different_passwords);
            return false;
        }

        return true;
    }

    public void onGenreSelected(AdapterView<?> parent, View view, int position, long id) {
        genre.set((String)parent.getSelectedItem());
    }

    public void changeAvatarImg() {
        currentRobotId++;
        avatarUrl.set(Constants.Other.ROBOHASH_API + currentRobotId + Constants.Other.ROBOHASH_SET_2_PARAM);
    }
}
