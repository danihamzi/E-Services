package com.example.dell_pc.e_services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class city_taxila extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icono);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_city_taxila);
    }
}
