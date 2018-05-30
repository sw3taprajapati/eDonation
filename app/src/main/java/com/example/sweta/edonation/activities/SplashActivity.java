package com.example.sweta.edonation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.sweta.edonation.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(FirebaseAuth.getInstance().getCurrentUser()==null){
                    Intent intent = new Intent(SplashActivity.this, MainDashboardActivity.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Intent intent = new Intent(SplashActivity.this, OrganizationLoginDashboardActivity.class);
                    startActivity(intent);
                    finish();

                }
             /*   mRef = FirebaseDatabase.getInstance().getReference();
                auth = FirebaseAuth.getInstance();
                firebaseAuth = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        if (firebaseAuth.getCurrentUser()!= null){
                            Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(getApplicationContext(), "You are not currently logged in.", Toast.LENGTH_SHORT).show();

                        }
                    }
                };
                */


            }
        }, 2000);
    }
}
