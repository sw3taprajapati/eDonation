package com.example.sweta.edonation.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sweta.edonation.R;
import com.example.sweta.edonation.pojoclasses.CurrentlyLooking;
import com.example.sweta.edonation.pojoclasses.Donor;
import com.example.sweta.edonation.pojoclasses.Organization;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddDonorActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText orgnName, donorName, email, location, phone;
    private String donorNameString, emailString, locationString, phoneString;
    private Long phoneNumber;
    private Button addButton;
    private boolean foodBoolean;
    private boolean clothesBoolean;
    private boolean booksBoolean;
    private CheckBox checkBoxFood;
    private CheckBox checkboxClothes;
    private CheckBox checkboxBooks;
    private CheckBox checkboxStationery;
    private boolean stationeryBoolean;
    private DatabaseReference databaseOrganization;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private String emailNavigation;
    private FirebaseUser user;
    private String name;
    private EditText describedItems;

    private FirebaseDatabase firebaseDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);

        initComponent();
        accessInformation();
        initToolbar();
        initListeners();



    }

    private void initComponent() {

        toolbar = findViewById(R.id.toolBar);
        orgnName = findViewById(R.id.orgNameDonor);
        donorName = findViewById(R.id.donorName);
        email = findViewById(R.id.donorEmail);
        location = findViewById(R.id.donorLocation);
        phone = findViewById(R.id.donorPhone);
        addButton = findViewById(R.id.donorAddBtn);
        checkboxBooks=findViewById(R.id.books_checkbox);
        checkboxClothes=findViewById(R.id.clothes_checkbox);
        checkBoxFood=findViewById(R.id.food_checkbox);
        checkboxStationery=findViewById(R.id.stationery_checkbox);
        describedItems=findViewById(R.id.describeItems);
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
                        if (checkBoxFood.isChecked()) {
                            foodBoolean = true;
                            //= "," + "Stationery"Log.i("food", currentlyLooking);
                        }

                        if (checkboxBooks.isChecked()) {
                            booksBoolean = true;
                            //Log.i("clothes", currentlyLooking);
                        }

                        if (checkboxClothes.isChecked()) {
                            clothesBoolean = true;
                        }

                        if (checkboxStationery.isChecked()) {
                            stationeryBoolean = true;
                        }

                        String orgDescribeItemsString = describedItems.getText().toString();
                        databaseOrganization = FirebaseDatabase.getInstance().
                                getReference("DonorDetails");

                        String donorId = databaseOrganization.push().getKey();
                        CurrentlyLooking currentlyLooking = new CurrentlyLooking(foodBoolean,
                                clothesBoolean, booksBoolean, stationeryBoolean);
                        Donor donor = new Donor(donorId, name, donorNameString, emailString,
                                locationString, phoneNumber,
                                currentlyLooking,orgDescribeItemsString);
                        databaseOrganization.child(donorId).setValue(donor);

                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:" + email)); // only email apps should handle this

                        intent.putExtra(Intent.EXTRA_SUBJECT, "Donation Received");
                        intent.putExtra(Intent.EXTRA_TEXT, "Thank you for your donation. Your " +
                                "donation has been sent");
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }

                    }
                }

            } else {
                email.setError("Enter valid email");
            }


        }

    }

    public void accessInformation() {

        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.
                getReference("OrganizationDetails");

        emailNavigation = user.getEmail();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method executes when successful

                if (dataSnapshot.exists()) {
                    for (DataSnapshot organizationSnapshot : dataSnapshot.getChildren()) {
                        Organization org = organizationSnapshot.getValue(Organization.class);
                        String emailFromDB = org.getOrgEmailID();
                        if (emailNavigation.equals(emailFromDB)) {

                            try {
                                name = org.getOrgFullName();
                                //setting to the edit text
                                orgnName.setText(name);
                                orgnName.setEnabled(false);

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