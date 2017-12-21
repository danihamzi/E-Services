package com.example.dell_pc.e_services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class postservice extends AppCompatActivity {


    EditText editname,editfathername,editphoneno,editidcardno,editcity,editaddress,editstartingtime,editfinishingtime,editexpertise,editrate;
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



        editname = (EditText) findViewById ( R.id.editTextname );
        editfathername = (EditText) findViewById ( R.id.editTextfathername );
        editphoneno = (EditText) findViewById ( R.id.editTextphoneno );
        editidcardno = (EditText) findViewById ( R.id.editTextidcardno );
        editcity = (EditText) findViewById ( R.id.editTextcity );
        editaddress = (EditText) findViewById ( R.id.editTextAddress );
        editstartingtime = (EditText) findViewById ( R.id.editTextStarttime );
        editfinishingtime = (EditText) findViewById ( R.id.editTextfinishtime );
        editexpertise = (EditText) findViewById ( R.id.editTextexpertise );
        editrate = (EditText) findViewById ( R.id.editTextrate );


        spinneroccupation = (Spinner) findViewById ( R.id.spinneroccp );
        btnupdate = (Button) findViewById ( R.id.btn_update );


        spinneroccupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    databaseServiceProvider = FirebaseDatabase.getInstance().getReference().child("PLUMBER");
                }
                if (position == 1) {
                    databaseServiceProvider = FirebaseDatabase.getInstance().getReference().child("CARPANTER");
                }
                if (position == 2) {
                    databaseServiceProvider = FirebaseDatabase.getInstance().getReference().child("DRIVER");
                }
                if (position == 3) {
                    databaseServiceProvider = FirebaseDatabase.getInstance().getReference().child("ELECTRICIAN");
                }
                if (position == 4) {
                    databaseServiceProvider = FirebaseDatabase.getInstance().getReference().child("PAINTER");
                }
                if (position == 5) {
                    databaseServiceProvider = FirebaseDatabase.getInstance().getReference().child("CAR MECHANIC");
                }
                if (position == 6) {
                    databaseServiceProvider = FirebaseDatabase.getInstance().getReference().child("GARDENER");
                }
                if (position == 7) {
                    databaseServiceProvider = FirebaseDatabase.getInstance().getReference().child("CONTRACTOR");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
        String fathername = editfathername.getText().toString ().trim ();
        String phoneno = editphoneno.getText().toString ().trim ();
        String idcardno = editidcardno.getText().toString ().trim ();
        String city = editcity.getText().toString ().trim ();
        String address = editaddress.getText().toString ().trim ();
        String startingtime = editstartingtime.getText().toString ().trim ();
        String finishingtime = editfinishingtime.getText().toString ().trim ();
        String occupation = spinneroccupation.getSelectedItem ().toString ();
        String expertise = editexpertise.getText().toString ().trim ();
        String rate = editrate.getText().toString ().trim ();


        if (!TextUtils.isEmpty ( name ) && !TextUtils.isEmpty ( city )  && !TextUtils.isEmpty ( phoneno ))
        {
            String id = databaseServiceProvider.push ().getKey ();

            ServiceProvider serviceprovider = new ServiceProvider ( id,name,fathername,phoneno,
                                                                    idcardno,city,address,startingtime,
                                                                    finishingtime,occupation,expertise,rate);

            databaseServiceProvider.child ( id ).setValue ( serviceprovider );

            Toast.makeText ( this,"Details are added", Toast.LENGTH_LONG ).show ();
        }

        else
        {
            Toast.makeText ( this, "You should enter complete details", Toast.LENGTH_SHORT ).show ();
        }
    }
}