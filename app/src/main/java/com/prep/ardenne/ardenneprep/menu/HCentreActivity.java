package com.prep.ardenne.ardenneprep.menu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.prep.ardenne.ardenneprep.R;
import com.prep.ardenne.ardenneprep.login.ProfileActivity;
import com.prep.ardenne.ardenneprep.menu.homework.Grade1CActivity;
import com.prep.ardenne.ardenneprep.menu.homework.Grade1JActivity;
import com.prep.ardenne.ardenneprep.menu.homework.Kinder1BActivity;
import com.prep.ardenne.ardenneprep.menu.homework.Kinder1CActivity;
import com.prep.ardenne.ardenneprep.profile.ProfileModelHome;

import java.util.ArrayList;

public class HCentreActivity extends AppCompatActivity {

    private GridView lvProfilesm;
    private MyAppAdapter myAppAdapter;
    private ArrayList<ProfileModelHome> profileModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcentre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profileModelArrayList = new ArrayList<>();
        profileModelArrayList.add(new ProfileModelHome("Kindergarden 1A", "", R.drawable.book));
        profileModelArrayList.add(new ProfileModelHome("Kindergarden 1B", "", R.drawable.book));
        profileModelArrayList.add(new ProfileModelHome("Grade 1C", "", R.drawable.book));
        profileModelArrayList.add(new ProfileModelHome("Grade 1J", "", R.drawable.book));
        profileModelArrayList.add(new ProfileModelHome("Grade 2M", "", R.drawable.book));
        profileModelArrayList.add(new ProfileModelHome("Grade 2J", "", R.drawable.book));
        profileModelArrayList.add(new ProfileModelHome("Grade 3F", "", R.drawable.book));
        profileModelArrayList.add(new ProfileModelHome("Grade 3P", "", R.drawable.book));
        profileModelArrayList.add(new ProfileModelHome("Grade 4C", "", R.drawable.book));
        profileModelArrayList.add(new ProfileModelHome("Grade 4J", "", R.drawable.book));
        profileModelArrayList.add(new ProfileModelHome("Grade 5M", "", R.drawable.book));
        profileModelArrayList.add(new ProfileModelHome("Grade 6J", "", R.drawable.book));

        lvProfilesm = (GridView) findViewById(R.id.grid);
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
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                finish();
            }

        });

        getSupportActionBar().setTitle("Homework Centre");

        final TextView tx = (TextView) findViewById(R.id.textView);
        tx.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tx.setSelected(true);
        tx.setSingleLine(true);
        tx.setText("Welcome to our homework centre                              Only the best is done here...                   ");
    }

    public class MyAppAdapter extends BaseAdapter {

        public class ViewHolder {
            TextView username, country;
            ImageView profilePic;

        }

        public ArrayList<ProfileModelHome> profileList;

        public Context context;


        private MyAppAdapter(ArrayList<ProfileModelHome> apps, Context context) {
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
                rowView = inflater.inflate(R.layout.grid_single, parent, false);

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

    private class ItemList implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            if (i == 0) {

                Intent intent = new Intent(getApplicationContext(), Kinder1BActivity.class);
                startActivity(intent);
                finish();

            }
            else if (i == 1) {

                Intent intent = new Intent(getApplicationContext(), Kinder1CActivity.class);
                startActivity(intent);
                finish();

            }
            else if (i == 2) {

                Intent intent = new Intent(getApplicationContext(), Grade1CActivity.class);
                startActivity(intent);
                finish();
            }

            else if (i == 3) {

                Intent intent = new Intent(getApplicationContext(), Grade1JActivity.class);
                startActivity(intent);
                finish();

            }
            else if (i == 4) {

                Intent intent = new Intent(getApplicationContext(), Grade1JActivity.class);
                startActivity(intent);
                finish();

            }
            else if (i == 5) {

                Intent intent = new Intent(getApplicationContext(), Kinder1CActivity.class);
                startActivity(intent);
                finish();

            }
            else if (i == 6) {

                Intent intent = new Intent(getApplicationContext(), Kinder1CActivity.class);
                startActivity(intent);
                finish();

            }
            else if (i == 7) {

                Intent intent = new Intent(getApplicationContext(), Grade1JActivity.class);
                startActivity(intent);
                finish();
            }

            else if (i == 8) {

                Intent intent = new Intent(getApplicationContext(), Kinder1CActivity.class);
                startActivity(intent);
                finish();

            }
            else if (i == 9) {

                Intent intent = new Intent(getApplicationContext(), Kinder1CActivity.class);
                startActivity(intent);
                finish();

            }
            else if (i == 10) {

                Intent intent = new Intent(getApplicationContext(), Kinder1CActivity.class);
                startActivity(intent);
                finish();

            }
            else if (i == 11) {

                Intent intent = new Intent(getApplicationContext(), Kinder1CActivity.class);
                startActivity(intent);
                finish();

            }
        }

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }
}
