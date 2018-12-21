package com.example.berq.e_commerce.helper;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "AndroidHiveLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.putLong("ExpiredDate", System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(60));
        if (pref.getLong("ExpiredDate", -1) > System.currentTimeMillis()) {
            //
        } else {
            editor.remove("key");
            editor.remove(KEY_IS_LOGGEDIN);
            editor.commit();
        }
        editor.apply();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getSessionCartValue() {
        return pref.getString("key", "");
    }

    public void addSessionCart(int id) {
        String strId = Integer.toString(id);

        String defaultValue = pref.getString("key", "");

        assert defaultValue != null;
        if (!defaultValue.isEmpty()) {
            editor.putString("key", defaultValue + "&" + strId);
        } else {
            editor.putString("key", "&" + strId);
        }
        editor.commit();

    }


    public String[] getSessionCart() {

        String defaultValue = pref.getString("key", "");
        String[] strArray = defaultValue.split("&");

        return strArray;
    }

    public void sil() {


        editor.remove("key");
        editor.commit();
    }

    public void silbirini(int positionId) {
        String defaultValue = pref.getString("key", "");
        assert defaultValue != null;
        defaultValue = defaultValue.replace("&" + positionId, "");
        editor.putString("key", defaultValue);
        editor.commit();
    }
}