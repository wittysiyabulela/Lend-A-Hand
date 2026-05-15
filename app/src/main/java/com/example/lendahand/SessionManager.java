package com.example.lendahand;

import android.content.Context;
import android.content.SharedPreferences;

public final class SessionManager {
    private static final String PREFS = "lendahand_session";
    private static final String KEY_LOGGED_IN = "logged_in";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_PASSWORD = "user_password";

    private SessionManager() {}

    public static boolean isLoggedIn(Context context) {
        return prefs(context).getBoolean(KEY_LOGGED_IN, false);
    }

    public static void setLoggedIn(Context context, boolean loggedIn) {
        prefs(context).edit().putBoolean(KEY_LOGGED_IN, loggedIn).apply();
    }

    public static boolean hasAccount(Context context) {
        return !prefs(context).getString(KEY_EMAIL, "").isEmpty();
    }

    public static boolean emailExists(Context context, String email) {
        return prefs(context).getString(KEY_EMAIL, "").equalsIgnoreCase(email.trim());
    }

    public static boolean credentialsMatch(Context context, String email, String password) {
        SharedPreferences p = prefs(context);
        return p.getString(KEY_EMAIL, "").equalsIgnoreCase(email.trim())
                && p.getString(KEY_PASSWORD, "").equals(password);
    }

    public static void saveCredentials(Context context, String email, String password) {
        prefs(context).edit()
                .putString(KEY_EMAIL, email.trim())
                .putString(KEY_PASSWORD, password)
                .apply();
    }

    public static void logout(Context context) {
        prefs(context).edit()
                .putBoolean(KEY_LOGGED_IN, false)
                .remove(KEY_EMAIL)
                .remove(KEY_PASSWORD)
                .apply();
    }

    private static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }
}

