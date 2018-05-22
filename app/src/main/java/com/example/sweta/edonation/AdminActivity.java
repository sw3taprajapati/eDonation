package com.example.sweta.edonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    OrganizationsAdapter adapter;
    List<Organization> organizationList;
    Button approveBtn;
    TextView panNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        /*initComponent();
        initToolbar();
    */
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        organizationList = new ArrayList<>();


        DatabaseReference dbOrganization = FirebaseDatabase.getInstance().getReference("OrganizationDetails");
        dbOrganization.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method executes when successful

                if (dataSnapshot.exists()) {
                    for (DataSnapshot organizationSnapshot : dataSnapshot.getChildren()) {
                        Organization org = organizationSnapshot.getValue(Organization.class);
                        organizationList.add(org);
                    }

                    adapter = new OrganizationsAdapter(AdminActivity.this, organizationList);
                    recyclerView.setAdapter(adapter);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                //this method executes when error

            }
        });
    }


    private void initComponent() {
        toolbar = findViewById(R.id.toolBar);
        approveBtn = findViewById(R.id.approveBtn);
        panNo = findViewById(R.id.panNo);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Admin");
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

    private void setListener() {
        approveBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == approveBtn) {

            Intent intent=new Intent(AdminActivity.this,OrganizationRegisterActivity.class);
            startActivity(intent);
            finish();

            }
        }
    }







