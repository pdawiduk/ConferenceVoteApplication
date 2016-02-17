package com.example.dawiduk.conferencevoteapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dawiduk.conferencevoteapplication.database.PresentationsDBstruct;
import com.example.dawiduk.conferencevoteapplication.database.PresentationsDb;

/**
 * Created by dawiduk on 15-2-16.
 */
public class AgendaAdapter extends CursorAdapter {

    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int sectionNumber;

    private static final int COLUMN_START=0;
    private static final int INDEX_COLUMN_PRESENTATION=1;
    private static final int INDEX_COLUMN_PRESENTER=2;
    private static final int COLUMN_ROOM=3;

    private static final String[] projection = {
            PresentationsDBstruct.PresentationsEntry.COLUMN_START,
            PresentationsDBstruct.PresentationsEntry.COLUMN_PRESENTATION,
            PresentationsDBstruct.PresentationsEntry.COLUMN_PRESENTER,
            PresentationsDBstruct.PresentationsEntry.COLUMN_ROOM,
    };


    Cursor cursor ;

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_agenda,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder =new ViewHolder(view);
        ((ViewHolder) holder).startTime.setText(Double.toString(cursor.getDouble(COLUMN_START)));
        ((ViewHolder) holder).presentation.setText(cursor.getString(INDEX_COLUMN_PRESENTATION));
        ((ViewHolder) holder).presentator.setText(cursor.getString(INDEX_COLUMN_PRESENTER));
        ((ViewHolder) holder).room.setText(cursor.getString(COLUMN_ROOM));
    }


    private class ViewHolder  {
        public TextView startTime;
        public TextView presentator;
        public TextView room;
        public TextView presentation;


        public ViewHolder(View view) {

            startTime = (TextView) view.findViewById(R.id.start_time);
            presentator= (TextView) view.findViewById(R.id.presenter);
            room=(TextView) view.findViewById(R.id.room);
            presentation=(TextView) view.findViewById(R.id.presentation);
        }
    }

    public AgendaAdapter(Context context, Cursor c, int flags){
        super(context,c,flags);
        this.context=context;
    }

//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//            switch(sectionNumber){
//                case 1:
//                    cursor=context.getContentResolver().query(PresentationsDb.BASE_CONTENT_URI,
//                            projection,
//                            PresentationsDBstruct.PresentationsEntry.COLUMN_ROOM +"="+ "aula",
//                            null,
//                            null);
//                    if(cursor.moveToFirst()){
//                        do{
//                            ((ViewHolder) holder).startTime.setText(Double.toString(cursor.getDouble(COLUMN_START)));
//                            ((ViewHolder) holder).presentation.setText(cursor.getString(INDEX_COLUMN_PRESENTATION));
//                            ((ViewHolder) holder).presentator.setText(cursor.getString(INDEX_COLUMN_PRESENTER));
//                            ((ViewHolder) holder).room.setText(cursor.getString(COLUMN_ROOM));
//
//                        }
//                        while (cursor.moveToNext());
//                    }
//                    break;
//                case 2:
//                    cursor=context.getContentResolver().query(PresentationsDb.BASE_CONTENT_URI,
//                            projection,
//                            PresentationsDBstruct.PresentationsEntry.COLUMN_ROOM +"="+ "room a",
//                            null,
//                            null);
//                    if(cursor.moveToFirst()){
//                        do{
//                            ((ViewHolder) holder).startTime.setText(Double.toString(cursor.getDouble(COLUMN_START)));
//                            ((ViewHolder) holder).presentation.setText(cursor.getString(INDEX_COLUMN_PRESENTATION));
//                            ((ViewHolder) holder).presentator.setText(cursor.getString(INDEX_COLUMN_PRESENTER));
//                            ((ViewHolder) holder).room.setText(cursor.getString(COLUMN_ROOM));
//                        }
//                        while (cursor.moveToNext());
//                    }
//                    break;
//                case 3:
//                    cursor=context.getContentResolver().query(PresentationsDb.BASE_CONTENT_URI,
//                            projection,
//                            PresentationsDBstruct.PresentationsEntry.COLUMN_ROOM +"="+ "room c",
//                            null,
//                            null);
//                    if(cursor.moveToFirst()){
//                        do{
//                            ((ViewHolder) holder).startTime.setText(Double.toString(cursor.getDouble(COLUMN_START)));
//                            ((ViewHolder) holder).presentation.setText(cursor.getString(INDEX_COLUMN_PRESENTATION));
//                            ((ViewHolder) holder).presentator.setText(cursor.getString(INDEX_COLUMN_PRESENTER));
//                            ((ViewHolder) holder).room.setText(cursor.getString(COLUMN_ROOM));
//                        }
//                        while (cursor.moveToNext());
//                    }
//                    break;
//            }
//
//    }


}
