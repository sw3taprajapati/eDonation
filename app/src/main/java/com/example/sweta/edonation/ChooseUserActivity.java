package com.example.sweta.edonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class ChooseUserActivity extends AppCompatActivity implements View.OnClickListener{

    Button loginOrg, loginDonor;
    TextView registerOrg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        initComponent();
        setListener();



    }

    private void initComponent(){
        loginOrg = findViewById(R.id.loginOrganizationBtn);
        //loginDonor = findViewById(R.id.loginDonorBtn);
        registerOrg = findViewById(R.id.registerOrgLink);

    }

    private void setListener(){
        loginOrg.setOnClickListener(this);
        //loginDonor.setOnClickListener(this);

        registerOrg.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v == loginOrg){

            Intent intent = new Intent(ChooseUserActivity.this, OrganizationLoginActivity.class);
            startActivity(intent);
            finish();
        }

        //if(v == loginDonor){
//            Intent intent = new Intent(ChooseUserActivity.this, OrganizationRegisterActivity.class);
//            startActivity(intent);
//            finish();
        //}

        if (v == registerOrg) {
            Intent intent = new Intent(ChooseUserActivity.this, OrganizationRegisterActivity.class);
            startActivity(intent);
            finish();

        }

        /*if(v == registerDonor){
            Intent intent = new Intent(ChooseUserActivity.this, OrganizationRegisterActivity.class);
            startActivity(intent);
            finish();
    }*/
    }


}









