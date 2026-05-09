package com.example.lendahand;

import android.content.Context;
import android.content.SharedPreferences;

public final class SessionManager {
    private static final String PREFS = "lendahand_session";
    private static final String KEY_LOGGED_IN = "logged_in";

    private SessionManager() {}

    public static boolean isLoggedIn(Context context) {
        return prefs(context).getBoolean(KEY_LOGGED_IN, false);
    }

    public static void setLoggedIn(Context context, boolean loggedIn) {
        prefs(context).edit().putBoolean(KEY_LOGGED_IN, loggedIn).apply();
    }

    public static void logout(Context context) {
        setLoggedIn(context, false);
    }

    private static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }
}

