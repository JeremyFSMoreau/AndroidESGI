package com.team.esgi.projet_esgi.models.Series;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastUpdated {

    @SerializedName("data")
    @Expose
    private List<SerieLastUpdated> serieLastUpdated = null;

    public List<SerieLastUpdated> getSerieLastUpdated() {
        return serieLastUpdated;
    }

    public void setSerieLastUpdated(List<SerieLastUpdated> serieLastUpdated) {
        this.serieLastUpdated = serieLastUpdated;
    }

}