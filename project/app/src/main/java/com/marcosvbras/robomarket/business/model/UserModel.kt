package com.marcosvbras.robomarket.business.model

import com.marcosvbras.robomarket.app.App
import com.marcosvbras.robomarket.business.api.APIService
import com.marcosvbras.robomarket.business.beans.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserModel {

    fun getAuthenticatedUser(): Observable<User>? {
        return APIService.getService()?.getUserByToken()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun login(username: String, password: String): Observable<User>? {
        return APIService.getService()?.login(username, password)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun signUp(newUser: User): Observable<User>? {
        return APIService.getService()?.signUp(newUser)
                ?.flatMap { (objectId, _, _, _, _, _, sessionToken) ->
                    App.instance.writeUserCredentials(
                            objectId!!, sessionToken!!)
                    APIService.getService()?.getUser(objectId!!)
                }
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun updateUser(newData: User, objectId: String): Observable<User>? {
        return APIService.getService()?.updateUser(newData, objectId)
                ?.flatMap { APIService.getService()?.getUserByToken() }
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun resetPassword(user: User): Observable<Void>? {
        return APIService.getService()?.resetPassword(user)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteUser(userId: String): Observable<Void>? {
        return APIService.getService()?.deleteUser(userId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

}