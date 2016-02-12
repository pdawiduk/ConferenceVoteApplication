package com.example.dawiduk.conferencevoteapplication.database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dawiduk on 3-2-16.
 */
public class PresentationsDBstruct {

    public static final class PresentationsEntry implements BaseColumns{
        public static final String CONTENT_AUTHORITY = "com.example.dawiduk.conferencevoteapplication";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
        public static final String PATH_PRESENTATION = "presentation";


        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRESENTATION;

        public static Uri buildPresentationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PRESENTATION).build();

        public static final String TABLE_PRESENTATION_ID = "id";
        public static final String TABLE_NAME = "presentations";
        public static final String COLUMN_PRESENTER = "presenter";
        public static final String COLUMN_START = "start";
        public static final String COLUMN_DESCRIPTION = "desc";
        public static final String COLUMN_ROOM = "room";
        public static final String COLUMN_PRESENTATION ="presentation";

    }
}
