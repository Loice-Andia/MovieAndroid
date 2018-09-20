package com.andia.loice.movies.model.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Movie {

    @ColumnInfo
    @Expose
    @SerializedName("adult")
    private Boolean mAdult;
    @ColumnInfo
    @Expose
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @PrimaryKey
    @Expose
    @SerializedName("id")
    private Long mId;
    @ColumnInfo
    @Expose
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @ColumnInfo
    @Expose
    @SerializedName("original_title")
    private String mOriginalTitle;
    @ColumnInfo
    @Expose
    @SerializedName("overview")
    private String mOverview;
    @ColumnInfo
    @Expose
    @SerializedName("popularity")
    private Double mPopularity;
    @ColumnInfo
    @Expose
    @SerializedName("poster_path")
    private String mPosterPath;
    @ColumnInfo
    @Expose
    @SerializedName("release_date")
    private String mReleaseDate;
    @ColumnInfo
    @Expose
    @SerializedName("title")
    private String mTitle;
    @ColumnInfo
    @Expose
    @SerializedName("video")
    private Boolean mVideo;
    @ColumnInfo
    @Expose
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @ColumnInfo
    @Expose
    @SerializedName("vote_count")
    private Long mVoteCount;

    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        mAdult = adult;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Boolean getVideo() {
        return mVideo;
    }

    public void setVideo(Boolean video) {
        mVideo = video;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public Long getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Long voteCount) {
        mVoteCount = voteCount;
    }

}
