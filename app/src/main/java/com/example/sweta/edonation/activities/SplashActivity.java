package com.example.sweta.edonation.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.sweta.edonation.*;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPreferences = getDefaultSharedPreferences(this);
        final Boolean isLoggedIn = sharedPreferences.getBoolean("IsLoggedIn", false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLoggedIn) {

                    Intent intent = new Intent(SplashActivity.this,
                            OrganizationDashboardActivity.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(SplashActivity.this,
                            MainDashboardActivity.class);
                    startActivity(intent);
                }
            }
        },2000);
    }
}
