package com.example.dell_pc.e_services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class getservice extends AppCompatActivity {

    private static Button button_city;
    private static Button button_occupation;
    private static Button button_location;
    private static Button button_view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icono);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

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
            Intent intent = new Intent("com.example.dell_pc.e_services.ProviderMapActivity");
            startActivity(intent);
        }
    });


}

}
