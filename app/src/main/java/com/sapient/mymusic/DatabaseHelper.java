package com.sapient.mymusic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kshan5 on 12/29/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper dbInstance;

    private final Context context;

    private static SQLiteDatabase sqLiteDatabase;


    private DatabaseHelper(Context context) {
        super(context, MyAppConstants.DATABASE_NAME, null, 8);
        this.context = context;
    }

    public static synchronized DatabaseHelper getInstance(Context context){

        if(null == dbInstance)
        {
            dbInstance = new DatabaseHelper(context);
        }
        return dbInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(MyAppConstants.CREATE_USERS_TABLE);
        db.execSQL(MyAppConstants.CREATE_ALBUM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MyAppConstants.DROP_USERS_TABLE);
        db.execSQL(MyAppConstants.DROP_ALBUM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS ALBUMS");
        onCreate(db);
    }

    public SQLiteDatabase getWriteableDB()
    {
        if((null == sqLiteDatabase) || !(sqLiteDatabase.isOpen()))
        {
            sqLiteDatabase = this.getWritableDatabase();
        }
    return sqLiteDatabase;
    }


    @Override
    public void close()
    {
        super.close();
        if(sqLiteDatabase!=null)
        {
            sqLiteDatabase.close();
        }
    }



}
