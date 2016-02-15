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

    private final String[] projection=

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

    public AgendaAdapter(Context context, RecyclerView recyclerView){

        this.context=context;
        this.recyclerView=recyclerView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Cursor cursor = context.getContentResolver().query(PresentationsDBstruct.PresentationsEntry.CONTENT_URI,
                )
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
