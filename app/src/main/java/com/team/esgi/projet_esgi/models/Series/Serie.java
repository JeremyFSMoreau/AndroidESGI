package com.team.esgi.projet_esgi.models.Series;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

public class Serie{

    @SerializedName("aliases")
    @Expose
    private List<String> aliases = null;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("firstAired")
    @Expose
    private String firstAired;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("network")
    @Expose
    private String network;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("seriesName")
    @Expose
    private String seriesName;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private Episode episode;
    @SerializedName("errors")
    @Expose
    private Errors errors;

    @SerializedName("links")
    @Expose
    private Links links;

    public Serie() {
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
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