package com.team.esgi.projet_esgi.models.Series;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EpisodesList {

    @SerializedName("data")
    @Expose
    private List<Episode> episode = null;
    @SerializedName("errors")
    @Expose
    private Errors errors;
    @SerializedName("links")
    @Expose
    private Links links;

    public List<Episode> getEpisode() {
        return episode;
    }

    public void setEpisode(List<Episode> episode) {
        this.episode = episode;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}