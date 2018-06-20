package com.marcosvbras.robomarket.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.marcosvbras.robomarket.business.domain.User;
import com.marcosvbras.robomarket.utils.Constants;

import java.util.HashMap;

public class App extends Application {

    private static App instance;

    private User user;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private SharedPreferences getPreferences() {
        return getSharedPreferences(Constants.Preferences.PREF_KEY, Context.MODE_PRIVATE);
    }

    public boolean writeUserCredentials(String userId, String sessionToken) {
        SharedPreferences.Editor editor = instance.getPreferences().edit();
        editor.putString(Constants.Preferences.USER_ID_KEY, userId);
        editor.putString(Constants.Preferences.SESSION_TOKEN_KEY, sessionToken);
        return editor.commit();
    }

    public HashMap<String, String> getUserCredentials() {
        SharedPreferences preferences = instance.getPreferences();
        String userId = preferences.getString(Constants.Preferences.USER_ID_KEY, null);
        String sessionToken = preferences.getString(Constants.Preferences.SESSION_TOKEN_KEY, null);
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put(Constants.Preferences.USER_ID_KEY, userId);
        credentials.put(Constants.Preferences.SESSION_TOKEN_KEY, sessionToken);

        return credentials;
    }

    public boolean hasCredentials() {
        SharedPreferences preferences = instance.getPreferences();
        String userId = preferences.getString(Constants.Preferences.USER_ID_KEY, null);
        String sessionToken = preferences.getString(Constants.Preferences.SESSION_TOKEN_KEY, null);

        return userId != null && sessionToken != null;
    }

    public boolean deleteCredentials() {
        SharedPreferences.Editor editor = instance.getPreferences().edit();
        editor.putString(Constants.Preferences.USER_ID_KEY, null);
        editor.putString(Constants.Preferences.SESSION_TOKEN_KEY, null);

        return editor.commit();
    }
}
