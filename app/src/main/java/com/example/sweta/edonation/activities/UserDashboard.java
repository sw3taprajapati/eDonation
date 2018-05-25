package com.example.sweta.edonation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sweta.edonation.R;
import com.example.sweta.edonation.adaptersandviewholders.ListAdapter;
import com.example.sweta.edonation.pojoclasses.Organization;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserDashboard extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    private List<Organization> organizationList;
    private ListAdapter adapter;
    DatabaseReference reference;
    Button admin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        reference = FirebaseDatabase.getInstance().getReference("OrganizationDetails");
        initComponents();
        initToolbar();
        initRecyclerView();
        setListener();

    }

    private void initComponents(){
        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recyclerViewUserDashboard);
        admin=findViewById(R.id.adminBtn);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("e-Donation");
    }

    public void setListener(){
        admin.setOnClickListener(this);
    }

    private void initRecyclerView() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        organizationList = new ArrayList<>();

        final DatabaseReference dbOrganization = FirebaseDatabase.getInstance().
                getReference("OrganizationDetails");

        dbOrganization.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method executes when successful

                if (dataSnapshot.exists()) {
                    for (DataSnapshot organizationSnapshot : dataSnapshot.getChildren()) {
                        Organization org = organizationSnapshot.getValue(Organization.class);

                        int status = org.getStatus();
                        if (status == 1) {
                            organizationList.add(org);
                        }
                    }

                    adapter = new ListAdapter(UserDashboard.this, organizationList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //this method executes when error

            }
        });

        if(recyclerView==null){
            Toast.makeText(this,"No Data Found!!",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {

        if(v==admin){
            Intent intent=new Intent(UserDashboard.this,AdminActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
