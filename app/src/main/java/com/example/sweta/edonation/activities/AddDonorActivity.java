package com.example.sweta.edonation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sweta.edonation.R;

public class AddDonorActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    EditText orgnName,donorName,email,location,phone;
    String donorNameString,emailString,locationString,phoneString;
    Long phoneNumber;
    Button addButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);

        initComponent();
        initToolbar();
        initListeners();


    }

    private void initComponent() {

        toolbar = findViewById(R.id.toolBar);
        orgnName=findViewById(R.id.orgName);
        donorName=findViewById(R.id.donorName);
        email=findViewById(R.id.donorEmail);
        location=findViewById(R.id.donorLocation);
        phone=findViewById(R.id.donorPhone);
        addButton=findViewById(R.id.donorAddBtn);

    }

    private void initListeners(){
        addButton.setOnClickListener(this);
    }

    private  void initToolbar(){
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

    public void infoValidation(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        donorNameString=donorName.getText().toString();

        if(donorNameString.equals("")){

            donorName.setError("Donor name cannot be empty");


        }else{

            emailString=email.getText().toString();
            if(emailString.equals("")){
                email.setError("email cannot be empty");
            }else if(emailString.matches(emailPattern)){
                locationString=location.getText().toString();
                if(locationString.equals("")){
                    location.setError("Location cannot be empty");
                }else {
                    phoneString = phone.getText().toString();
                    if (phoneString.equals("")) {
                        phone.setError("Phone number cannot be empty");
                    } else if (phone.length() > 10 && phone.length() < 7) {
                        phone.setError("Enter valid number");

                    }else{
                        phoneNumber=Long.parseLong(phoneString);
                    }
                }

            }else{
                email.setError("Enter valid email");
            }



        }

    }

    @Override
    public void onClick(View v) {
        infoValidation();

    }
}
