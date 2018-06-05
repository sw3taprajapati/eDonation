package com.example.sweta.edonation.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sweta.edonation.R;
import com.google.firebase.auth.FirebaseAuth;

public class DonorDetailsActivity extends AppCompatActivity {

    TextView name;
    TextView location;
    TextView currentReqDetail;
    TextView descriptionDetail;
    TextView emailDetail;
    Button emailBtn;
    Button callBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_detail);
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
            initComponents();
            initToolbar();
            getIntents();

        } else {

            Toast toast = Toast.makeText(this, "Connect to a network",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();


        }
    }

    private void getIntents() {
        if (getIntent().hasExtra("donorName") && getIntent().hasExtra("donorLocation")
                && getIntent().hasExtra("donorEmail")
                && getIntent().hasExtra("currentDonation")
                && getIntent().hasExtra("description")
                && getIntent().hasExtra("phone")) {

            String donorName = getIntent().getStringExtra("donorName");
            String donorLocation = getIntent().getStringExtra("donorLocation");
            String email = getIntent().getStringExtra("donorEmail");
            String currentDonation = getIntent().getStringExtra("currentDonation");
            String description = getIntent().getStringExtra("description");
            Long phone = getIntent().getLongExtra("phone", 0);

            setDetails(donorName, donorLocation, email, currentDonation, description, phone);
        }
    }

    private void setDetails(String donorName, String donorLocation, final String email,
                            String currentDonation, String description, final long phone) {
        name.setText(donorName);
        location.setText(donorLocation);
        emailDetail.setText(email);
        currentReqDetail.setText(currentDonation);

        if(description.equals("")){
            descriptionDetail.setText("We aren't accepting any donation at this time. " +
                    "Thank you!!!!!");
        }else{
            descriptionDetail.setText("Donation received for: " + description);
        }


        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + email)); // only email apps should handle this

                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        final String phoneNo = String.valueOf(phone);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNo.length() == 10) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse("tel: " + String.valueOf(phone)));

                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                    }
                } else if (phoneNo.length() >= 7 && phoneNo.length() < 10) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse("tel: 01" + String.valueOf(phone)));

                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    private void initComponents() {
        name = findViewById(R.id.donorNameDetail);
        location = findViewById(R.id.locationDetail);
        currentReqDetail = findViewById(R.id.donationDetail);
        descriptionDetail = findViewById(R.id.descriptionDetail);
        emailBtn = findViewById(R.id.btnEmailDetail);
        callBtn = findViewById(R.id.callBtnDetail);
        emailDetail = findViewById(R.id.emailDetail);
        toolbar = findViewById(R.id.toolbar);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Donor Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                Intent intent = new Intent(DonorDetailsActivity.this,
                        MainDashboardActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(DonorDetailsActivity.this,
                        OrganizationDashboardActivity.class);
                startActivity(intent);
                finish();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
