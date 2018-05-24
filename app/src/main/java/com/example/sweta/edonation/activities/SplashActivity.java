package com.example.sweta.edonation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.sweta.edonation.ChooseUserActivity;
import com.example.sweta.edonation.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

<<<<<<< HEAD:app/src/main/java/com/example/sweta/edonation/SplashActivity.java

                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();


=======
                Intent intent=new Intent(SplashActivity.this,ChooseUserActivity.class);
                startActivity(intent);
                finish();
>>>>>>> 5a3ccf8daa9f849b21d9d57be0b23dfab75cc855:app/src/main/java/com/example/sweta/edonation/activities/SplashActivity.java
            }
        },2000);
    }
}
