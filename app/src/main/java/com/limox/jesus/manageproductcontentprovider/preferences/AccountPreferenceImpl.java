package com.limox.jesus.manageproductcontentprovider.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.limox.jesus.manageproductcontentprovider.interfaces.Preferences;

/**
 * Created by usuario on 10/11/16.
 */

public class AccountPreferenceImpl implements Preferences {

    private static Preferences accountPreference;
    //No es el package es el id de la aplicacin
    // public static final String FILE = "com.limox.jesus.recicledview_application_preferences";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    private SharedPreferences sharedPreferences;

    private AccountPreferenceImpl(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    //Singletón de la clase
    public static Preferences getInstance(Context context) {
        if (accountPreference == null) {
            accountPreference = new AccountPreferenceImpl(context);
        }
        return accountPreference;
    }

    public void putUser(String user) {
        getEditor().putString(USER, user).apply();
    }

    public void putPassword(String password) {
        getEditor().putString(PASSWORD, password).apply();
    }

    public void putEmail(String email) {
        getEditor().putString(EMAIL, email).apply();
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }
}
