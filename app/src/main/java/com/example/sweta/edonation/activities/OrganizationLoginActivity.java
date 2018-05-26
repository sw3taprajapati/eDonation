package com.example.sweta.edonation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.example.sweta.edonation.activities.MainDashboardActivity;

public class OrganizationLoginActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button orgSignin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_login);
        initComponent();
        initToolbar();
        setListener();

    }

    private void initComponent() {
        toolbar = findViewById(R.id.toolBar);
        orgSignin = findViewById(R.id.orgSigninBtn);

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login As Organization");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(OrganizationLoginActivity.this, MainDashboardActivity.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void setListener(){
        orgSignin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        if (v == orgSignin) {

            Intent intent = new Intent(OrganizationLoginActivity.this,
                    MainDashboardActivity.class);


            startActivity(intent);
            finish();
        }


    }
}
