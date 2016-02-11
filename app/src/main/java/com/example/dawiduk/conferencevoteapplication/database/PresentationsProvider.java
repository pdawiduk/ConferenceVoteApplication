package com.example.dawiduk.conferencevoteapplication.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.dawiduk.conferencevoteapplication.Presentation;

/**
 * Created by dawiduk on 3-2-16.
 */
public class PresentationsProvider extends ContentProvider {

    private static final UriMatcher uriMatcher=buildUriMatcher();
    private PresentationsDb db;

    static final int PRESENTATION =333;
    static final int PRESENTATION_ID =666;



    static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PresentationsDb.CONTENT_AUTHORITY;

        matcher.addURI(authority,PresentationsDb.);
        matcher.addURI();

        return matcher;

    }

    @Override
    public boolean onCreate() {

        db=new PresentationsDb(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

            Cursor retCursor;
            int match = uriMatcher.match(uri);

        switch(match){

            case PRESENTATION:{
                retCursor=db.getReadableDatabase().query(
                        PresentationsDb.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                default:
                    throw new UnsupportedOperationException("no matched URI " + match + "Unkonown URI: " + uri);
            }
        }
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int help = uriMatcher.match(uri);
        switch (help){
            case PRESENTATION:
                return;
            default:
                throw new UnsupportedOperationException("Unkown URI: "+ uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {

        final SQLiteDatabase db= this.db.getWritableDatabase();
        final int help= uriMatcher.match(uri);

        switch(help){

            case PRESENTATION:{
                db.beginTransaction();
                int returncount=0;
                for (ContentValues value : values){
                    long _id = db.insert(PresentationsDBstruct.PresentationsEntry.TABLE_NAME,null, value);
                    if (_id != -1 ) returncount++;
                }
                db.setTransactionSuccessful();
                db.endTransaction();
                getContext().getContentResolver().notifyChange(uri, null);
                return returncount;

            }
            default: return super.bulkInsert(uri, values);
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db= this.db.getWritableDatabase();
        final int help = uriMatcher.match(uri);
        int rowsDeleted;

        if(selection== null) selection="1";

        switch(help) {
            case PRESENTATION:
                rowsDeleted = db.delete(PresentationsDBstruct.PresentationsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PRESENTATION_ID:
                rowsDeleted=db.delete(PresentationsDBstruct.PresentationsEntry)
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

    }
}
