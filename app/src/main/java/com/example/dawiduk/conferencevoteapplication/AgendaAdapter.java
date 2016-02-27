package com.example.dawiduk.conferencevoteapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dawiduk.conferencevoteapplication.database.PresentationsDBstruct;

import java.io.IOException;

/**
 * Created by dawiduk on 15-2-16.
 */
public class AgendaAdapter extends CursorAdapter {
    private static final String LOG_TAG = AgendaAdapter.class.getSimpleName();
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int sectionNumber;
    private boolean actualPresentation = true;

    private static final int COLUMN_START = 4;
    private static final int INDEX_COLUMN_PRESENTATION = 2;
    private static final int INDEX_COLUMN_PRESENTER = 3;
    private static final int COLUMN_ROOM = 5;

    private Integer hour;

    private static final String[] projection = {
            PresentationsDBstruct.PresentationsEntry.COLUMN_START,
            PresentationsDBstruct.PresentationsEntry.COLUMN_PRESENTATION,
            PresentationsDBstruct.PresentationsEntry.COLUMN_PRESENTER,
            PresentationsDBstruct.PresentationsEntry.COLUMN_ROOM,
    };


    Cursor cursor;

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        int layoutId = -1;


            hour = 10;


        Log.d(LOG_TAG, "hour = "+ hour);
        if (cursor.getString(COLUMN_START).equals(hour.toString()) & actualPresentation) {
            layoutId = R.layout.actual_presentation;
            actualPresentation = false;
            //cursor.moveToPrevious();
            return LayoutInflater.from(context).inflate(layoutId, parent, false);

//        if (cursor.getString(COLUMN_START).equals(hour.toString()) & !actualPresentation) {
//            layoutId = R.layout.presentation_desc;
          //  return LayoutInflater.from(context).inflate(layoutId, parent, false);
        } else {layoutId = R.layout.presentation_desc;
            return LayoutInflater.from(context).inflate(layoutId, parent, false);}



    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        ViewHolder holder = new ViewHolder(view);
        ((ViewHolder) holder).startTime.setText(cursor.getString(COLUMN_START));
        ((ViewHolder) holder).presentation.setText(cursor.getString(INDEX_COLUMN_PRESENTATION));
        ((ViewHolder) holder).presentator.setText(cursor.getString(INDEX_COLUMN_PRESENTER));
        ((ViewHolder) holder).room.setText(cursor.getString(COLUMN_ROOM));
    }


    private class ViewHolder {
        public TextView startTime;
        public TextView presentator;
        public TextView room;
        public TextView presentation;


        public ViewHolder(View view) {

            startTime = (TextView) view.findViewById(R.id.start_time);
            presentator = (TextView) view.findViewById(R.id.presenter);
            room = (TextView) view.findViewById(R.id.room);
            presentation = (TextView) view.findViewById(R.id.presentation);
        }
    }

    public AgendaAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.context = context;
    }


}
