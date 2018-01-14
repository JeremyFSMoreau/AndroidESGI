package com.team.esgi.projet_esgi.managers;

import android.util.Log;

import com.team.esgi.projet_esgi.MainActivity;
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

    public synchronized static UserManager getInstance(){
        if(instance == null) {
            instance = new UserManager();
            instance.userData = new ArrayList<>();

        }
        return instance;
    }

    //private constructor
    private UserManager(){}

    //endregion

    private  List<User> userData;

    public static void connexion(String apikey, String userkey, String username){

    }

    private static void deconnexion(){

    }

    private static void isConnected(){

    }


}
