package com.sapient.mymusic;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class AlbumDetailedView extends AppCompatActivity {

    ImageView imageView;
    TextView alName, alArtist, alRelDate, alProducer, alGenres, alSongs, alRatings, alAwards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detailed_view);

        initializeDetailedViewElements();

        LoginAndRegisterHelper helper = new LoginAndRegisterHelper(DatabaseHelper.getInstance(getApplicationContext()));

        long selectedId = getIntent().getLongExtra("selectedId", 0);

        String albumId = Long.toString(selectedId);
        if(albumId!=null){

            if(albumId.equals("0") || albumId.equals("2")|| albumId.equals("4")){
                setImageView(albumId);
                setAlbumDetailedView(helper.getAlbumDetails("1001"));
            }else if(albumId.equals("1")|| albumId.equals("3") || albumId.equals("5") ){
                setImageView(albumId);
                setAlbumDetailedView(helper.getAlbumDetails("1002"));
            }else{
                setImageView(albumId);
                setAlbumDetailedView(helper.getAlbumDetails("1003"));
            }
        }
    }

    public void initializeDetailedViewElements(){
        imageView = (ImageView) findViewById(R.id.album_poster);
        alName = (TextView)findViewById(R.id.al_name);
        alArtist = (TextView)findViewById(R.id.al_artist);
        alRelDate = (TextView)findViewById(R.id.al_rel_date);
        alProducer = (TextView)findViewById(R.id.al_producer);
        alGenres = (TextView)findViewById(R.id.al_geners);
        alSongs = (TextView)findViewById(R.id.al_songs);
        alRatings = (TextView)findViewById(R.id.al_ratings);
        alAwards = (TextView)findViewById(R.id.al_awards);


    }
    public void setAlbumDetailedView(HashMap<String, String> viewDetails){

        alName.setText("Album Name : "+viewDetails.get("Name"));
        alArtist.setText("Artist : "+viewDetails.get("Artist"));
        alRelDate.setText("Album Release Date : "+viewDetails.get("ReleaseDate"));
        alProducer.setText("Producer : "+viewDetails.get("Producer"));
        alGenres.setText("Genres : "+viewDetails.get("Genres"));
        alSongs.setText("Songs : "+viewDetails.get("Songs"));
        alRatings.setText("Ratings : "+viewDetails.get("Ratings"));
        alAwards.setText("Awards : "+viewDetails.get("Awards"));

    }

    public void setImageView(String imgId){

        switch (imgId){
            case "0":
                imageView.setImageResource(R.drawable.a1001);
                break;
            case "1":
                imageView.setImageResource(R.drawable.a1002);
                break;
            case "2":
                imageView.setImageResource(R.drawable.a1003);
                break;
            case "3":
                imageView.setImageResource(R.drawable.a1004);
                break;
            case "4":
                imageView.setImageResource(R.drawable.a1005);
                break;
            case "5":
                imageView.setImageResource(R.drawable.a1006);
                break;
            case "6":
                imageView.setImageResource(R.drawable.a1007);
                break;
            case "7":
                imageView.setImageResource(R.drawable.a1008);
                break;
            default:
                imageView.setImageResource(R.drawable.album9);
        }
    }
}
