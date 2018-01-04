package com.team.esgi.projet_esgi.models.Series;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Episode {

    @SerializedName("absoluteNumber")
    @Expose
    private Integer absoluteNumber;
    @SerializedName("airedEpisodeNumber")
    @Expose
    private Integer airedEpisodeNumber;
    @SerializedName("airedSeason")
    @Expose
    private Integer airedSeason;
    @SerializedName("airsAfterSeason")
    @Expose
    private Integer airsAfterSeason;
    @SerializedName("airsBeforeEpisode")
    @Expose
    private Integer airsBeforeEpisode;
    @SerializedName("airsBeforeSeason")
    @Expose
    private Integer airsBeforeSeason;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("directors")
    @Expose
    private List<String> directors = null;
    @SerializedName("dvdChapter")
    @Expose
    private Integer dvdChapter;
    @SerializedName("dvdDiscid")
    @Expose
    private String dvdDiscid;
    @SerializedName("dvdEpisodeNumber")
    @Expose
    private Integer dvdEpisodeNumber;
    @SerializedName("dvdSeason")
    @Expose
    private Integer dvdSeason;
    @SerializedName("episodeName")
    @Expose
    private String episodeName;
    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("firstAired")
    @Expose
    private String firstAired;
    @SerializedName("guestStars")
    @Expose
    private List<String> guestStars = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imdbId")
    @Expose
    private String imdbId;
    @SerializedName("lastUpdated")
    @Expose
    private Integer lastUpdated;
    @SerializedName("lastUpdatedBy")
    @Expose
    private String lastUpdatedBy;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("productionCode")
    @Expose
    private String productionCode;
    @SerializedName("seriesId")
    @Expose
    private String seriesId;
    @SerializedName("showUrl")
    @Expose
    private String showUrl;
    @SerializedName("siteRating")
    @Expose
    private Integer siteRating;
    @SerializedName("siteRatingCount")
    @Expose
    private Integer siteRatingCount;
    @SerializedName("thumbAdded")
    @Expose
    private String thumbAdded;
    @SerializedName("thumbAuthor")
    @Expose
    private Integer thumbAuthor;
    @SerializedName("thumbHeight")
    @Expose
    private String thumbHeight;
    @SerializedName("thumbWidth")
    @Expose
    private String thumbWidth;
    @SerializedName("writers")
    @Expose
    private List<String> writers = null;

    public Integer getAbsoluteNumber() {
        return absoluteNumber;
    }

    public void setAbsoluteNumber(Integer absoluteNumber) {
        this.absoluteNumber = absoluteNumber;
    }

    public Integer getAiredEpisodeNumber() {
        return airedEpisodeNumber;
    }

    public void setAiredEpisodeNumber(Integer airedEpisodeNumber) {
        this.airedEpisodeNumber = airedEpisodeNumber;
    }

    public Integer getAiredSeason() {
        return airedSeason;
    }

    public void setAiredSeason(Integer airedSeason) {
        this.airedSeason = airedSeason;
    }

    public Integer getAirsAfterSeason() {
        return airsAfterSeason;
    }

    public void setAirsAfterSeason(Integer airsAfterSeason) {
        this.airsAfterSeason = airsAfterSeason;
    }

    public Integer getAirsBeforeEpisode() {
        return airsBeforeEpisode;
    }

    public void setAirsBeforeEpisode(Integer airsBeforeEpisode) {
        this.airsBeforeEpisode = airsBeforeEpisode;
    }

    public Integer getAirsBeforeSeason() {
        return airsBeforeSeason;
    }

    public void setAirsBeforeSeason(Integer airsBeforeSeason) {
        this.airsBeforeSeason = airsBeforeSeason;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public Integer getDvdChapter() {
        return dvdChapter;
    }

    public void setDvdChapter(Integer dvdChapter) {
        this.dvdChapter = dvdChapter;
    }

    public String getDvdDiscid() {
        return dvdDiscid;
    }

    public void setDvdDiscid(String dvdDiscid) {
        this.dvdDiscid = dvdDiscid;
    }

    public Integer getDvdEpisodeNumber() {
        return dvdEpisodeNumber;
    }

    public void setDvdEpisodeNumber(Integer dvdEpisodeNumber) {
        this.dvdEpisodeNumber = dvdEpisodeNumber;
    }

    public Integer getDvdSeason() {
        return dvdSeason;
    }

    public void setDvdSeason(Integer dvdSeason) {
        this.dvdSeason = dvdSeason;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    public List<String> getGuestStars() {
        return guestStars;
    }

    public void setGuestStars(List<String> guestStars) {
        this.guestStars = guestStars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Integer getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public Integer getSiteRating() {
        return siteRating;
    }

    public void setSiteRating(Integer siteRating) {
        this.siteRating = siteRating;
    }

    public Integer getSiteRatingCount() {
        return siteRatingCount;
    }

    public void setSiteRatingCount(Integer siteRatingCount) {
        this.siteRatingCount = siteRatingCount;
    }

    public String getThumbAdded() {
        return thumbAdded;
    }

    public void setThumbAdded(String thumbAdded) {
        this.thumbAdded = thumbAdded;
    }

    public Integer getThumbAuthor() {
        return thumbAuthor;
    }

    public void setThumbAuthor(Integer thumbAuthor) {
        this.thumbAuthor = thumbAuthor;
    }

    public String getThumbHeight() {
        return thumbHeight;
    }

    public void setThumbHeight(String thumbHeight) {
        this.thumbHeight = thumbHeight;
    }

    public String getThumbWidth() {
        return thumbWidth;
    }

    public void setThumbWidth(String thumbWidth) {
        this.thumbWidth = thumbWidth;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

}

/*package com.team.esgi.projet_esgi.models.Series;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Episode extends RealmObject {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}*/

//Episode episode = new Episode;
//
//Realm.init(this);

//
//Episode episode = new Episode();
//Episode.getInstance();
