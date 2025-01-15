package com.example.recyclefilmes;

public class Movie {
    private double rating;
    private String overview;
    private String title;
    private int posterImage;
    private String releaseDate;

    public Movie(String title, int posterImage, String releaseDate, double rating, String overview) {
        this.title = title;
        this.posterImage = posterImage;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.overview = overview;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(int posterImage) {
        this.posterImage = posterImage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

}
