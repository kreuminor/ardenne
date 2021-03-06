package com.prep.ardenne.ardenneprep.menu.homework;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prep.ardenne.ardenneprep.R;
import com.prep.ardenne.ardenneprep.menu.HCentreActivity;
import com.prep.ardenne.ardenneprep.menu.adapter.HomeworkAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Grade1JActivity extends AppCompatActivity {

    private ScrollView scrNotification;
    private TextView txtNotification;
    private SwipeRefreshLayout swipeLayout;
    private CharSequence notification;
    private ProgressBar prog;
    private ListView lstAnnounce;
    public HomeworkAdapter adapter;
    private Elements group;

    private ArrayList<String> text = new ArrayList<>();
    private ArrayList<Integer> sort = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Grade1JActivity.this, HCentreActivity.class);
                startActivity(intent);
                finish();
            }

        });

        getSupportActionBar().setTitle("Grade 1J");


        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);

        scrNotification = (ScrollView) findViewById(R.id.scrNotification);
        txtNotification = (TextView) findViewById(R.id.txtNotification);

        prog = (ProgressBar) findViewById(R.id.prog);
        lstAnnounce = (ListView) findViewById(R.id.lstAnnounce);
        lstAnnounce.setFastScrollEnabled(true);

        if (savedInstanceState == null) {
            swipeLayout.setRefreshing(true);
            new CheckNotifications().execute();
            new AnnounceScrape().execute();
        } else {

            notification = savedInstanceState.getCharSequence("Notification");
            if (notification != null) {
                setNotification();
            }

            text = savedInstanceState.getStringArrayList("Text");
            sort = savedInstanceState.getIntegerArrayList("Sort");

            adapter = new HomeworkAdapter(getApplicationContext());

            for (int i = 0; i < text.size(); i++) {
                if (sort.get(i) == 0) {
                    adapter.addSeparatorItem(text.get(i));
                } else {
                    adapter.addItem(text.get(i));
                }
            }

            setAnnouncements();
        }

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                new AnnounceScrape().execute();
            }
        });
    }

    private void setNotification() {
                runOnUiThread(new Runnable() {
            public void run() {
                txtNotification.setText(notification);

                txtNotification.setVisibility(View.VISIBLE);
                scrNotification.setVisibility(View.VISIBLE);
            }
        });
    }

    private void parseAnnouncements() {

        if (group != null) {
            for (int i = 0; i < group.size(); i++) {

                for (int j = 0; j < group.get(i).select("date").size(); j++) {
                    adapter.addSeparatorItem(group.get(i).select("date").get(j).text());
                    text.add(group.get(i).select("date").get(j).text());
                    sort.add(0);
                }
                for (int k = 0; k < group.get(i).select("announcement").size(); k++) {
                    adapter.addItem(group.get(i).select("announcement").get(k).text());
                    text.add(group.get(i).select("announcement").get(k).text());
                    sort.add(1);
                }
            }
        }
    }

    private void setAnnouncements() {
        lstAnnounce.setAdapter(adapter);

        FadeAnimation f = new FadeAnimation();
        f.start(lstAnnounce, prog);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList("Text", text);
        outState.putIntegerArrayList("Sort", sort);

        outState.putCharSequence("Notification", notification);
    }

    @SuppressWarnings("unused")
    public class CheckNotifications extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            //Check for emergency notifications on the website.
            try {
                final Elements emergNotifBox = Jsoup.connect(getString(R.string.SchoolURL)).get().getElementsByClass("emergNotifBox");
                if (emergNotifBox.hasText()) {
                    //Emergency notification is present.
                    notification = emergNotifBox.text();
                    setNotification();
                }
            } catch (IOException | NullPointerException e) {
                //No notifications. Don't do anything.
            }

            return null;
        }
    }

    private class AnnounceScrape extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            //Scrape the daily announcements into a list.
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    swipeLayout.setEnabled(false);
                }
            });

            adapter = new HomeworkAdapter(getApplicationContext());
            text.clear();
            sort.clear();

            final Document announce;

            try {
                announce = Jsoup.connect("http://drive.google.com/uc?export=downloads&id=0B1VzuZrVMPLNc0ZDTnkxUHN5RFk").get();
                group = announce.select("group");

            } catch (NullPointerException | IOException e) {

                //Add "No Announcements."
                adapter.addItem(getString(R.string.NoAnnouncements));
                text.add(getString(R.string.NoAnnouncements));
                sort.add(1);

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            parseAnnouncements();
            setAnnouncements();
            swipeLayout.setEnabled(true);
            swipeLayout.setRefreshing(false);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Grade1JActivity.this, HCentreActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }
}
