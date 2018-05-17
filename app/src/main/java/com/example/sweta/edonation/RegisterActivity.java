package com.example.sweta.edonation;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    EditText orgname, orgemail, orglocation, orgphone, orgwebsite, orgpan;
    String orgnameString, orgemailString, orglocationString, orgwebsiteString;
    int orgphoneInt, orgpanInt, length;
    Button orgregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponent();
        initListeners();
        initToolbar();


    }


    private void initListeners() {
        orgregister.setOnClickListener(this);
        orgphone.setOnClickListener(this);

    }

    private void initComponent() {

        toolbar = findViewById(R.id.toolBar);
        orgname = findViewById(R.id.orgn_name);
        orgemail = findViewById(R.id.orgn_email);
        orglocation = findViewById(R.id.orgn_location);
        orgphone = findViewById(R.id.orgn_phone);
        orgwebsite = findViewById(R.id.orgn_website);
        orgpan = findViewById(R.id.orgn_pan);
        orgregister = findViewById(R.id.register_btn);


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

    @Override
    public void onClick(View v) {
        String phone;
        String panno;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        orgnameString = orgname.getText().toString().trim();

        if (orgnameString.equals("")) {
            Toast.makeText(this, "Organization name cannot be empty", Toast.LENGTH_SHORT).show();

        } else {
            orgemailString = orgemail.getText().toString().trim();
            if (orgemailString.equals("")) {
                Toast.makeText(this, "Organization email cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else if (orgemailString.matches(emailPattern)) {
                orglocationString = orglocation.getText().toString().trim();
                if (orglocationString.equals("")) {
                    Toast.makeText(this, "Organization location cannot be empty", Toast.LENGTH_SHORT).show();

                } else {
                    phone = orgphone.getText().toString().trim();

                    if (phone.equals("")) {
                        Toast.makeText(this, "Organization phone cannot be empty", Toast.LENGTH_SHORT).show();

                    }else if(phone.length()!=10 && phone.length()!=7){

                        Toast.makeText(this, "Enter valid phone number", Toast.LENGTH_SHORT).show();

                    } else {
                        try {
                            orgphoneInt = Integer.parseInt(orgphone.getText().toString());
                        } catch (Exception e) {

                        }

                        orgwebsiteString = orgwebsite.getText().toString().trim();
                        if (orgwebsiteString.equals("")) {
                            Toast.makeText(this, "Organization website cannot be empty", Toast.LENGTH_SHORT).show();

                        } else {
                            panno = orgpan.getText().toString();
                            if (panno.equals("")) {
                                Toast.makeText(this, "Organization PAN No. cannot be empty", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
            else{
                Toast.makeText(this, "Enter valid email address", Toast.LENGTH_SHORT).show();

            }
        }

    }
}