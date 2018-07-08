package com.marcosvbras.robomarket.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.marcosvbras.robomarket.business.beans.User
import java.util.HashMap

class App : Application() {

    var user: User? = null

    private val preferences: SharedPreferences
        get() = getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)

    val userCredentials: HashMap<String, String>
        get() {
            val preferences = instance.preferences
            val userId = preferences.getString(USER_ID_PREF, null)
            val sessionToken = preferences.getString(SESSION_TOKEN_PREF, null)
            val credentials = HashMap<String, String>()
            credentials[USER_ID_PREF] = userId
            credentials[SESSION_TOKEN_PREF] = sessionToken

            return credentials
        }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun writeUserCredentials(userId: String, sessionToken: String): Boolean {
        val editor = instance.preferences.edit()
        editor.putString(USER_ID_PREF, userId)
        editor.putString(SESSION_TOKEN_PREF, sessionToken)
        return editor.commit()
    }

    fun hasCredentials(): Boolean {
        val preferences = instance.preferences
        val userId = preferences.getString(USER_ID_PREF, null)
        val sessionToken = preferences.getString(SESSION_TOKEN_PREF, null)

        return userId != null && sessionToken != null
    }

    fun deleteCredentials(): Boolean {
        val editor = instance.preferences.edit()
        editor.putString(USER_ID_PREF, null)
        editor.putString(SESSION_TOKEN_PREF, null)

        return editor.commit()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}