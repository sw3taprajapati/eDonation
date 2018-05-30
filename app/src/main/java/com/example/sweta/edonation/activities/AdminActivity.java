package com.example.sweta.edonation.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.sweta.edonation.pojoclasses.Organization;
import com.example.sweta.edonation.adaptersandviewholders.OrganizationAdapter;
import com.example.sweta.edonation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button btnRefresh;
    RecyclerView recyclerView;
    OrganizationAdapter adapter;
    List<Organization> organizationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initComponent();
        initToolbar();
        initRecyclerView();
        setListener();
    }

    private void initComponent() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        btnRefresh=findViewById(R.id.refreshRecyclerView);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Admin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setListener(){
       // btnRefresh.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(
                        AdminActivity.this,MainDashboardActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void initRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        organizationList = new ArrayList<>();


       DatabaseReference dbOrganization = FirebaseDatabase.getInstance().
                getReference("OrganizationDetails");
        dbOrganization.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method executes when successful

                if (dataSnapshot.exists()) {
                    for (DataSnapshot organizationSnapshot : dataSnapshot.getChildren()) {
                        Organization org = organizationSnapshot.getValue(Organization.class);

                        int status=org.getStatus();
                        if(status==0) {
                            organizationList.add(org);
                        }

                    }
                    adapter = new OrganizationAdapter(AdminActivity.this, organizationList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                //this method executes when error

            }
        });
    }

    @Override
    public void onClick(View v) {

            Intent intent=new Intent(AdminActivity.this,AdminActivity.class);
            startActivity(intent);
            finish();
    }
}