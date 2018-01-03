package com.team.esgi.projet_esgi.models.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("favoritesDisplaymode")
    @Expose
    private String favoritesDisplaymode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFavoritesDisplaymode() {
        return favoritesDisplaymode;
    }

    public void setFavoritesDisplaymode(String favoritesDisplaymode) {
        this.favoritesDisplaymode = favoritesDisplaymode;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", language='" + language + '\'' +
                ", favoritesDisplaymode='" + favoritesDisplaymode + '\'' +
                '}';
    }
}
