package com.example.sweta.edonation.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.sweta.edonation.R;
import com.example.sweta.edonation.pojoclasses.Organization;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class OrganizationLoginActivity extends AppCompatActivity implements View.OnClickListener {


    Toolbar toolbar;
    EditText orgEmail, orgPassword;
    String orgEmailString, orgPasswordString;
    Button logInBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_login);
        initComponent();
        initToolbar();
        setListener();
        firebaseAuth = FirebaseAuth.getInstance();


    }

    private void initComponent() {
        toolbar = findViewById(R.id.toolBar);
        orgEmail = findViewById(R.id.orgEmail);
        orgPassword = findViewById(R.id.orgPassword);
        logInBtn = findViewById(R.id.orgLogIn);

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
            Intent intent = new Intent(OrganizationLoginActivity.this,
                    MainDashboardActivity.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void setListener() {
        logInBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if (v == logInBtn) {

            DatabaseReference dbOrganization = FirebaseDatabase.getInstance().getReference("OrganizationDetails");


            //checked if entered email or password matches or not

            //validation here
            if (orgEmailString.equals("")) {

                orgEmail.setError("Organization email cannot be empty");

            } else if (orgEmailString.matches(emailPattern)) {

                orgPasswordString = orgPassword.getText().toString().trim();

                if (orgPasswordString.equals("")) {
                    orgPassword.setError("Password cannot be empty");
                } else if (orgPasswordString.length() <= 8) {
                    orgPassword.setError("Password cannot be less than eight characters");

                } else if (orgPasswordString.contains("a-zA-Z1-9")) {
                    orgPassword.setError("Enter password containing numbers and alphabets");

                }


                dbOrganization.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot organizationSnapshot : dataSnapshot.getChildren()) {
                                Organization org = organizationSnapshot.
                                        getValue(Organization.class);
                                int status = org.getStatus();
                                orgEmailString = orgEmail.getText().toString().trim();
                                orgPasswordString = orgPassword.getText().toString().trim();

                                logInValidation(status);
                                /*if (status == 1) {

                                    Intent intent = new Intent
                                            (OrganizationLoginActivity.this,
                                                    OrganizationLoginDashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                }*/
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }


        }
    }

    private void logInValidation(int status) {

        if (status == 1) {
            firebaseAuth.signInWithEmailAndPassword(orgEmailString, orgPasswordString)
                    .addOnCompleteListener(this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        //logged in
                                        //LoginDashboard is opened
                                        finish();
                                        Intent intent = new Intent(OrganizationLoginActivity.this,
                                                OrganizationLoginDashboardActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });
        }
        else {
            Toast.makeText(this,"Email is either not registered or not Validated",
                    Toast.LENGTH_LONG).show();
        }
    }
}

