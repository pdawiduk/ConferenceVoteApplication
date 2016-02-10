package com.example.dawiduk.conferencevoteapplication.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.dawiduk.conferencevoteapplication.Presentation;

/**
 * Created by dawiduk on 3-2-16.
 */
public class PresentationsProvider extends ContentProvider {


    private static final String DB_NAME= "presentations.db";
    private static final String TABLE_NAMTE = "presentations";
    private static final String Query="";
    private  PresentationsDb dbHelper;
    private  UriMatcher uriMatcher;
    static final int PRESENTATION = 666;
    static final int PRESENTATION_ID =333;


    static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority= PresentationsDb.CONTENT_AUTHORITY;

        matcher.addURI(authority,PresentationsDb.PATH_PRESENTATIONS,PRESENTATION);
        matcher.addURI(authority, PresentationsDb.PATH_PRESENTATIONS + "/#", PRESENTATION_ID);
        return matcher;

    }
    @Override
    public boolean onCreate() {
        dbHelper=new PresentationsDb(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCurosor;

        int help= uriMatcher.match(uri);

        if (help== PRESENTATION){
            retCurosor=dbHelper.getWritableDatabase().query(
                    PresentationsDb.DATABASE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

            switch (uriMatcher){

                case PRESENTATION :
                    return PresentationsDb.CONTENT_AUTHORITY;

                default :
                    throw new UnsupportedOperationException("Unknown Uri "+ uri);
            }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        final SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();

        Uri returnUri=null;

        final int match = uriMatcher.match(uri);



            if (match== PRESENTATION){
                sqLiteDatabase.insert(PresentationsDb.DATABASE_NAME,null,values);
            }




    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);

        int rowsDeleted;
        if (selection==null) selection = "1";
        if (match == PRESENTATION){
            rowsDeleted = db.delete(PresentationsDb.DATABASE_NAME,selection,
                    selectionArgs);
        }
        else throw new UnsupportedOperationException("Unknown URi "+ uri);

        if (rowsDeleted !=0) getContext().getContentResolver().notifyChange(uri,null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
