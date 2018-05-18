package com.example.sweta.edonation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    Button registerBtn;
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        checkwifi();

    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private void checkwifi(){

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Permission is not granted
//        }

        boolean check=isNetworkConnected();
        if (check==true){
            initComponents();
            setListener();

        }

        else{
            WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            wifi.setWifiEnabled(true);
            //Toast.makeText(this,"no internet connection",Toast.LENGTH_SHORT).show();
        }

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
