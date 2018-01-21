package com.team.esgi.projet_esgi.managers;

import android.util.Log;

import com.team.esgi.projet_esgi.data.remote.ApiUtils;
import com.team.esgi.projet_esgi.data.remote.ShowServices.ShowServices;
import com.team.esgi.projet_esgi.data.remote.UserServices.UserServices;
import com.team.esgi.projet_esgi.models.Episodes.Episode;
import com.team.esgi.projet_esgi.models.Series.Serie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SeriesManager {

    //region Singleton

    private static SeriesManager instance;
    private ShowServices showServices;
    private Serie serie;

    public synchronized static SeriesManager getInstance(){
        if(instance == null) {
            instance = new SeriesManager();
            instance.showServices = ApiUtils.getShowService();
            instance.serie = new Serie();
        }
        return instance;
    }

    private SeriesManager(){}



}
