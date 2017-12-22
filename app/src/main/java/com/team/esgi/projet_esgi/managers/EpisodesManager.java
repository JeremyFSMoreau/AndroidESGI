package com.team.esgi.projet_esgi.managers;

import com.team.esgi.projet_esgi.models.Episode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yves on 21/12/2017.
 */

public class EpisodesManager {

    //region Singleton

    private static EpisodesManager instance;

    public synchronized static EpisodesManager getInstance(){
        if(instance == null) {
            instance = new EpisodesManager();
            instance.episodes = new ArrayList<>();
        }
        return instance;
    }

    //private constructor
    private EpisodesManager(){}

    //endregion

    private List<Episode> episodes;

}
