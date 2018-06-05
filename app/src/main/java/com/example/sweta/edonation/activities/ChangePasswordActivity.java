package com.example.sweta.edonation.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sweta.edonation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    private EditText newPassword;
    private Button changePasswordBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        checkwifi();
        initComponent();
        initToolbar();
        initListeners();

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);


        return cm.getActiveNetworkInfo() != null;


    }

    private void checkwifi() {

        boolean check = isNetworkConnected();
        if (check == true) {
            initComponent();

        } else {

            Toast toast = Toast.makeText(this, "Connect to a network",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            initComponent();
        }

    }

    private void initComponent() {
        toolbar = findViewById(R.id.toolBar);
        newPassword = findViewById(R.id.newPassword);
        changePasswordBtn = findViewById(R.id.changePasswordBtn);





    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reset Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ChangePasswordActivity.this,
                        OrganizationLoginActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void initListeners() {
        changePasswordBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {



        if(v == changePasswordBtn){


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {

            user.updatePassword(newPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ChangePasswordActivity.this,"Your password has been changed",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ChangePasswordActivity.this,OrganizationLoginActivity.class));
                    }
                    else{
                        Toast.makeText(ChangePasswordActivity.this,"Your password could not be changed",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }




        }

    }
}
