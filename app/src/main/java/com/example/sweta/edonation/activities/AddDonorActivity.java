package com.example.sweta.edonation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.sweta.edonation.R;

public class AddDonorActivity extends AppCompatActivity {

    Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);

        initComponent();
        initToolbar();

    }

    private void initComponent() {

        toolbar = findViewById(R.id.toolBar);

    }

    private  void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Donor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddDonorActivity.this,
                        OrganizationDashboardActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
