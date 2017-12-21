package com.example.dell_pc.e_services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class city_taxila extends AppCompatActivity {
    private static Button button_plumber, button_carpenter, button_driver, button_electrician, button_painter, button_carmechanic, button_gardener, button_contractor;
    public int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icono);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_city_taxila);
        ShowPlumber();
        ShowCarpenter();
        ShowDriver();
        ShowElectrician();
        ShowPainter();
        ShowCarMechanic();
        ShowGardener();
        ShowContractor();

       id = getIntent().getExtras().getInt("ID");
    }

    public void ShowPlumber() {
        button_plumber = (Button) findViewById(R.id.btn_tplumber);
        button_plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Plumber");
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }


    public void ShowCarpenter() {
        button_carpenter = (Button) findViewById(R.id.btn_tcpanter);
        button_carpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Carpenter");
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }

    public void ShowDriver() {
        button_driver = (Button) findViewById(R.id.btn_tdriver);
        button_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Driver");
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }

    public void ShowElectrician() {
        button_electrician = (Button) findViewById(R.id.btn_telectrician);
        button_electrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Electrician");
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }

    public void ShowPainter() {
        button_painter = (Button) findViewById(R.id.btn_tpainter);
        button_painter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Painter");
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }


    public void ShowCarMechanic() {
        button_carmechanic = (Button) findViewById(R.id.btn_tcmechanic);
        button_carmechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Carmechanic");
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }

    public void ShowGardener() {
        button_gardener = (Button) findViewById(R.id.btn_tgardener);
        button_gardener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Gardener");
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }

    public void ShowContractor() {
        button_contractor = (Button) findViewById(R.id.btn_tcontractor);
        button_contractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.dell_pc.e_services.Contractor");
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }
}