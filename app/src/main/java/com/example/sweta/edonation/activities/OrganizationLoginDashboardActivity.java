package com.example.sweta.edonation.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sweta.edonation.R;
import com.example.sweta.edonation.pojoclasses.Organization;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class OrganizationLoginDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ArrayAdapter<String> adapter;
    DrawerLayout drawer;
    NavigationView navigationView;
    NavigationView navView;
    Toolbar toolbar = null;
    ActionBarDrawerToggle toggle;

    DatabaseReference databaseOrganization;
    FirebaseUser user;

    TextView organizationEmail, organizationName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_login_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("e-Donation");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view2);

       navigationView.setNavigationItemSelectedListener(this);

        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        user = FirebaseAuth.getInstance().getCurrentUser();




        databaseOrganization = FirebaseDatabase.getInstance().
                getReference("OrganizationDetails");

        insertInfoInNav();

//        accessInformation();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_editProfile:
                Intent in = new Intent(OrganizationLoginDashboardActivity.this,
                        EditProfileActivity.class);
                startActivity(in);
                break;


            case R.id.nav_aboutApp:
                Intent in2 = new Intent(OrganizationLoginDashboardActivity.this,
                        AboutAppActivity.class);
                startActivity(in2);
                break;

            case R.id.nav_logOut:
                Intent in3 = new Intent(OrganizationLoginDashboardActivity.this,
                        MainDashboardActivity.class);
                startActivity(in3);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void insertInfoInNav() {

        final String email = user.getEmail();
        organizationEmail  = (TextView) navigationView.getHeaderView(0).findViewById(R.id.organizationEmail);
        organizationName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.organizationName);
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

//    public void accessInformation(){
//        user= FirebaseAuth.getInstance().getCurrentUser();
//
//        String email = user.getEmail();
//        emailEditText.setText(email);
//    }
    }
}


