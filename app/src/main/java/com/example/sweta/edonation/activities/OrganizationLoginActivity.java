package com.example.sweta.edonation.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.sweta.edonation.R;
import com.example.sweta.edonation.activities.MainDashboardActivity;
import com.example.sweta.edonation.activities.OrganizationLoginDashboardActivity;
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
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_login);
        checkWifi();




    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);


        return cm.getActiveNetworkInfo() != null;


    }

    private void checkWifi() {

        boolean check = isNetworkConnected();
        if (check == true) {
            initComponent();
            initToolbar();
            setListener();
            firebaseAuth = FirebaseAuth.getInstance();

        } else {

            Toast toast = Toast.makeText(this, "Connect to a network",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();


        }
    }


    private void initComponent() {
        toolbar = findViewById(R.id.toolBar);
        orgEmail = findViewById(R.id.orgEmail);
        orgPassword = findViewById(R.id.orgPassword);
        logInBtn = findViewById(R.id.orgLogIn);
        progressBar = findViewById(R.id.progressBar);

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

        if (!isNetworkConnected()) {
            Toast.makeText(this, "no internet connection", Toast.LENGTH_SHORT).show();
        } else {

            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


            if (v == logInBtn) {

                DatabaseReference dbOrganization = FirebaseDatabase.getInstance().getReference("OrganizationDetails");


                //checked if entered email or password matches or not


                //validation here
                orgEmailString = orgEmail.getText().toString().trim();

                if (orgEmailString.equals("")) {

                    orgEmail.setError("Organization email cannot be empty");

                } else if (orgEmailString.matches(emailPattern)) {

                    orgPasswordString = orgPassword.getText().toString().trim();

                    progressBar.setVisibility(View.VISIBLE);
                    afterValidation();

                }
            }
        }
    }
        private void afterValidation () {

            firebaseAuth.signInWithEmailAndPassword(orgEmailString, orgPasswordString)
                    .addOnCompleteListener(this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {
                                        //logged in
                                        //LoginDashboard is opened
                                        finish();
                                        Intent intent = new Intent(OrganizationLoginActivity.this,
                                                OrganizationLoginDashboardActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Enter valid email id and password",
                                                Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

        }
    }
