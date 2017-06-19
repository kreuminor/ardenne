package com.prep.ardenne.ardenneprep.menu.staff;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.prep.ardenne.ardenneprep.R;
import com.prep.ardenne.ardenneprep.login.ProfileActivity;


import java.util.Arrays;
import java.util.List;

import static com.prep.ardenne.ardenneprep.R.id.lst_staff;

public class StaffActivity extends AppCompatActivity {

    static TextView staffSearch;
    private StaffAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        ProgressBar prog = (ProgressBar) findViewById(R.id.prog);
        ListView lst_staff = (ListView) findViewById(R.id.lst_staff);
        staffSearch = (TextView) findViewById(R.id.staffSearch);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<String> staffNames = Arrays.asList(getResources().getStringArray(R.array.staff_names));
        List<String> staffEmails = Arrays.asList(getResources().getStringArray(R.array.staff_emails));
        List<String> staffPhones = Arrays.asList(getResources().getStringArray(R.array.staff_phones));
        adapter = new StaffAdapter(getApplicationContext(),
                staffNames, staffEmails, staffPhones);
        lst_staff.setAdapter(adapter);


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
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                finish();
            }

        });

        getSupportActionBar().setTitle("Staff Members");

        FadeAnimation f = new FadeAnimation();
        f.start(lst_staff, prog);

        staffSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        lst_staff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                staffSearch.clearFocus();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }
}
