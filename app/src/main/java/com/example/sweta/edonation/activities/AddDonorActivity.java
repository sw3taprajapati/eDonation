package com.example.sweta.edonation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sweta.edonation.R;
import com.example.sweta.edonation.pojoclasses.CurrentlyLooking;
import com.example.sweta.edonation.pojoclasses.DonorInfoPojo;
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

public class AddDonorActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    EditText orgnName, donorName, email, location, phone;
    String donorNameString, emailString, locationString, phoneString;
    Long phoneNumber;
    Button addButton;
    private boolean foodBoolean;
    private boolean clothesBoolean;
    private boolean booksBoolean;
    private boolean check;
    private boolean stationeryBoolean;
    DatabaseReference databaseOrganization;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    FirebaseUser user;
    String name;

    private FirebaseDatabase firebaseDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);

        initComponent();
        initToolbar();
        initListeners();
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();


    }

    private void initComponent() {

        toolbar = findViewById(R.id.toolBar);
        orgnName = findViewById(R.id.orgNameDonor);
        donorName = findViewById(R.id.donorName);
        email = findViewById(R.id.donorEmail);
        location = findViewById(R.id.donorLocation);
        phone = findViewById(R.id.donorPhone);
        addButton = findViewById(R.id.donorAddBtn);
        accessInformation();

    }

    private void initListeners() {
        addButton.setOnClickListener(this);
    }

    private void initToolbar() {
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

    @Override
    public void onClick(View v) {
        infoValidation();

    }

    public void infoValidation() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        donorNameString = donorName.getText().toString();

        if (donorNameString.equals("")) {

            donorName.setError("Donor name cannot be empty");


        } else {

            emailString = email.getText().toString();
            if (emailString.equals("")) {
                email.setError("email cannot be empty");
            } else if (emailString.matches(emailPattern)) {
                locationString = location.getText().toString();
                if (locationString.equals("")) {
                    location.setError("Location cannot be empty");
                } else {
                    phoneString = phone.getText().toString();
                    if (phoneString.equals("")) {
                        phone.setError("Phone number cannot be empty");
                    } else if (phone.length() > 10 && phone.length() < 7) {
                        phone.setError("Enter valid number");

                    } else {
                        phoneNumber = Long.parseLong(phoneString);

//                        firebaseAuth.createUserWithEmailAndPassword(orgemailString, orgPasswordString)
//                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                        if (task.isSuccessful()) {
//                                            //registered
//                                        }
//                                    }
//                                });


                        accessInformation();
                        databaseOrganization = FirebaseDatabase.getInstance().
                                getReference("DonorDetails");

                        String donorId = databaseOrganization.push().getKey();
                        CurrentlyLooking currentlyLooking = new CurrentlyLooking(foodBoolean,
                                clothesBoolean, booksBoolean, stationeryBoolean);
                        DonorInfoPojo donor = new DonorInfoPojo(donorId, name, donorNameString, emailString, locationString, phoneNumber, currentlyLooking);
                        databaseOrganization.child(donorId).setValue(donor);
                        Intent intent = new Intent(
                                AddDonorActivity.this,
                                OrganizationDashboardActivity.class);
                        startActivity(intent);
                        finish();


                    }
                }

            } else {
                email.setError("Enter valid email");
            }


        }

    }

    public void accessInformation() {


        databaseReference = firebaseDatabase.
                getReference("OrganizationDetails");

        final String email;
        email = user.getEmail();

        databaseOrganization.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method executes when successful

                if (dataSnapshot.exists()) {
                    for (DataSnapshot organizationSnapshot : dataSnapshot.getChildren()) {
                        Organization org = organizationSnapshot.getValue(Organization.class);
                        String emailFromDB = org.getOrgEmailID();

                        if (email.equals(emailFromDB)) {

                            try {
                                name = org.getOrgFullName();

                                //setting to the edit text
                                orgnName.setText(name);

                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }
}