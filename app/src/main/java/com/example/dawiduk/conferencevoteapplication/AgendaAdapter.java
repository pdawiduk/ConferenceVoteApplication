package com.example.dawiduk.conferencevoteapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dawiduk.conferencevoteapplication.database.PresentationsDBstruct;

/**
 * Created by dawiduk on 15-2-16.
 */
public class AgendaAdapter extends RecyclerView.Adapter {

    private Context context;
    private RecyclerView recyclerView;
    private int sectionNumber;

    private static final String[] projection ={

            PresentationsDBstruct.PresentationsEntry.COLUMN_START,
            PresentationsDBstruct.PresentationsEntry.COLUMN_PRESENTATION,
            PresentationsDBstruct.PresentationsEntry.COLUMN_PRESENTER,
            PresentationsDBstruct.PresentationsEntry.COLUMN_ROOM,

    };


    Cursor cursor = context.getContentResolver().query(PresentationsDBstruct.PresentationsEntry.CONTENT_URI,
                                                               )

    private class ViewHolder extends RecyclerView.ViewHolder {
        public TextView startTime;
        public TextView presentator;
        public TextView room;


        public ViewHolder(View view) {
            super(view);
            startTime = (TextView) view.findViewById(R.id.start_time);
            presentator= (TextView) view.findViewById(R.id.presenter);
            room=(TextView) view.findViewById(R.id.room);
        }
    }

    public AgendaAdapter(Context context, RecyclerView recyclerView,int sectionNumber){

        this.context=context;
        this.recyclerView=recyclerView;
        this.sectionNumber=sectionNumber;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            switch(sectionNumber){
                case 1:
                    cursor=context.getContentResolver().query();
                    break;
                case 2:
                    cursor=context.getContentResolver().query();
                    break;
                case 3:
                    cursor=context.getContentResolver().query();
                    break;
            }

    }

    @Override
    public int getItemCount() {
        return cursor.getCount() ;
    }
}
