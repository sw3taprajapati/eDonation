package com.example.sweta.edonation.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

//import com.example.sweta.edonation.R;
import com.example.sweta.edonation.R;
import com.example.sweta.edonation.pojoclasses.Organization;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity{
    EditText orgname, orgemail, orglocation, orgphone, orgwebsite, orgpan , orgPassword, describeItems;
    String name,email,website,pan;

    CheckBox check1, check2, check3, check4;
    DatabaseReference reference;
    Button orgregister;
    Toolbar toolbar;
    FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_register);
        mAuth = FirebaseAuth.getInstance();
        //accessInformation();
        initComponent();
        initToolbar();
        disableFields();


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser user) {

        //hideProgressDialog();

        if (user != null) {

        }

    }
    private void accessInformation(){



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();
           // website = user.getWebsite();

            // Check if user's email is verified
           // boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
        orgname.setText(name);




    }


    private void disableFields(){
        orgname.setEnabled(false);
        orgemail.setEnabled(false);

        orgwebsite.setEnabled(false);
        orgpan.setEnabled(false);
        orgregister.setText("Done");



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
                        OrganizationLoginDashboardActivity.class);
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

