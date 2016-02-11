package com.example.dawiduk.conferencevoteapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by dawiduk on 3-2-16.
 */
public class PresentationsDb extends SQLiteOpenHelper {


    public static final String CONTENT_AUTHORITY = "com.example.dawiduk.conferencevoteapplication";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String TABLE_NAME = "presentations";
    public static final int DATABASE_VERSION =2;

    static final String DATABASE_NAME="presenations.db";


    public PresentationsDb(Context context ){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_PRESENTATIONS_TABLE= "CREATE TABLE "+ PresentationsDBstruct.PresentationsEntry.TABLE_NAME + " ("+
                PresentationsDBstruct.PresentationsEntry._ID + " INTEGER PRIMARY KEY, " +
                PresentationsDBstruct.PresentationsEntry.COLUMN_PRESENTATION +" TEXT UNIQUE  NOT NULL, "+
                PresentationsDBstruct.PresentationsEntry.COLUMN_PRESENTER +" TEXT NOT NULL, "+
                PresentationsDBstruct.PresentationsEntry.COLUMN_ROOM +" TEXT NOT NULL, "+
                PresentationsDBstruct.PresentationsEntry.COLUMN_START+" TEXT NOT NULL, "+
                PresentationsDBstruct.PresentationsEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL";

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + PresentationsDBstruct.PresentationsEntry.TABLE_NAME);
        onCreate(db);

    }
}
