package com.marcosvbras.robomarket.viewmodels;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Patterns;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.model.api.APIService;
import com.marcosvbras.robomarket.model.domains.User;
import com.marcosvbras.robomarket.utils.ErrorObservable;
import com.marcosvbras.robomarket.views.activities.HomeActivity;
import com.marcosvbras.robomarket.views.interfaces.BaseActivityCallback;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private Disposable disposable;
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
        avatarUrl.set("https://robohash.org/9.png");
    }

    @SuppressLint("CheckResult")
    public void signup() {
        if(isFormValid()) {
            if(disposable != null && !disposable.isDisposed())
                disposable.dispose();

            User user = new User();
            user.setBirth(birth.get());
            user.setAvatarUrl(avatarUrl.get());
            user.setEmail(email.get());
            user.setGenre(genre.get());
            user.setName(name.get());
            user.setPassword(password.get());
            user.setPhone(phone.get());
            user.setUsername(username.get());

            APIService.getService().signup(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(d -> {
                        isLoading.set(true);
                        disposable = d;
                    })
                    .subscribe(next -> {

                    }, error -> {
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
}
