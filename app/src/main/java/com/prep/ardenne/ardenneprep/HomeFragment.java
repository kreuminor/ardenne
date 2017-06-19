package com.prep.ardenne.ardenneprep;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.prep.ardenne.ardenneprep.map.MapsActivity;
import com.prep.ardenne.ardenneprep.menu.AnnounceActivity;
import com.prep.ardenne.ardenneprep.menu.BookActivity;
import com.prep.ardenne.ardenneprep.menu.CalenderActivity;
import com.prep.ardenne.ardenneprep.menu.ExtraActivity;
import com.prep.ardenne.ardenneprep.menu.TimeActivity;
import com.prep.ardenne.ardenneprep.profile.ProfileModelHome;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private GridView lvProfilesm;
    private MyAppAdapter myAppAdapter;
    private ArrayList<ProfileModelHome> profileModelArrayList;

    ImageButton imgEmail;
    ImageButton imgCall;
    ImageButton imgWeb;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home1, container, false);

        imgEmail = (ImageButton) v.findViewById(R.id.imageEmail);
        imgCall = (ImageButton) v.findViewById(R.id.imageCall);
        imgWeb = (ImageButton) v.findViewById(R.id.imageWeb);

        imgEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"colin.holness@gmail.com"});  //Heart Trust 's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "Dear Ardenne Preparatory," + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Send Feedback/Query:"));
            }
        });

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        imgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), WebActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
/*
        profileModelArrayList = new ArrayList<>();
        profileModelArrayList.add(new ProfileModelHome("Announcements", "", R.drawable.announce));
        profileModelArrayList.add(new ProfileModelHome("Events", "", R.drawable.calen));
        profileModelArrayList.add(new ProfileModelHome("Location", "", R.drawable.map));
        profileModelArrayList.add(new ProfileModelHome("Booklists", "", R.drawable.book));
        profileModelArrayList.add(new ProfileModelHome("Timetables", "", R.drawable.timetable));
        profileModelArrayList.add(new ProfileModelHome("Extra Curricular", "", R.drawable.sports));

        lvProfilesm = (GridView) v.findViewById(R.id.grid);
        myAppAdapter = new MyAppAdapter(profileModelArrayList, getActivity());
        lvProfilesm.setAdapter(myAppAdapter);
        lvProfilesm.setOnItemClickListener(new ItemList());
*/
        return v;
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
                LayoutInflater inflater = getLayoutInflater(null);
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
            Glide.with(getActivity()).load(profileList.get(position).getProfilePic()).into(viewHolder.profilePic);

            return rowView;
        }

    }

    private class ItemList implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            if (i == 0) {

                Intent intent = new Intent(getActivity(), AnnounceActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
            else if (i == 1) {

                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
            else if (i == 2) {

                Toast.makeText(getContext(), "Location", Toast.LENGTH_SHORT).show();
            }

            else if (i == 3) {

                Intent intent = new Intent(getActivity(), BookActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
            else if (i == 4) {

                Intent intent = new Intent(getActivity(), TimeActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
            else if (i == 5) {

                Intent intent = new Intent(getActivity(), ExtraActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        }

    }

}