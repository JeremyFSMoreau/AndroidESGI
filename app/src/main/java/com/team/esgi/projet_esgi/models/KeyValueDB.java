package com.team.esgi.projet_esgi.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.team.esgi.projet_esgi.models.Series.Serie;
import com.team.esgi.projet_esgi.models.User.User;

public class KeyValueDB {
    private SharedPreferences sharedPreferences;
    private static String PREF_NAME = "prefs";

    public KeyValueDB() {
        // Blank
    }

    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getUsername(Context context) {
        return getPrefs(context).getString("username_key", "default_username");
    }

    public static String getToken(Context context) {
        return getPrefs(context).getString("token_key", "default_token");
    }

    public static String getUser(Context context) {
        return getPrefs(context).getString("user_key", "default_user");
    }

    public static String getSerie(Context context) {
        return getPrefs(context).getString("serie_key", "default_serie");
    }

    public static void setUsername(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("username_key", input);
        editor.commit();
    }

    public static void setToken(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("token_key", input);
        editor.commit();
    }

    public static void setUser(Context context, User user) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user,User.class);
        editor.putString("user_key", json);
        editor.apply();
    }

    public static void setSerie(Context context, Serie serie) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(serie,Serie.class);
        editor.putString("serie_key", json);
        editor.apply();
    }
}
