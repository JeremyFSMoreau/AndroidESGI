package com.team.esgi.projet_esgi.models.Actors;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActorsList {

    @SerializedName("data")
    @Expose
    private List<Actor> actor = null;

    public List<Actor> getActor() {
        return actor;
    }

    public void setActor(List<Actor> actor) {
        this.actor = actor;
    }

}