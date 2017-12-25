package com.example.dell_pc.e_services;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class getservice extends AppCompatActivity {

    private static Button button_city;
    private static Button button_occupation;
    private static Button button_location;
    private static Button button_view;

    private FirebaseAuth mAuth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icono);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_getpost);
        OnClickButtonListener();
        OnClickButtonListener1();
        OnClickButtonListener2();

    }

    public void OnClickButtonListener() {
        button_city = (Button) findViewById(R.id.btn_city);
        button_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Search_City");
                startActivity(intent);
            }
        });
    }
    public void OnClickButtonListener1(){
        button_occupation = (Button)findViewById(R.id.btn_ocp);
        button_occupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Search_Occupation");
                startActivity(intent);
            }
        });
    }
    public void OnClickButtonListener2()
    {
        button_location = (Button)findViewById(R.id.btn_loc);
        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getservice.this , Search_GPS.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to logout ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mAuth.getInstance().signOut();

                Intent intent = new Intent(getservice.this , UserLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });

        final AlertDialog ad = builder.create();
        ad.show();

    }
}
