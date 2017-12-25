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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class postservice extends AppCompatActivity {


    EditText editname,editfathername,editphoneno,editidcardno,editcity,editaddress,editstartingtime,editfinishingtime,editexpertise,editrate;
    Button btnupdate;
    Spinner spinneroccupation;



    DatabaseReference databaseServiceProvider;
    private FirebaseAuth mAuth ;
    private String occup ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getSupportActionBar ().setDisplayShowHomeEnabled ( true );
        getSupportActionBar ().setLogo ( R.drawable.icono );
        getSupportActionBar ().setDisplayUseLogoEnabled ( true );

        setContentView ( R.layout.activity_postservice );

        occup = getIntent().getExtras().getString("occup");

        mAuth = FirebaseAuth.getInstance();
        databaseServiceProvider = FirebaseDatabase.getInstance().getReference().child(occup) ;

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
        final String nameString = editname.getText().toString().trim();
        final String phonestring = editphoneno.getText().toString().trim();
        final String idcardstring = editidcardno.getText().toString().trim();
        final String addressstring = editaddress.getText().toString().trim();
        final String citystring = editcity.getText().toString().trim();
        final String startstring = editstartingtime.getText().toString().trim();
        final String endstring = editfinishingtime.getText().toString().trim();
        final String expertstring = editexpertise.getText().toString().trim();
        final String ratestring = editrate.getText().toString().trim();


        /*if (!TextUtils.isEmpty ( name ) && !TextUtils.isEmpty ( city )  && !TextUtils.isEmpty ( phoneno ))
        {
            String id = databaseServiceProvider.push ().getKey ();

            ServiceProvider serviceprovider = new ServiceProvider ( id,name,fathername,phoneno,
                                                                    idcardno,city,address,startingtime,
                                                                    finishingtime,occupation,expertise,rate);

            databaseServiceProvider.child ( id ).setValue ( serviceprovider );

            Toast.makeText ( this,"Details are added", Toast.LENGTH_LONG ).show ();
        }*/
        if(!TextUtils.isEmpty(nameString) && !TextUtils.isEmpty(phonestring) && !TextUtils.isEmpty(idcardstring) && !TextUtils.isEmpty(addressstring) && !TextUtils.isEmpty(citystring) &&
                !TextUtils.isEmpty(startstring) && !TextUtils.isEmpty(endstring) && !TextUtils.isEmpty(expertstring) && !TextUtils.isEmpty(ratestring)){

            databaseServiceProvider.child(mAuth.getCurrentUser().getUid()).child("Address").setValue(addressstring);
            databaseServiceProvider.child(mAuth.getCurrentUser().getUid()).child("City").setValue(citystring);
            databaseServiceProvider.child(mAuth.getCurrentUser().getUid()).child("EndTime").setValue(endstring);
            databaseServiceProvider.child(mAuth.getCurrentUser().getUid()).child("Expertise").setValue(expertstring);
            databaseServiceProvider.child(mAuth.getCurrentUser().getUid()).child("IdCard").setValue(idcardstring);
            databaseServiceProvider.child(mAuth.getCurrentUser().getUid()).child("Phone").setValue(phonestring);
            databaseServiceProvider.child(mAuth.getCurrentUser().getUid()).child("Rate").setValue(ratestring);
            databaseServiceProvider.child(mAuth.getCurrentUser().getUid()).child("ServiceProviderName").setValue(nameString);
            databaseServiceProvider.child(mAuth.getCurrentUser().getUid()).child("StartTime").setValue(startstring);

        }

        else
        {
            Toast.makeText ( this, "You should enter complete details", Toast.LENGTH_SHORT ).show ();
        }
    }
}