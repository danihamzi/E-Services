package com.example.dell_pc.e_services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class postservice extends AppCompatActivity {


    EditText editname, editcity, editphone;
    Button btnupdate;
    Spinner spinneroccupation;

    DatabaseReference databaseServiceProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getSupportActionBar ().setDisplayShowHomeEnabled ( true );
        getSupportActionBar ().setLogo ( R.drawable.icono );
        getSupportActionBar ().setDisplayUseLogoEnabled ( true );

        setContentView ( R.layout.activity_postservice );

        databaseServiceProvider = FirebaseDatabase.getInstance ().getReference ("serviceprovider");

        editname = (EditText) findViewById ( R.id.editTextname );
        editcity = (EditText) findViewById ( R.id.editTextcity );
        editphone = (EditText) findViewById ( R.id.editTextphoneno );

        spinneroccupation = (Spinner) findViewById ( R.id.spinneroccp );

        btnupdate = (Button) findViewById ( R.id.btn_update );

        btnupdate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
              addServiceProvider();
            }
        } );


    }

    private void addServiceProvider()
    {
        String name = editname.getText().toString ().trim ();
        String city = editcity.getText().toString ().trim ();
        String phoneno = editphone.getText().toString ().trim ();

        String occupation = spinneroccupation.getSelectedItem ().toString ();


        if (!TextUtils.isEmpty ( name ) && !TextUtils.isEmpty ( city )  && !TextUtils.isEmpty ( phoneno ))
        {
            String id = databaseServiceProvider.push ().getKey ();

            ServiceProvider serviceprovider = new ServiceProvider ( id,name,city,phoneno,occupation);
//.add bvalu event listener new event listner
            databaseServiceProvider.child (name).setValue ( serviceprovider );

            Toast.makeText ( this,"Details are added", Toast.LENGTH_LONG ).show ();
        }

        else
        {
            Toast.makeText ( this, "You should enter complete details", Toast.LENGTH_SHORT ).show ();
        }
    }

}
