package com.team.esgi.projet_esgi.models.Series;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SerieLastUpdated {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lastUpdated")
    @Expose
    private Integer lastUpdated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}