package com.example.watchionbeta;

public class movies {
    String movieName,url,imageUrl;

    public movies() {

    }

    public movies(String movieName, String url, String imageUrl) {
        this.movieName = movieName;
        this.url = url;
        this.imageUrl = imageUrl;
    }
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}


