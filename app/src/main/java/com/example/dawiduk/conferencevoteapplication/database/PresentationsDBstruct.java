package com.example.dawiduk.conferencevoteapplication.database;

import android.provider.BaseColumns;

/**
 * Created by dawiduk on 3-2-16.
 */
public class PresentationsDBstruct {

    public static final class PresentationsEntry implements BaseColumns{


        public static final String TABLE_NAME = "presentations";
        public static final String COLUMN_PRESENTER = "presenter";
        public static final String COLUMN_START = "start";
        public static final String COLUMN_DESCRIPTION = "desc";
        public static final String COLUMN_ROOM = "room";
        public static final String COLUMN_PRESENTATION ="presentation";

    }
}
