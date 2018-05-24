package com.example.sweta.edonation;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    Toolbar toolbar;

    EditText nameOrg, emailOrg, locationOrg, phoneOrg, websiteOrg, panOrg;
    String orgNameString, orgEmailString, orgLocationString, orgWebsiteString;
    int orgPhoneInt, orgPanInt,status=0;
    Button registerOrg;



    DatabaseReference databaseOrganization;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponent();
        initListeners();
        initToolbar();

        databaseOrganization= FirebaseDatabase.getInstance().getReference("OrganizationDetails");

    }

    private void initListeners() {
        registerOrg.setOnClickListener(this);

    }

    private void initComponent() {

        toolbar = findViewById(R.id.toolBar);
        nameOrg = findViewById(R.id.orgnName);
        emailOrg = findViewById(R.id.orgnEmail);
        locationOrg = findViewById(R.id.orgnLocation);
        phoneOrg = findViewById(R.id.orgnPhone);
        websiteOrg = findViewById(R.id.orgnWebsite);
        panOrg = findViewById(R.id.orgnPan);
        registerOrg = findViewById(R.id.registerBtn);


    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url);
        if (m.matches())
            return true;
        else
            return false;
    }

    @Override
    public void onClick(View v) {

        String phone;
        String panNo;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        orgNameString = nameOrg.getText().toString().trim();

        if (orgNameString.equals("")) {
            // Toast.makeText(this, "Organization name cannot be empty", Toast.LENGTH_SHORT).show();
            nameOrg.setError("Organization name cannot be empty");
            //orgemail.setEnabled(false);
            //orgemail.setClickable(false);
            //orgemail.isEnabled(false);


        } else {

            orgEmailString = emailOrg.getText().toString().trim();
            if (orgEmailString.equals("")) {
                // Toast.makeText(this, "Organization email cannot be empty", Toast.LENGTH_SHORT).show();
                emailOrg.setError("Organization email cannot be empty");

            } else if (orgEmailString.matches(emailPattern)) {
                orgLocationString = locationOrg.getText().toString().trim();
                if (orgLocationString.equals("")) {



                    //Toast.makeText(this, "Organization location cannot be empty", Toast.LENGTH_SHORT).show();
                    locationOrg.setError("Organization location cannot be empty");

                } else {
                    phone = phoneOrg.getText().toString().trim();

                    if (phone.equals("")) {
                        //Toast.makeText(this, "Organization phone cannot be empty", Toast.LENGTH_SHORT).show();
                        phoneOrg.setError("Organization phone cannot be empty");

                    } else if (phone.length() != 10 && phone.length() != 7) {

                        //Toast.makeText(this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
                        phoneOrg.setError("Enter valid number");

                    } else {
                        try {
                            orgPhoneInt = Integer.parseInt(phoneOrg.getText().toString());
                        } catch (Exception e) {

                        }


                        orgWebsiteString = websiteOrg.getText().toString().trim();
                        boolean flag = isValidUrl(orgWebsiteString);
                        if (orgWebsiteString.equals("")) {
                            //Toast.makeText(this, "Organization website cannot be empty", Toast.LENGTH_SHORT).show();
                            websiteOrg.setError("Organization website cannot be empty");
                        } else if (flag == false) {
                            websiteOrg.setError("Invalid website");

                        } else {
                            panNo = panOrg.getText().toString();
                            if (panNo.equals("")) {
                                panOrg.setError("Organization pan cannot be empty");
                                //Toast.makeText(this, "Organization PAN No. cannot be empty", Toast.LENGTH_SHORT).show();
                            } else {


                                orgPanInt = Integer.parseInt(panOrg.getText().toString());

                                String orgId = databaseOrganization.push().getKey();
                                Organization org = new Organization(orgId, orgNameString, orgEmailString, orgLocationString, orgPhoneInt, orgWebsiteString, orgPanInt, status);
                                databaseOrganization.child(orgId).setValue(org);


                                orgPanInt = Integer.parseInt(panOrg.getText().toString());
                                Intent intent = new Intent(RegisterActivity.this, OnVerifyActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        }
                    }
                }

            } else{
                emailOrg.setError("enter valid email");
                //Toast.makeText(this, "Enter valid email address", Toast.LENGTH_SHORT).show();


            }


        }


    }

}
