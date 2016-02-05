package com.example.dawiduk.conferencevoteapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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


    public static class PlaceholderFragment extends Fragment {
        private ArrayAdapter<String> adapter;

        private static final String ARG_SECTION_NUMBER = "section_number";

        private final static String[][]presentations=

                {
                        {
                                "Keynote",
                                "ASM hacks",
                                "Java 8 Lambdas",
                                "RxJava Essentials",
                                "Tom Tom Challange",
                                "Theory of Api Evolutions",
                                "Indoor drone Programming",
                                "Summary Christmas party"},
                        {
                                "   ",
                                "QRIS to the max",
                                "Buil yourself a drone ",
                                "Who are Customer Care",
                                "   ", "" +
                                "Let's make a deal",
                                "From atlantic to pacific",
                                "    "},
                        {
                                "Command Line is not dead",
                                "Cloud Formation for every engineer",
                                "Functional Thinking ",
                                "    ",
                                "Competive Benchmarking",
                                "MVC is so italian Cusine ", "  "}
                }

                ;

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putStringArray(ARG_SECTION_NUMBER, presentations[sectionNumber-1]);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {



            adapter=new ArrayAdapter<String>(getActivity(),
                    R.layout.presentation_desc,
                    R.id.list_presentation_textview,
                    (getArguments().getStringArray(ARG_SECTION_NUMBER))
                    );

            View rootView = inflater.inflate(R.layout.fragment_agenda, container, false);
            ListView presentationList = (ListView) rootView.findViewById(R.id.presentation_list);
            presentationList.setAdapter(adapter);
            return rootView;
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
                    return "ROOM 1";
                case 1:
                    return "ROOM 2";
                case 2:
                    return "ROOM 3";


            }
            return null;
        }
    }
}