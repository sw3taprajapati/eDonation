
package com.example.sweta.edonation.activities;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sweta.edonation.R;
import com.example.sweta.edonation.R;
import com.example.sweta.edonation.pojoclasses.Organization;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText orgName, orgEmail, orgLocation, orgPhone, orgWebsite, orgPan, orgPassword, describeItems;
    private String orgNameString, orgEmailString, orgLocationString, orgWebsiteString,
            orgPasswordString, orgDescribeItemsString;
    private String emailFromDB, orgId;
    private int orgPhoneInt, orgPanInt;
    private CheckBox checkFood, checkClothes, checkBooks, checkStationery;

    private Button orgRegister;
    private Toolbar toolbar;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    private boolean foodBoolean;
    private boolean clothesBoolean;
    private boolean booksBoolean;
    private boolean stationeryBoolean;
    private DatabaseReference databaseOrganization;
    private String currentlyLooking = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_register);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseOrganization = firebaseDatabase.
                getReference("OrganizationDetails");
        user = FirebaseAuth.getInstance().getCurrentUser();
        checkwifi();
        initComponent();
        initToolbar();
        initListeners();
        disableFields();
        accessInformation();


    }

    private void disableFields() {
        orgName.setEnabled(false);
        orgName.setFocusable(false);


        orgPassword.setEnabled(false);
        orgPassword.setFocusable(false);

        orgEmail.setEnabled(false);
        orgPassword.setFocusable(false);


        orgWebsite.setEnabled(false);
        orgWebsite.setFocusable(false);

        orgPan.setEnabled(false);
        orgPan.setFocusable(false);

        orgRegister.setText("UPDATE");


    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);


        return cm.getActiveNetworkInfo() != null;


    }

    private void checkwifi() {

        boolean check = isNetworkConnected();
        if (check == true) {
            initComponent();

        } else {

            Toast toast = Toast.makeText(this, "Connect to a network",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            initComponent();
        }

    }

    private void initComponent() {

        toolbar = findViewById(R.id.toolBar);
        orgName = findViewById(R.id.orgName);
        orgEmail = findViewById(R.id.orgEmail);
        orgPassword = findViewById(R.id.orgPassword);
        orgLocation = findViewById(R.id.orgnLocation);
        orgPhone = findViewById(R.id.orgnPhone);
        orgWebsite = findViewById(R.id.orgnWebsite);
        orgPan = findViewById(R.id.orgnPan);
        checkFood = findViewById(R.id.food_checkbox);
        checkClothes = findViewById(R.id.clothes_checkbox);
        checkBooks = findViewById(R.id.books_checkbox);
        checkStationery = findViewById(R.id.stationery_checkbox);
        describeItems = findViewById(R.id.describeItems);
        orgRegister = findViewById(R.id.registerBtn);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //Do what you want here
            Intent intent=new Intent(EditProfileActivity.this,OrganizationDashboardActivity.class);
            startActivity(intent);
            finish();
            //moveTaskToBack(true);
            // return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(EditProfileActivity.this,
                        OrganizationDashboardActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void initListeners() {
        orgRegister.setOnClickListener(this);
    }

    public void accessInformation() {

        final String email = user.getEmail();

        databaseOrganization.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method executes when successful

                if (dataSnapshot.exists()) {
                    for (DataSnapshot organizationSnapshot : dataSnapshot.getChildren()) {
                        Organization org = organizationSnapshot.getValue(Organization.class);

                        emailFromDB = org.getOrgEmailID();

                        if (email.equals(emailFromDB)) {
                            orgId = org.getOrgId();
                            String name = org.getOrgFullName();
                            String password = org.getOrgPassword();
                            String location = org.getOrgLocation();
                            String phoneNo = String.valueOf(org.getOrgPhone());
                            String website = org.getOrgWebsite();
                            String panNo = String.valueOf(org.getOrgPan());
                            //setting to the edit text
                            orgName.setText(name);
                            orgEmail.setText(email);
                            orgPassword.setText(password);
                            orgLocation.setText(location);
                            orgPhone.setText(phoneNo);
                            orgWebsite.setText(website);
                            orgPan.setText(panNo);
                        }

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //this method executes when error
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == orgRegister) {

            String phone;
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            orgPasswordString = orgPassword.getText().toString().trim();

            if (orgPasswordString.equals("")) {
                orgPassword.setError("Password cannot be empty");
            } else if (orgPasswordString.length() <= 8) {
                orgPassword.setError("Password cannot be less than eight characters");

            } else if (orgPasswordString.contains("a-zA-Z1-9")) {
                orgPassword.setError("Enter pasword containing numbers and alphabets");

            } else {
                orgLocationString = orgLocation.getText().toString().trim();
                if (orgLocationString.equals("")) {

                    orgLocation.setError("Organization location cannot be empty");
                } else {
                    phone = orgPhone.getText().toString().trim();

                    if (phone.equals("")) {

                        orgPhone.setError("Organization phone cannot be empty");

                    } else if (phone.length() != 10 && phone.length() != 7) {


                        orgPhone.setError("Enter valid number");

                    } else {
                        try {
                            orgPhoneInt = Integer.parseInt(orgPhone.getText().toString());
                        } catch (Exception e) {

                        }

                        if (checkFood.isChecked()) {
                            foodBoolean = true;
                            //= "," + "Stationery"Log.i("food", currentlyLooking);
                        }

                        if (checkBooks.isChecked()) {
                            booksBoolean= true;
                            //Log.i("clothes", currentlyLooking);
                        }

                        if (checkClothes.isChecked()) {
                            clothesBoolean =true;
                        }

                        if (checkStationery.isChecked()) {
                            stationeryBoolean =true;
                        }

                        orgDescribeItemsString = describeItems.getText().toString();


                        databaseOrganization.child(orgId).child("orgPassword").setValue(orgPasswordString);
                        databaseOrganization.child(orgId).child("orgLocation").setValue(orgLocationString);
                        databaseOrganization.child(orgId).child("orgPhone").setValue(orgPhoneInt);
                        databaseOrganization.child(orgId).child("describeItems").setValue(orgDescribeItemsString);
                        databaseOrganization.child(orgId).child("currentlyLooking").child("food").setValue(foodBoolean);
                        databaseOrganization.child(orgId).child("currentlyLooking").child("clothes").setValue(clothesBoolean);
                        databaseOrganization.child(orgId).child("currentlyLooking").child("books").setValue(booksBoolean);
                        databaseOrganization.child(orgId).child("currentlyLooking").child("stationery").setValue(stationeryBoolean);

                        databaseOrganization.child(orgId).child("describeItems").setValue(orgDescribeItemsString);

                    }
                }
            }
        }
        Toast.makeText(this, "Information Updated",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(EditProfileActivity.this,
                OrganizationDashboardActivity.class);
        startActivity(intent);
        finish();

    }
}