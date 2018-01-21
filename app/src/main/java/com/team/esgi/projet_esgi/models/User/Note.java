package com.team.esgi.projet_esgi.models.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {

    @SerializedName("ratingType")
    @Expose
    private String ratingType;
    @SerializedName("ratingItemId")
    @Expose
    private Integer ratingItemId;
    @SerializedName("rating")
    @Expose
    private Integer rating;

    public String getRatingType() {
        return ratingType;
    }

    public void setRatingType(String ratingType) {
        this.ratingType = ratingType;
    }

    public Integer getRatingItemId() {
        return ratingItemId;
    }

    public void setRatingItemId(Integer ratingItemId) {
        this.ratingItemId = ratingItemId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}