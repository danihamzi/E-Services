package com.example.dell_pc.e_services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private static Button button_get;
    private static Button button_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icono);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_main);

         OnClickButtonListener();
         OnclickButtonListener();


    }

    public void OnClickButtonListener() {                           // get service
        button_get = (Button) findViewById(R.id.button2);
        button_get.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.dell_pc.e_services.getservice");
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnclickButtonListener()                             // post service
    {
        button_post = (Button)findViewById(R.id.button3);
        button_post.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.dell_pc.e_services.postservice");
                        startActivity(intent);
                    }
                }
        );



    }


}




