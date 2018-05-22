package com.example.sweta.edonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity{

    Toolbar toolbar;
    RecyclerView recyclerView;
    OrganizationsAdapter adapter;
    List<Organization> organizationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


      /*  initComponent();
        initToolbar(); */

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        organizationList = new ArrayList<>();


        DatabaseReference dbOrganization = FirebaseDatabase.getInstance().getReference("OrganizationDetails");
        dbOrganization.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method executes when successful

                if(dataSnapshot.exists()){
                    for(DataSnapshot organizationSnapshot : dataSnapshot.getChildren()){
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

    private void initComponent(){
        toolbar=findViewById(R.id.toolBar);
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
}