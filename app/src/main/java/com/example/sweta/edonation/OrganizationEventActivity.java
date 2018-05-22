package com.example.sweta.edonation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.support.v7.widget.Toolbar;

public class OrganizationEventActivity extends AppCompatActivity {
        Toolbar toolbar;
        CheckBox c1,c2,c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_event);
        initComponent();
        initToolbar();

    }

    private void initComponent() {

        toolbar = findViewById(R.id.toolBar);
        c1 =findViewById(R.id.c1);
        c2 =findViewById(R.id.c2);
        c3 =findViewById(R.id.c3);


    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Oganization's Event");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


}
