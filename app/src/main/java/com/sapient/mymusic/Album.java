package com.sapient.mymusic;

/**
 * Created by kshan5 on 1/5/2017.
 */

public class Album {

    private String albArtist;
    private String albName;


    public Album(String albArtist, String albName) {
        this.albArtist = albArtist;
        this.albName = albName;
    }

    public String getAlbArtist() {
        return albArtist;
    }

    public void setAlbArtist(String albArtist) {
        this.albArtist = albArtist;
    }

    public String getAlbName() {
        return albName;
    }

    public void setAlbName(String albName) {
        this.albName = albName;
    }
}
