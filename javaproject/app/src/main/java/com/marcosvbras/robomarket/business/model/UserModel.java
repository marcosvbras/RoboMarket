package com.marcosvbras.robomarket.business.model;

import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.api.APIService;
import com.marcosvbras.robomarket.business.domain.User;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserModel {

    public Observable<User> getAuthenticatedUser() {
        return APIService.getService().getUserByToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<User> login(String username, String password) {
        return APIService.getService().login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<User> signUp(User newUser) {
        return APIService.getService().signup(newUser)
                .flatMap(userResponse -> {
                    App.getInstance().writeUserCredentials(
                            userResponse.getObjectId(), userResponse.getSessionToken());
                    return APIService.getService().getUser(userResponse.getObjectId());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<User> updateUser(User newData, String objectId) {
        return APIService.getService().updateUser(newData, objectId)
                .flatMap(userResponse -> APIService.getService().getUserByToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Void> resetPassword(User user) {
        return APIService.getService().resetPassword(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Void> deleteUser(String userId) {
        return APIService.getService().deleteUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
