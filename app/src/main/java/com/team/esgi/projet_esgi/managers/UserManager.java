package com.team.esgi.projet_esgi.managers;

import android.content.Context;
import android.util.Log;

import com.team.esgi.projet_esgi.MainActivity;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.data.remote.UserServices.UserServices;
import com.team.esgi.projet_esgi.fragments.connection.OtherFragment;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.User.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class UserManager {

    //classe qui permet de gérer l'utilisateur (permet de récupérer son état (connecté ?), méthode de connexion, méthode de déconnexion
    // connexion

    //region Singleton

    private static UserManager instance;
    private UserServices userService;

    public synchronized static UserManager getInstance(){
        if(instance == null) {
            instance = new UserManager();
            instance.userService = ApiUtils.getUserService();
        }
        return instance;
    }

    //private constructor
    private UserManager(){}

    //endregion

    private  User userData;



    public void connexion(Context context, String apikey, String userkey, String username){
        instance.sendPost(context, apikey, userkey, username);
    }

    public void sendPost(final Context context, String apikey, String userkey, String username) {
        final User user = new User(apikey,userkey,username);
        userService.login(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    user.setToken(response.body().getToken());
                    KeyValueDB.setUser(context,user);
                    sendGet(context,user);
                    ((MainActivity)context).pushFragment(OtherFragment.newInstance());
                }
                else
                    Log.d("arf","c'est raté");
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void sendGet(final Context context ,final User user) {
        userService.show("Bearer " + user.getToken()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    user.setUserData(response.body().getUserData());
                    KeyValueDB.setUser(context,user);
                    Log.d("test",user.getUserData().getLanguage());
                }
                else
                    Log.d("arf","c'est raté");
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    private static void deconnexion(){

    }

    private static void isConnected(){

    }


    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }
}
