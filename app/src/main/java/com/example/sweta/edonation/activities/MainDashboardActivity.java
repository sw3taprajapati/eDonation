package com.example.sweta.edonation.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

public class MainDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private List<Organization> organizationList;

    private ListAdapter adapter;
    private DatabaseReference reference;
    private LinearLayout linearSearch;
    private CheckBox checkFood, checkClothes, checkBooks, checkStationery;
    private String searchTxt = "";
    private Button searchBtn;

    //private SwipeRefreshLayout swipeRefreshLayout;

    private Boolean foodBoolean, clothesBoolean, booksBoolean, stationeryBoolean;
    private ImageView imageView;
    private ProgressBar mainProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);


        reference = FirebaseDatabase.getInstance().getReference("OrganizationDetails");

        initComponents();
        initToolbar();
        initActionBar();
        checkwifi();
        setListener();

        settingClickable();

        initRecyclerView();

    }

    private void settingClickable() {
        imageView.setClickable(true);
    }

    private void initComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        checkFood = findViewById(R.id.food_checkbox);
        checkClothes = findViewById(R.id.clothes_checkbox);
        checkBooks = findViewById(R.id.books_checkbox);
        checkStationery = findViewById(R.id.stationery_checkbox);
        recyclerView = findViewById(R.id.recyclerViewOrganizationList);
        linearSearch = findViewById(R.id.linearCheckbox);
        searchBtn = findViewById(R.id.searchBtn);
        View header = navigationView.getHeaderView(0);
        imageView = header.findViewById(R.id.imageView1);
        mainProgressBar = findViewById(R.id.progressBarMain);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initActionBar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setListener() {
        navigationView.setNavigationItemSelectedListener(this);
        //btnAdmin.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        imageView.setOnClickListener(this);
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
        //swipeRefreshLayout.setOnRefreshListener(this);


    }


    private void initRecyclerView() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));


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
                        int status;


                        try {
                            status = org.getStatus();

                            if (status == 1) {
                                mainProgressBar.setVisibility(View.GONE);
                                organizationList.add(org);
                                adapter = new ListAdapter(MainDashboardActivity.this,
                                        organizationList);
                                recyclerView.setAdapter(adapter);
                            }
                        } catch (Exception e) {

                        }

                    }


                }
                try {
                    if (adapter.getItemCount() <= 0) {
                        Toast toast = Toast.makeText(getApplicationContext(), "No Data Found",
                                Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //this method executes when error

            }
        });
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
            case R.id.imageView:
                finish();
                Intent intentImg = new Intent(MainDashboardActivity.this,
                        AdminActivity.class);
                startActivity(intentImg);
                break;

            case R.id.nav_registerOrg:
                finish();
                Intent intent = new Intent(MainDashboardActivity.this,
                        OrganizationRegisterActivity.class);
                startActivity(intent);
                break;


            case R.id.nav_loginOrg:
                finish();
                Intent intent1 = new Intent(MainDashboardActivity.this,
                        OrganizationLoginActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_our_donors:
                finish();
                Intent intent4=new Intent(MainDashboardActivity.this,OurDonorsActivity.class);
                startActivity(intent4);
                break;

            case R.id.nav_aboutApp:
                finish();
                Intent intent3 = new Intent(MainDashboardActivity.this,
                        AboutAppActivity.class);
                startActivity(intent3);
                break;


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

/*
    @Override
    public void onClick(View v) {
        //if (v == btnAdmin) {
            Intent intent = new Intent(MainDashboardActivity.this,
                    AdminActivity.class);
            startActivity(intent);
            finish();
        }
    }*/


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);


        return cm.getActiveNetworkInfo() != null;


    }

    private void checkwifi() {

        boolean check = isNetworkConnected();
        if (check == true) {
            initComponents();
            setListener();

        } else {

            Toast toast = Toast.makeText(this, "Connect to a network",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            initComponents();
            setListener();
        }

    }


    private void searchOrganization() {

        final int[] itemCount = new int[1];
        if (checkFood.isChecked()) {
            foodBoolean = true;
            //Log.i("food", currentlyLooking);
        } else {
            foodBoolean = false;
        }

        if (checkClothes.isChecked()) {
            clothesBoolean = true;
            //Log.i("clothes", currentlyLooking);
        } else {
            clothesBoolean = false;
        }

        if (checkBooks.isChecked()) {
            booksBoolean = true;
        } else {
            booksBoolean = false;
        }

        if (checkStationery.isChecked()) {
            stationeryBoolean = true;
        } else {
            stationeryBoolean = false;
        }

        checkFood.setChecked(false);
        checkBooks.setChecked(false);
        checkClothes.setChecked(false);
        checkStationery.setChecked(false);
        organizationList.clear();
        adapter.notifyDataSetChanged();
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
                        Boolean booleanFood = org.getCurrentlyLooking().isFood();
                        Boolean booleanClothes = org.getCurrentlyLooking().isClothes();
                        Boolean booleanBooks = org.getCurrentlyLooking().isBooks();
                        Boolean booleanStationery = org.getCurrentlyLooking().isStationery();

                        if ((booleanFood == true && foodBoolean == true) ||
                                (booleanClothes == true && clothesBoolean == true)
                                || (booleanBooks == true && booksBoolean == true)
                                || (booleanStationery == true && stationeryBoolean == true)
                                && status == 1) {
                            organizationList.add(org);
                            adapter = new ListAdapter(MainDashboardActivity.this,
                                    organizationList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        }
                    }

                }
                try {


                    if (adapter.getItemCount() <= 0) {
                        Toast toast = Toast.makeText(getApplicationContext(), "No Data Found",
                                Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                initRecyclerView();
                            }
                        }, 1000);
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //this method executes when error

            }
        });

        if (recyclerView == null)

        {
            Toast.makeText(this, "No Data Found!!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == imageView) {
            Intent intentImg = new Intent(MainDashboardActivity.this, AdminActivity.class);
            startActivity(intentImg);
        }
        if (v == searchBtn) {
            searchOrganization();
        }


    }



        /*else if(v==imageView){
            Intent intent=new Intent(MainDashboardActivity.this,
                    AdminActivity.class);
            startActivity(intent);
            finish();
        }*/
}

    /*@Override
    public void onRefresh() {
        initRecyclerView();
        swipeRefreshLayout.setRefreshing(false);
    }*/
