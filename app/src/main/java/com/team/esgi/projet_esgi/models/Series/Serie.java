package com.team.esgi.projet_esgi.models.Series;

import android.provider.ContactsContract;

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
    private DataSerie dataSerie;

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

    public DataSerie getDataSerie() {
        return dataSerie;
    }

    public void setDataSerie(DataSerie dataSerie) {
        this.dataSerie = dataSerie;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "aliases=" + aliases +
                ", banner='" + banner + '\'' +
                ", firstAired='" + firstAired + '\'' +
                ", id=" + id +
                ", network='" + network + '\'' +
                ", overview='" + overview + '\'' +
                ", seriesName='" + seriesName + '\'' +
                ", status='" + status + '\'' +
                ", dataSerie=" + dataSerie +
                '}';
    }
}