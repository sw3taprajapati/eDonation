package com.example.sweta.edonation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    EditText orgNameTxt;
    EditText orgEmailTxt;
    EditText locationTxt;
    EditText phoneTxt;
    EditText websiteTxt;
    EditText panNumTxt;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponent();
        initToolbar();
        setListerner();
    }

    private void initComponent(){
        toolbar=findViewById(R.id.toolBar);
        orgNameTxt=findViewById(R.id.orgName);
        orgEmailTxt=findViewById(R.id.orgEmail);
        locationTxt=findViewById(R.id.location);
        phoneTxt=findViewById(R.id.phoneNum);
        websiteTxt=findViewById(R.id.website);
        panNumTxt=findViewById(R.id.panNum);
        btnRegister=findViewById(R.id.registerBtn);
    }

    private void setListerner(){
        btnRegister.setOnClickListener(this);
    }


    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
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

    }
}
