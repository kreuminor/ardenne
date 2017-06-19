package com.prep.ardenne.ardenneprep.menu;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.prep.ardenne.ardenneprep.R;
import com.prep.ardenne.ardenneprep.login.ProfileActivity;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
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
                Intent intent = new Intent(AdminActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }

        });

        getSupportActionBar().setTitle("Administration");

        ProgressBar prog = (ProgressBar) findViewById(R.id.prog);
        ListView lst_admin = (ListView) findViewById(R.id.lst_admin);

        String[] names = getResources().getStringArray(R.array.admin_names);

        ListAdapter adminAdapter = new OfficeAdapter
                (AdminActivity.this, 0, names);

        lst_admin.setAdapter(adminAdapter);

        FadeAnimation f = new FadeAnimation();
        f.start(lst_admin, prog);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }
}
