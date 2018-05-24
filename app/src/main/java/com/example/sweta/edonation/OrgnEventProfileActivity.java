package com.example.sweta.edonation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class OrgnEventProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button eventButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgn_event_profile);

        initComponent();
        setListeners();
        initToolbar();
    }


    private void initComponent() {

        toolbar = findViewById(R.id.toolBar);
        eventButton =findViewById(R.id.eventsBtn);


    }
    private void setListeners(){
        eventButton.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        Intent intent=new Intent(OrgnEventProfileActivity.this,OrganizationEventActivity.class);
        startActivity(intent);



    }
}
