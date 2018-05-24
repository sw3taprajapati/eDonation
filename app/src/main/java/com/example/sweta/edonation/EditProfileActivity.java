package com.example.sweta.edonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity{
    EditText orgname, orgemail, orglocation, orgphone, orgwebsite, orgpan , orgPassword, describeItems;

    CheckBox check1, check2, check3, check4;

    Button orgregister;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_register);

        initComponent();
        initToolbar();
        disableFields();
    }
    private void disableFields(){
        orgname.setEnabled(false);
        orgemail.setEnabled(false);
        orgPassword.setEnabled(false);
        orgwebsite.setEnabled(false);
        orgpan.setEnabled(false);



    }

    private void initComponent(){

        toolbar=findViewById(R.id.toolBar);
        orgname = findViewById(R.id.orgName);
        orgemail = findViewById(R.id.orgEmail);
        orgPassword = findViewById(R.id.orgPassword);

        orglocation = findViewById(R.id.orgnLocation);
        orgphone = findViewById(R.id.orgnPhone);
        orgwebsite = findViewById(R.id.orgnWebsite);
        orgpan = findViewById(R.id.orgnPan);
        check1 = findViewById(R.id.food_checkbox);
        check2 = findViewById(R.id.clothes_checkbox);
        check3 = findViewById(R.id.books_checkbox);
        check4 = findViewById(R.id.stationery_checkbox);
        describeItems = findViewById(R.id.describeItems);
        orgregister = findViewById(R.id.registerBtn);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(EditProfileActivity.this,OrganizationRegisterActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }



}

