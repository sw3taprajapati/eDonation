package com.example.sweta.edonation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DonorRegisterActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText nameDonar, emailDonar, locationDonar, phoneDonar;
    String donorNameString, donorEmailString, donorLocationString;
    int donorPhoneInt, status = 0;
    Button registerDonor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_register);

        initComponent();
//        initListeners();
        initToolbar();

    }


    private void initComponent() {

        toolbar = findViewById(R.id.toolBar);
        nameDonar = findViewById(R.id.donorName);
        emailDonar = findViewById(R.id.donarEmail);
        locationDonar = findViewById(R.id.donorLocation);
        phoneDonar = findViewById(R.id.donorPhone);
        registerDonor = findViewById(R.id.registerBtn);


    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register Donar Account");
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
