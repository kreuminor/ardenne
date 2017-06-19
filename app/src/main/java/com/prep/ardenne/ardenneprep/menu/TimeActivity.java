package com.prep.ardenne.ardenneprep.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.prep.ardenne.ardenneprep.MainActivity;
import com.prep.ardenne.ardenneprep.R;
import com.prep.ardenne.ardenneprep.booklist.BookKActivity;
import com.prep.ardenne.ardenneprep.login.ProfileActivity;
import com.prep.ardenne.ardenneprep.profile.ProfileModelHomeT;
import com.prep.ardenne.ardenneprep.time.Time1Activity;
import com.prep.ardenne.ardenneprep.time.Time2Activity;
import com.prep.ardenne.ardenneprep.time.Time3Activity;
import com.prep.ardenne.ardenneprep.time.Time4Activity;
import com.prep.ardenne.ardenneprep.time.Time5Activity;
import com.prep.ardenne.ardenneprep.time.Time6Activity;
import com.prep.ardenne.ardenneprep.time.TimeKActivity;

import java.util.ArrayList;

public class TimeActivity extends AppCompatActivity {

    private ListView lvProfilesm;
    private MyAppAdapter myAppAdapter;
    private ArrayList<ProfileModelHomeT> profileModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profileModelArrayList = new ArrayList<>();
        profileModelArrayList.add(new ProfileModelHomeT("Kindergarden ", "", R.drawable.ardene1));
        profileModelArrayList.add(new ProfileModelHomeT("Grade 1 ", "", R.drawable.ardene1));
        profileModelArrayList.add(new ProfileModelHomeT("Grade 2 ", "", R.drawable.ardene1));
        profileModelArrayList.add(new ProfileModelHomeT("Grade 3 ", "", R.drawable.ardene1));
        profileModelArrayList.add(new ProfileModelHomeT("Grade 4 ", "", R.drawable.ardene1));
        profileModelArrayList.add(new ProfileModelHomeT("Grade 5 ", "", R.drawable.ardene1));
        profileModelArrayList.add(new ProfileModelHomeT("Grade 6 ", "", R.drawable.ardene1));

        lvProfilesm = (ListView) findViewById(R.id.lblist);
        myAppAdapter = new MyAppAdapter(profileModelArrayList, getApplicationContext());
        lvProfilesm.setAdapter(myAppAdapter);
        lvProfilesm.setOnItemClickListener(new ItemList());

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }

        });

        getSupportActionBar().setTitle("TimeTables");

    }
    public class MyAppAdapter extends BaseAdapter {

        public class ViewHolder {
            TextView username, country;
            ImageView profilePic;

        }

        public ArrayList<ProfileModelHomeT> profileList;

        public Context context;


        private MyAppAdapter(ArrayList<ProfileModelHomeT> apps, Context context) {
            this.profileList = apps;
            this.context = context;

        }

        @Override
        public int getCount() {
            return profileList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;
            MyAppAdapter.ViewHolder viewHolder;

            if (rowView == null) {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_single, parent, false);

                viewHolder = new MyAppAdapter.ViewHolder();
                viewHolder.profilePic = (ImageView) rowView.findViewById(R.id.imgProfile);
                viewHolder.username = (TextView) rowView.findViewById(R.id.txtUsername);
                viewHolder.country = (TextView) rowView.findViewById(R.id.txtCountry);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (MyAppAdapter.ViewHolder) convertView.getTag();
            }

            viewHolder.username.setText(profileList.get(position).getUsername() + "");
            viewHolder.country.setText(profileList.get(position).getCountry() + "");
            Glide.with(getApplicationContext()).load(profileList.get(position).getProfilePic()).into(viewHolder.profilePic);

            return rowView;
        }
    }
        @Override
        public void onBackPressed() {
        Intent intent = new Intent(TimeActivity.this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }

    private class ItemList implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            if (i == 0) {

                Intent intent = new Intent(TimeActivity.this, TimeKActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                finish();
            }
            else if (i == 1) {

                Intent intent = new Intent(TimeActivity.this, Time1Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
            else if (i == 2) {

                Intent intent = new Intent(TimeActivity.this, Time2Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
            else if (i == 3) {

                Intent intent = new Intent(TimeActivity.this, Time3Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
            else if (i == 4) {

                Intent intent = new Intent(TimeActivity.this, Time4Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
            else if (i == 5) {

                Intent intent = new Intent(TimeActivity.this, Time5Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
            else if (i == 6) {

                Intent intent = new Intent(TimeActivity.this, Time6Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }

    }
}
