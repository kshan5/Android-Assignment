package com.sapient.mymusic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by kshan5 on 1/5/2017.
 */

public class CustomListViewAdapter extends ArrayAdapter<String> {

    public CustomListViewAdapter(Context context, Integer[] imgId, String[] title, String[] ratings){

        super(context, R.layout.music_album_row_view);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
