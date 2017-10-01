package com.example.dell_pc.e_services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Search_City extends AppCompatActivity {
 private static Button button_taxila;
 private static Button button_wah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icono);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_search__city);
        OnClickButtonListener();
        OnClickButtonListener1();
    }

    public void OnClickButtonListener()
    {
        button_taxila = (Button)findViewById(R.id.btn_taxila);
        button_taxila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.city_taxila");
                startActivity(intent);
            }
        });

    }

    public void OnClickButtonListener1()
    {
        button_wah = (Button)findViewById(R.id.btn_wah);
        button_wah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.city_wah");
                startActivity(intent);
            }
        });

    }
}
