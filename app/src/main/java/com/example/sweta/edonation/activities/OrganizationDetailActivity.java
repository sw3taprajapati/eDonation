package com.example.sweta.edonation.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sweta.edonation.R;

public class OrganizationDetailActivity extends AppCompatActivity {

    TextView name;
    TextView location;
    TextView currentReqDetail;
    TextView descriptionDetail;
    Button websiteBtn;
    Button callBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_detail);
        initComponents();
        getIntents();
    }

    private void getIntents() {
        if (getIntent().hasExtra("orgName") && getIntent().hasExtra("oorgLocation")
                && getIntent().hasExtra("orgName") && getIntent().hasExtra("orgLocation")
                && getIntent().hasExtra("currentRequirement") && getIntent().hasExtra("description")
                && getIntent().hasExtra("website") && getIntent().hasExtra("phone")) {

            String orgName = getIntent().getStringExtra("orgName");
            String orgLocation = getIntent().getStringExtra("orgLocation");
            String currentReq = getIntent().getStringExtra("currentRequirement");
            String description = getIntent().getStringExtra("description");
            String website = getIntent().getStringExtra("website");
            int phone = getIntent().getIntExtra("phone",0);

            setDetails(orgName,orgLocation, currentReq,description,
                website,phone);
        }
    }

    private void setDetails(String orgName, String orgLocation,
                            String currentReq, String description,
                            final String website, final int phone) {
        name.setText(orgName);
        location.setText(orgLocation);
        currentReqDetail.setText(currentReq);
        descriptionDetail.setText(description);
        websiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(String.valueOf(website)));
                startActivity(intent);
            }
        });
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + String.valueOf(phone)));
                try {
                    if (ActivityCompat.checkSelfPermission(OrganizationDetailActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {

                }
            }
        });
    }
    private void initComponents(){
        name=findViewById(R.id.orgNameDetail);
        location=findViewById(R.id.locationDetail);
        currentReqDetail=findViewById(R.id.needDetail);
        descriptionDetail=findViewById(R.id.describeItemsDetail);
        websiteBtn=findViewById(R.id.btnWebsiteDetail);
        callBtn=findViewById(R.id.callBtnDetail);
    }

}
