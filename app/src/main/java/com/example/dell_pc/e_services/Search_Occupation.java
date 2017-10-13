package com.example.dell_pc.e_services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Search_Occupation extends AppCompatActivity {

    private static Button button_plumber,button_carpenter,button_driver,button_electrician,button_painter,button_carmechanic,button_gardener,button_contractor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icono);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_search__occupation);
        ShowPlumber();
        ShowCarpenter();
        ShowDriver();
        ShowElectrician();
        ShowPainter();
        ShowCarMechanic();
        ShowGardener ();
        ShowContractor();
    }
    public void ShowPlumber() {
        button_plumber = (Button) findViewById(R.id.btn_plm);
        button_plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Plumber");
                startActivity(intent);
            }
        });
    }


    public void ShowCarpenter() {
        button_carpenter = (Button) findViewById(R.id.btn_crp);
        button_carpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.viewall");
                startActivity(intent);
            }
        });
    }

    public void ShowDriver() {
        button_driver = (Button) findViewById(R.id.btn_dri);
        button_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Driver");
                startActivity(intent);
            }
        });
    }

    public void ShowElectrician() {
        button_electrician = (Button) findViewById(R.id.btn_elec);
        button_electrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Electrician");
                startActivity(intent);
            }
        });
    }

    public void ShowPainter() {
        button_painter = (Button) findViewById(R.id.btn_pain);
        button_painter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Painter");
                startActivity(intent);
            }
        });
    }


    public void ShowCarMechanic() {
        button_carmechanic = (Button) findViewById(R.id.btn_car);
        button_carmechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Carmechanic");
                startActivity(intent);
            }
        });
    }

    public void ShowGardener() {
        button_gardener = (Button) findViewById(R.id.btn_grdn);
        button_gardener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Gardener");
                startActivity(intent);
            }
        });
    }

    public void ShowContractor() {
        button_contractor = (Button) findViewById(R.id.btn_crnt);
        button_contractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Contractor");
                startActivity(intent);
            }
        });
    }
}

