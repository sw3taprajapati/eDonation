package com.example.sweta.edonation;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    TextView registerTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initComponents();
    }

    private void initComponents(){

        registerTxt =findViewById(R.id.registerTxt);
    }
}
