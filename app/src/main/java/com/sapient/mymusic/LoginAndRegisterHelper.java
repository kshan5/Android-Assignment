package com.sapient.mymusic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kshan5 on 12/30/2016.
 */

public class LoginAndRegisterHelper {

    DatabaseHelper database;

    LoginAndRegisterHelper (DatabaseHelper databaseHelper){
        this.database = databaseHelper;
    }


    public boolean registerUserData(Map userInputValues){

        ContentValues contentValues = new ContentValues();

        contentValues.put("FIRST_NAME", userInputValues.get("FirstName").toString());
        contentValues.put("LAST_NAME", userInputValues.get("LastName").toString());
        contentValues.put(MyAppConstants.USERS_EMAIL, userInputValues.get("Email").toString());

        contentValues.put(MyAppConstants.USERS_PASSWORD, userInputValues.get("Password").toString());
        contentValues.put(MyAppConstants.USERS_MOBILE, userInputValues.get("Mobile").toString());
        contentValues.put("GENDER", userInputValues.get("Gender").toString());

        contentValues.put("LANGUAGE", userInputValues.get("Lang").toString());

        contentValues.put("GENDER_GROUP", userInputValues.get("AgeGroup").toString());


        long result = database.getWriteableDB().insert(MyAppConstants.TABLE_NAME, null, contentValues);

        if(result == -1)
        {
            return false;
        }else
        {
            return true;
        }
    }


    public boolean storeAlbums(ContentValues contentValues){

        long result = database.getWriteableDB().insert("ALBUMS", null, contentValues);

        if(result == -1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public boolean loginUser(String emailId, String password){

     Cursor cursor = database.getWriteableDB().rawQuery("SELECT PASSWORD FROM USERS WHERE EMAIL = ?", new String[]{emailId});
        cursor.moveToFirst();

        if(cursor.getCount()>0)
        {
        String pwd = cursor.getString(cursor.getColumnIndex("PASSWORD"));

            Log.i("DB Password :", pwd);
            if(pwd.equals(password)){
                return true;
            }
        }
        return false;
    }


    public HashMap<String, String> getAlbumDetails(String albumId){

        HashMap<String, String> albumMap= null;
        Cursor cursor = database.getWriteableDB().rawQuery("SELECT ID,ALBUM_NAME, ARTIST, ALBUM_RELEASE_DATE, " +
                "PRODUCER,GENRES,SONGS,RATING,AWARDS FROM ALBUMS WHERE ID = ?", new String[]{albumId});
        cursor.moveToFirst();

        if(cursor.getCount()>0)
        {
            albumMap = new HashMap<String, String>();
            albumMap.put("Id", cursor.getString(cursor.getColumnIndex("ID")));
            albumMap.put("Name", cursor.getString(cursor.getColumnIndex("ALBUM_NAME")));
            albumMap.put("Artist", cursor.getString(cursor.getColumnIndex("ARTIST")));
            albumMap.put("ReleaseDate", cursor.getString(cursor.getColumnIndex("ALBUM_RELEASE_DATE")));
            albumMap.put("Producer", cursor.getString(cursor.getColumnIndex("PRODUCER")));
            albumMap.put("Genres", cursor.getString(cursor.getColumnIndex("GENRES")));
            albumMap.put("Songs", cursor.getString(cursor.getColumnIndex("SONGS")));
            albumMap.put("Ratings", Double.toString(cursor.getDouble(cursor.getColumnIndex("RATING"))));
            albumMap.put("Awards", cursor.getString(cursor.getColumnIndex("AWARDS")));

        }
        return albumMap;
    }

    public int checkUserAvailabilty(String emailId){

        Cursor cursor = database.getWriteableDB().rawQuery("SELECT EMAIL FROM USERS WHERE EMAIL = ?", new String[]{emailId});
        cursor.moveToFirst();
        return cursor.getCount();
    }


    public void populateDataFromDB(){

    }

}
