package com.example.sweta.edonation.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.sweta.edonation.R;
import android.widget.Toast;



public class OrganizationDetailActivity extends AppCompatActivity {

    TextView name;
    TextView location;
    TextView currentReqDetail;
    TextView descriptionDetail;
    TextView emailDetail;
    Button websiteBtn;
    Button callBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_detail);

        initComponents();
        initToolbar();
        getIntents();
    }

    private void getIntents() {
        if (getIntent().hasExtra("orgName") && getIntent().hasExtra("orgLocation")
                && getIntent().hasExtra("orgEmail")
                && getIntent().hasExtra("currentRequirement")
                && getIntent().hasExtra("description")
                && getIntent().hasExtra("website") && getIntent().hasExtra("phone")) {

            String orgName = getIntent().getStringExtra("orgName");
            String orgLocation = getIntent().getStringExtra("orgLocation");
            String email = getIntent().getStringExtra("orgEmail");
            String currentReq = getIntent().getStringExtra("currentRequirement");
            String description = getIntent().getStringExtra("description");
            String website = getIntent().getStringExtra("website");
            int phone = getIntent().getIntExtra("phone", 0);

            setDetails(orgName, orgLocation, email, currentReq, description,
                    website, phone);
        }
    }

    private void setDetails(String orgName, String orgLocation, String email,
                            String currentReq, String description,
                            final String website, final int phone) {
        name.setText(orgName);
        location.setText(orgLocation);
        emailDetail.setText(email);
        currentReqDetail.setText(currentReq);
        descriptionDetail.setText("We are currently looking for "+description);
        websiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://" + website));
                startActivity(intent);
            }
        });
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel: "+ String.valueOf(phone)));
                try {
                    startActivity(intent);
                } catch (Exception e) {
                }
            }
        });
    }
    private void initComponents(){
        name=findViewById(R.id.orgNameDetail);
        location=findViewById(R.id.locationDetail);
        currentReqDetail=findViewById(R.id.needDetail);
        descriptionDetail=findViewById(R.id.descriptionDetail);
        websiteBtn=findViewById(R.id.btnWebsiteDetail);
        callBtn=findViewById(R.id.callBtnDetail);
        emailDetail=findViewById(R.id.emailDetail);
        toolbar=findViewById(R.id.toolbar);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Organization Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(OrganizationDetailActivity.this,
                    MainDashboardActivity.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
