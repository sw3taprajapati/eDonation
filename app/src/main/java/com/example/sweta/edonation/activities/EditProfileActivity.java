package com.example.sweta.edonation.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.sweta.edonation.OrganizationRegisterActivity;
import com.example.sweta.edonation.R;

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

    private void getIntents() {
        if (getIntent().hasExtra("orgName") && getIntent().hasExtra("orgEmail")
                && getIntent().hasExtra("orgPassword") && getIntent().hasExtra("orgLocation")
                && getIntent().hasExtra("currentRequirement") && getIntent().hasExtra("description")
                && getIntent().hasExtra("website") && getIntent().hasExtra("phone")) {

            String orgName = getIntent().getStringExtra("orgName");
            String orgEmail=getIntent().getStringExtra("orgEmail");
            String password=getIntent().getStringExtra("orgPassword");
            //String orgLocation = getIntent().getStringExtra("orgLocation");
            String currentReq = getIntent().getStringExtra("currentRequirement");
            //String description = getIntent().getStringExtra("description");
            //String website = getIntent().getStringExtra("website");
            //int phone = getIntent().getIntExtra("phone",0);

            setDetails(orgName,orgEmail, currentReq,password);
        }
    }
    private void setDetails(String orgName, String orgEmail,
                            String currentReq, String password) {
        orgname.setText(orgName);
        //location.setText(orgLocation);
        orgname.setText(orgEmail);
        //descriptionDetail.setText(description);
//        websiteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(String.valueOf(website)));
//                startActivity(intent);
//            }
//        });
//        callBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_CALL);
//                intent.setData(Uri.parse("tel:" + String.valueOf(phone)));
//                try {
//                    if (ActivityCompat.checkSelfPermission(EditProfileActivity.this,
//                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
//                    startActivity(intent);
//                } catch (android.content.ActivityNotFoundException ex) {
//
//                }
//            }
//        });
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
                Intent intent=new Intent(EditProfileActivity.this,
                        OrganizationRegisterActivity.class);
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

