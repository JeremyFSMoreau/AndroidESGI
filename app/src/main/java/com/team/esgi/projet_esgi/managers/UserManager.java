package com.team.esgi.projet_esgi.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.team.esgi.projet_esgi.MainActivity;
import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.data.remote.UserServices.UserServices;
import com.team.esgi.projet_esgi.fragments.MainMenuFragment;
import com.team.esgi.projet_esgi.fragments.connection.ConnectionFragment;
import com.team.esgi.projet_esgi.models.KeyValueDB;
import com.team.esgi.projet_esgi.models.User.NoteList;
import com.team.esgi.projet_esgi.models.User.User;
import com.team.esgi.projet_esgi.models.User.UserData;

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
                    ((MainActivity)context).pushFragment(MainMenuFragment.newInstance());
                }
                else
                {
                    Log.d("arf","c'est raté");
                    ((MainActivity)context).pushFragment(ConnectionFragment.newInstance());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                ((MainActivity)context).pushFragment(ConnectionFragment.newInstance());
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
                    getUserNotes(context,user);
                    getUserFavorites(context,user);
                    Log.d("test",user.getUserData().getLanguage());
                }
                else
                {
                    Log.d("arf","c'est raté");
                    ((MainActivity)context).pushFragment(ConnectionFragment.newInstance());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                ((MainActivity)context).pushFragment(ConnectionFragment.newInstance());
            }
        });
    }

    public void getUserFavorites(final Context context, final User user){
        userService.favoritesList("Bearer " + user.getToken()).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if(response.isSuccessful()) {
                    Log.d("AAh",response.body().getFavorite().getFavorites().toString());
                    user.getUserData().setFavorite(response.body().getFavorite());
                    KeyValueDB.setUser(context,user);
                }
                else
                {
                    Log.d("arf","c'est raté");
                    ((MainActivity)context).pushFragment(ConnectionFragment.newInstance());
                }

            }
            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API." + t.toString());
                ((MainActivity)context).pushFragment(ConnectionFragment.newInstance());
            }
        });
    }

    public void getUserNotes(final Context context, final User user){
        userService.getNotes("Bearer " + user.getToken()).enqueue(new Callback<NoteList>() {
            @Override
            public void onResponse(Call<NoteList> call, Response<NoteList> response) {
                if(response.isSuccessful()) {
                    Log.d("AAhzzz",response.body().toString());
                    KeyValueDB.setNotes(context,response.body());
                }
                else
                {
                    Log.d("arf","c'est raté");
                    ((MainActivity)context).pushFragment(ConnectionFragment.newInstance());
                }
            }
            @Override
            public void onFailure(Call<NoteList> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API." + t.toString());
                ((MainActivity)context).pushFragment(ConnectionFragment.newInstance());
            }
        });
    }

    public void refreshToken(final Context context,final User user){
        userService.refreshToken("Bearer " + user.getToken()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    user.setToken(response.body().getToken());
                    KeyValueDB.setUser(context,user);
                    ((MainActivity)context).pushFragment(MainMenuFragment.newInstance());
                }
                else
                {
                    SharedPreferences.Editor editor = KeyValueDB.getPrefs(context).edit();
                    editor.clear().commit();
                    ((MainActivity)context).pushFragment(ConnectionFragment.newInstance());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
                ((MainActivity)context).pushFragment(ConnectionFragment.newInstance());
            }
        });
    }
    private static void deconnexion(){

    }

    public static void isConnected(final Context context, User user){
    }


    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }
}
