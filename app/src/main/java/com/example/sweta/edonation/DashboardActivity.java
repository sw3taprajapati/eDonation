package com.example.sweta.edonation;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    Button registerBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initComponents();
        setListener();
    }

    private void initComponents(){

        registerBtn =findViewById(R.id.registerBtn);
    }

    private void setListener(){
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v==registerBtn){
            Intent intent=new Intent(DashboardActivity.this,
                    RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
