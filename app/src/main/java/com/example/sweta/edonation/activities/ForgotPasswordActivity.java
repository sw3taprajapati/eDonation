package com.example.sweta.edonation.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.sweta.edonation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    private Button resetPasswordBtn;
    private EditText passwordEmail;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        checkwifi();
        initComponent();
        initToolbar();
        initListeners();

        firebaseAuth = FirebaseAuth.getInstance();
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
        resetPasswordBtn = findViewById(R.id.resetBtn);
        passwordEmail = findViewById(R.id.resetPasswordEmail);





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
                Intent intent = new Intent(ForgotPasswordActivity.this,
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
        resetPasswordBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == resetPasswordBtn){
            String userEmail = passwordEmail.getText().toString().trim();

            if(userEmail.equals("")){
                Toast.makeText(this, "Please enter your registered email ID",Toast.LENGTH_SHORT).show();

            }
            else{
                firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            dialogBox();

                        }else{
                            Toast.makeText(ForgotPasswordActivity.this ,"Error in sending password reset email",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

        }

    }

    public void dialogBox() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Check your email to reset password");
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();

                        Intent intent = new Intent(ForgotPasswordActivity.this, OrganizationLoginActivity.class);
                        startActivity(intent);



                    }
                });



        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
