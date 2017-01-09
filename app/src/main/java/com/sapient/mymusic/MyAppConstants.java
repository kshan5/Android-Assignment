package com.sapient.mymusic;

/**
 * Created by kshan5 on 1/3/2017.
 */

public class MyAppConstants {

    /* Database Constants */
    public static final String DATABASE_NAME="MUSICSHOP";
    public static final String TABLE_NAME="USERS";
    public static final String USERS_ID="ID";
    public static final String USERS_PASSWORD="PASSWORD";
    public static final String USERS_EMAIL="EMAIL";
    public static final String USERS_MOBILE="MOBILE";
    public static final String CREATE_USERS_TABLE="CREATE TABLE "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "FIRST_NAME TEXT NOT NULL, LAST_NAME TEXT, LANGUAGE TEXT NOT NULL, " +
            USERS_EMAIL+ " TEXT NOT NULL UNIQUE, "+USERS_PASSWORD+" TEXT NOT NULL, "+USERS_MOBILE+" TEXT NOT NULL, GENDER CHARACTER(1) NOT NULL, GENDER_GROUP TEXT, APP_RATING INTEGER )";

    public static final String DROP_USERS_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;

    public static final String DROP_ALBUM_TABLE="DROP TABLE IF EXISTS ALUMBS";

    public static final String CREATE_ALBUM_TABLE="CREATE TABLE ALBUMS (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
    " ALBUM_NAME TEXT NOT NULL, ARTIST TEXT NOT NULL, ALBUM_RELEASE_DATE TEXT NOT NULL, PRODUCER TEXT , " +
            "GENRES TEXT, SONGS TEXT NOT NULL, RATING DOUBLE, AWARDS TEXT)";

}
