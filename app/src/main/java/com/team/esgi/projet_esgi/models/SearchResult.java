package com.team.esgi.projet_esgi.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.team.esgi.projet_esgi.models.Series.Serie;

import java.util.List;

public class SearchResult {
    @SerializedName("data")
    @Expose
    private List<Serie> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public SearchResult() {
    }

    /**
     *
     * @param data
     */
    public SearchResult(List<Serie> data) {
        super();
        this.data = data;
    }

    public List<Serie> getData() {
        return data;
    }

    public void setData(List<Serie> data) {
        this.data = data;
    }
}
