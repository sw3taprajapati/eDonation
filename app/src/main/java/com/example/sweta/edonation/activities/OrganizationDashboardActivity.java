package com.example.sweta.edonation.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sweta.edonation.R;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sweta.edonation.R;
import com.example.sweta.edonation.adaptersandviewholders.DonorAdapter;
import com.example.sweta.edonation.adaptersandviewholders.ListAdapter;

import com.example.sweta.edonation.pojoclasses.Donor;
import com.example.sweta.edonation.pojoclasses.Organization;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class OrganizationDashboardActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<Donor> donorList;
    private DonorAdapter adapter;
    private ActionBarDrawerToggle toggle;
    private DatabaseReference databaseOrganization;
    private FirebaseUser user;
    private TextView organizationEmail, organizationName;
    private String organizationNameString;
    private DatabaseReference dbDonor;
    private SwipeRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_dashboard);


        initComponents();
        initToolbar();
        initDrawer();
        setListeners();
        checkwifi();


        user = FirebaseAuth.getInstance().getCurrentUser();

        databaseOrganization = FirebaseDatabase.getInstance().
                getReference("OrganizationDetails");
        dbDonor=FirebaseDatabase.getInstance().getReference("DonorDetails");

        insertInfoInNav();
        initRecyclerView();
    }


    private void initComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.recyclerViewOrganizationList);
        navigationView = (NavigationView) findViewById(R.id.nav_view2);
        refreshLayout=findViewById(R.id.refreshRecyclerViewOrganization);

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);


        return cm.getActiveNetworkInfo() != null;


    }

    private void checkwifi() {

        boolean check = isNetworkConnected();
        if (check == true) {


        } else {

            Toast toast = Toast.makeText(this, "Connect to a network",
                    Toast.LENGTH_SHORT);
            //toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();


        }
    }

    private void initToolbar() {
        toolbar.setTitle("e-Donation");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDrawer() {
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }


    private void setListeners() {
        navigationView.setNavigationItemSelectedListener(this);
        refreshLayout.setOnRefreshListener(this);
    }




    private void initRecyclerView() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        donorList = new ArrayList<>();


        DatabaseReference dbDonor=FirebaseDatabase.getInstance().getReference("DonorDetails");
        dbDonor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method executes when successful

                if (dataSnapshot.exists()) {
                    for (DataSnapshot organizationSnapshot : dataSnapshot.getChildren()) {
                        Donor donor = organizationSnapshot.getValue(Donor.class);
                        int status;


                        try {
                            String orgName = donor.getOrgName();

                            if (orgName.equals(organizationNameString)) {
                                donorList.add(donor);
                                adapter = new DonorAdapter(OrganizationDashboardActivity.this,
                                        donorList);
                                recyclerView.setAdapter(adapter);
                                /*adapter.notifyDataSetChanged();*/
                            }
                        } catch (Exception e) {

                        }
                    }
                    try{
                    if (adapter.getItemCount() <= 0) {
                        Toast toast = Toast.makeText(getApplicationContext(), "No Data Found",
                                Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }catch(Exception e) {
                    }
                    }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //this method executes when error


            }
        });

        if (recyclerView == null) {
            Toast.makeText(this, "No Data Found!!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
            //sothat one cannot return to maindashboard after logging in
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            System.exit(0);

        }

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }


        if (id == R.id.action_change_password) {

            startActivity(new Intent(OrganizationDashboardActivity.this,ChangePasswordActivity.class));


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_editProfile:
                finish();
                Intent in = new Intent(OrganizationDashboardActivity.this,
                        EditProfileActivity.class);
                startActivity(in);
                break;

            case R.id.nav_addDonor:
                finish();
                Intent in2 = new Intent(OrganizationDashboardActivity.this,
                        AddDonorActivity.class);
                startActivity(in2);
                break;

            case R.id.nav_aboutApp:
                finish();
                Intent in3 = new Intent(OrganizationDashboardActivity.this,
                        AboutAppActivity.class);
                startActivity(in3);
                break;

            case R.id.nav_logOut:
                dialogBox();

                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void dialogBox() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to log out?");
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(OrganizationDashboardActivity.this,
                                MainDashboardActivity.class);
                        startActivity(intent);

                    }
                });

        alertDialogBuilder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.cancel();

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void insertInfoInNav() {
        final String email ;
        try {

            email = user.getEmail();



        organizationEmail = (TextView) navigationView.getHeaderView(0).
                findViewById(R.id.organizationEmail);
        organizationName = (TextView) navigationView.getHeaderView(0).
                findViewById(R.id.organizationName);
        databaseOrganization.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method executes when successful
                if (dataSnapshot.exists()) {
                    for (DataSnapshot organizationSnapshot : dataSnapshot.getChildren()) {
                        Organization org = organizationSnapshot.getValue(Organization.class);

                        String emailFromDB = org.getOrgEmailID();
                        if (email.equals(emailFromDB)) {
                            String name = org.getOrgFullName();
                            String emailID = org.getOrgEmailID();
                            organizationNameString=org.getOrgFullName();
                            organizationEmail.setText(emailID);
                            organizationName.setText(name);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }catch(Exception e){

        }

    }


    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
        initRecyclerView();
    }
}


