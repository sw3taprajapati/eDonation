package com.example.sweta.edonation;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    Button registerBtn;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initComponents();
        initToolbar();
        setListener();
    }

    private void initComponents(){

        registerBtn =findViewById(R.id.registerBtn);
        toolbar=findViewById(R.id.toolbar);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("e-Donation");
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

    private void setListener(){
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //if(v==registerBtn){
            Intent intent=new Intent(DashboardActivity.this,
                    RegisterActivity.class);
            startActivity(intent);
            finish();
        //}
    }
}
