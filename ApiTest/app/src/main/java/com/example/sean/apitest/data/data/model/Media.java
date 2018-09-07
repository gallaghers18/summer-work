package com.example.sean.apitest.data.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media implements Comparable<Media> {

    private int id;
    @SerializedName("title")
    @Expose
    private String title;
//    private byte[] posterImage;
//    private String posterUrl;
    private boolean isMovie;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("runtime")
    @Expose
    private int runtime;
    private String releaseDate;
    @SerializedName("rating")
    @Expose
    private double rating;
    private String numSeasons;

    public Media(String title, String genre, int runtime, double rating) {
        this.title = title;
        this.genre = genre;
        this.runtime = runtime;
        this.rating = rating;
    }


//    public Media() {
//        this.title = "";
//        this.posterUrl = "";
//        this.id = 0;
//        this.isMovie = true;
//        this.posterImage = null;
//    }

//    public Media(int id) {
//        this.title = "";
//        this.posterUrl = "";
//        this.id = id;
//        this.isMovie = true;
//        this.posterImage = null;
//    }

//    public Media(int id, String title, String posterUrl, boolean isMovie) {
//        this.id = id;
//        this.title = title;
//        this.posterUrl = posterUrl;
//        id = 0;
//        this.isMovie = isMovie;
//    }

//    public Media(String title, byte[] posterImage, boolean isMovie, int id) {
//        this.title = title;
//        this.posterImage = posterImage;
//        this.isMovie = isMovie;
//        this.id = id;
//    }


    public int compareTo(Media compareMedia) {
        return trimDownTitle(this.title).compareTo(trimDownTitle(compareMedia.title));
    }

    private String trimDownTitle(String title) {
        if (title.startsWith("The")) {
            return title.substring(4);
        }
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String movieName) {
        this.title = movieName;
    }

//    public String getPosterUrl() {
//        return posterUrl;
//    }

//    public void setPosterUrl(String posterUrl) {
//        this.posterUrl = posterUrl;
//    }

//    public byte[] getPosterImage() {
//        return posterImage;
//    }

//    public void setPosterImage(byte[] posterImage) {
//        this.posterImage = posterImage;
//    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsMovie(boolean movie) {
        isMovie = movie;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isMovie() {
        return isMovie;
    }

    public String getNumSeasons() {
        return numSeasons;
    }

    public void setNumSeasons(String numSeasons) {
        this.numSeasons = numSeasons;
    }
}
