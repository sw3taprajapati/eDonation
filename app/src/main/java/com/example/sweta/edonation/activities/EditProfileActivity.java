package com.example.sweta.edonation.activities;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
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
    EditText orgName, orgEmail, orgLocation, orgPhone, orgWebsite, orgPan, orgPassword, describeItems;


    String orgNameString, orgEmailString, orgLocationString, orgWebsiteString,
            orgPasswordString, orgDescribeItemsString;
    String emailFromDB, orgId;
    int orgPhoneInt, orgPanInt;
    CheckBox check1, check2, check3, check4;
    DatabaseReference reference;
    Button orgRegister;
    Toolbar toolbar;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    List<Organization> organizationList;
    DatabaseReference databaseOrganization;
    String currentlyLooking = "";
    int status = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_register);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseOrganization = FirebaseDatabase.getInstance().
                getReference("OrganizationDetails");
        user = FirebaseAuth.getInstance().getCurrentUser();
        initComponent();
        initToolbar();
        initListeners();
        disableFields();
        accessInformation();



    }


    private void disableFields() {
        orgName.setEnabled(false);
        orgName.setFocusable(false);
        orgName.setCursorVisible(false);
        orgEmail.setEnabled(false);
        orgWebsite.setEnabled(false);
        orgPan.setEnabled(false);
        orgRegister.setText("UPDATE");


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
        check1 = findViewById(R.id.food_checkbox);
        check2 = findViewById(R.id.clothes_checkbox);
        check3 = findViewById(R.id.books_checkbox);
        check4 = findViewById(R.id.stationery_checkbox);
        describeItems = findViewById(R.id.describeItems);
        orgRegister = findViewById(R.id.registerBtn);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(EditProfileActivity.this,
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

            orgPassword = findViewById(R.id.orgPassword);


            orgLocation = findViewById(R.id.orgnLocation);
            orgPhone = findViewById(R.id.orgnPhone);

            check1 = findViewById(R.id.food_checkbox);
            check2 = findViewById(R.id.clothes_checkbox);
            check3 = findViewById(R.id.books_checkbox);
            check4 = findViewById(R.id.stationery_checkbox);
            describeItems = findViewById(R.id.describeItems);
            orgRegister = findViewById(R.id.registerBtn);

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



                        if (check1.isChecked()) {
                            currentlyLooking = "Food";
                            //Log.i("food", currentlyLooking);


                        }

                        if (check2.isChecked()) {
                            currentlyLooking += "," + "Clothes";
                            //Log.i("clothes", currentlyLooking);
                        }

                        if (check3.isChecked()) {
                            currentlyLooking += "," + "Books";
                        }

                        if (check4.isChecked()) {
                            currentlyLooking += "," + "Stationery";
                        }

                        orgDescribeItemsString = describeItems.getText().toString();


                        databaseOrganization.child(orgId).child("orgPassword").setValue(orgPasswordString);
                        databaseOrganization.child(orgId).child("orgLocation").setValue(orgLocationString);
                        databaseOrganization.child(orgId).child("orgPhone").setValue(phone);
                        databaseOrganization.child(orgId).child("describeItems").setValue(orgDescribeItemsString);
                        databaseOrganization.child(orgId).child("currentlyLooking").setValue(currentlyLooking);
                        databaseOrganization.child(orgId).child("describeItems").setValue(orgDescribeItemsString);




                    }
                }
            }


        }
        Toast.makeText(this, "Information Updated",
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(EditProfileActivity.this,
                OrganizationLoginDashboardActivity.class);
        startActivity(intent);
        finish();
    }
}