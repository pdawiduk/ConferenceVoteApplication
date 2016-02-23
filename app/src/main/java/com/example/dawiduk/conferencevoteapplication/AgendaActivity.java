package com.example.dawiduk.conferencevoteapplication;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dawiduk.conferencevoteapplication.database.PresentationsDBstruct;
import com.example.dawiduk.conferencevoteapplication.database.PresentationsDb;

public class AgendaActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agenda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
        private AgendaAdapter adapter;

        private static final int AULA=1;
        private static final int ROOM_A=2;
        private static final int ROOM_C=3;

        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final int DETAIL_LOADER =0;

        private static final int COL_PRESENTER=2;
        private static final int COL_START=4;
        private static final int COL_PRESENTATION=1;
        private static final int COL_ROOM=3;


        private TextView presenter;
        private TextView room;
        private TextView start;
        private TextView presentation;
        private TextView vote;




        private final static String[] COLUMN_PRESENTATIONS = {
                PresentationsDBstruct.PresentationsEntry._ID,
                PresentationsDBstruct.PresentationsEntry.TABLE_PRESENTATION_ID,
                PresentationsDBstruct.PresentationsEntry.COLUMN_PRESENTATION,
                PresentationsDBstruct.PresentationsEntry.COLUMN_PRESENTER,
                PresentationsDBstruct.PresentationsEntry.COLUMN_START,
                PresentationsDBstruct.PresentationsEntry.COLUMN_ROOM
        };

        public PlaceholderFragment() {
        }




        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Bundle args = getArguments();
            View rootView = inflater.inflate(R.layout.fragment_agenda, container, false);
            ListView presentationsListView = (ListView) rootView.findViewById(R.id.presentationListView);
            SwipeRefreshLayout swipeRefreshLayout =(SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);

            adapter = new AgendaAdapter(this.getActivity(),null,0 );
            presentationsListView.setAdapter(adapter);
            return rootView;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            getLoaderManager().initLoader(DETAIL_LOADER, null, this);
            super.onActivityCreated(savedInstanceState);
        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {

            final Uri THIS_CONTENT_URI =
                    PresentationsDBstruct.PresentationsEntry.BASE_CONTENT_URI.buildUpon().appendPath(PresentationsDBstruct.PresentationsEntry.PATH_PRESENTATION).build();
            Bundle sectionArgs=getArguments();


            int sectionNumber = sectionArgs.getInt(ARG_SECTION_NUMBER);
          //  Rooms rooms =Rooms(sectionArgs);
            String sortOrder = PresentationsDBstruct.PresentationsEntry.COLUMN_START + " ASC";
            switch (sectionNumber) {
                case AULA:
                    return new CursorLoader(
                            getActivity(),
                            THIS_CONTENT_URI,
                            COLUMN_PRESENTATIONS,
                            PresentationsDBstruct.PresentationsEntry.COLUMN_ROOM + " = " + '"'+ "aula"+'"',
                            null,
                            sortOrder);

                case ROOM_A:

                    return new CursorLoader(
                            getActivity(),
                            THIS_CONTENT_URI,
                            COLUMN_PRESENTATIONS,
                            PresentationsDBstruct.PresentationsEntry.COLUMN_ROOM + " = "+ '"' + "room a"+ '"',
                            null,
                            sortOrder);

                case ROOM_C:
                    return new CursorLoader(
                            getActivity(),
                            THIS_CONTENT_URI,
                            COLUMN_PRESENTATIONS,
                            PresentationsDBstruct.PresentationsEntry.COLUMN_ROOM + " = "+ '"' + "room c"+ '"',
                            null,
                            sortOrder);

                default:  throw new IllegalArgumentException();

            }


        }


        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

           adapter.swapCursor(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);

        }
    }


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {

        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "aula";
            case 1:
                return "room a";
            case 2:
                return "room c";
        }
        return null;
    }

}
}
