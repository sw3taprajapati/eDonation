package com.example.sweta.edonation;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    Button registerBtn;
    private Context context;
    Button adminBtn;
    ImageButton searchBtn;
    android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerView;
    List<Organization> organizationList;
    ListAdapter adapter;
    EditText searchText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initComponents();
        initToolbar();
        initRecyclerView();
        setListener();
        checkwifi();

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("e-Donation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);


        return cm.getActiveNetworkInfo() != null;


    }

    private void checkwifi() {

        boolean check = isNetworkConnected();
        if (check == true) {
            initComponents();
            setListener();

        } else {

            Toast toast = Toast.makeText(this, "Connect to a network", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();


            initComponents();
            setListener();

            //finishActivity(1);


            //startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            //onBackPressed();

//            checkwifi();

//             WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//            wifi.setWifiEnabled(true);


            // WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            //  wifi.setWifiEnabled(true);
            //Toast.makeText(this,"no internet connection",Toast.LENGTH_SHORT).show();
        }

    }

    private void initComponents() {

        registerBtn = findViewById(R.id.registerBtn);
        adminBtn = findViewById(R.id.adminButton);
        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbar);
        searchBtn=findViewById(R.id.searchBtn);
        searchText=findViewById(R.id.searchTxt);
    }

    private void initRecyclerView(){

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

                    adapter = new ListAdapter(DashboardActivity.this, organizationList);
                    recyclerView.setAdapter(adapter);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                //this method executes when error

            }
        });

    }

    private void setListener() {
        registerBtn.setOnClickListener(this);
        adminBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        boolean check1 = isNetworkConnected();
        if (check1 == true) {
            if (v == registerBtn) {
                Intent intent = new Intent(DashboardActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
                finish();

            } else if (v == adminBtn) {

                Intent intent = new Intent(DashboardActivity.this,
                        AdminActivity.class);
                startActivity(intent);
                finish();

            } else if (v==searchBtn){
                String search = searchText.getText().toString();

                searchOrganization(search);
            }
        } else {

            Toast toast = Toast.makeText(this, "Connect to a network", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }
    }

    private void searchOrganization(String search){

    }
}





