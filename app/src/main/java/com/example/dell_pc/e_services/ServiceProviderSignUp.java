package com.example.dell_pc.e_services;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceProviderSignUp extends AppCompatActivity {

    private EditText editname,editEmail,editphoneno,editidcardno,editcity,editaddress,editstartingtime,editfinishingtime,editexpertise,editrate , editpass , editconfirmPass ;
    private Button btn_serviceSignUp;
    private Spinner spinneroccupation;

    private FirebaseAuth mAuth ;
    private ProgressDialog mprogress ;
    private DatabaseReference mDatabase , mDatabaseLocation ;

    private String occupation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_sign_up);

        mDatabaseLocation = FirebaseDatabase.getInstance().getReference().child("Location");

        editname = (EditText) findViewById ( R.id.et_serviceName );
        editEmail = (EditText) findViewById ( R.id.et_serviceEmail );
        editphoneno = (EditText) findViewById ( R.id.et_servicePhone );
        editidcardno = (EditText) findViewById ( R.id.et_serviceIdCard );
        editcity = (EditText) findViewById ( R.id.et_serviceCity );
        editaddress = (EditText) findViewById ( R.id.et_serviceAddress );
        editstartingtime = (EditText) findViewById ( R.id.et_serviceStartTime );
        editfinishingtime = (EditText) findViewById ( R.id.et_serviceEndTime );
        editexpertise = (EditText) findViewById ( R.id.et_serviceExpertise );
        editrate = (EditText) findViewById ( R.id.et_serviceRate );
        editpass = (EditText) findViewById ( R.id.et_servicePass );
        editconfirmPass = (EditText) findViewById ( R.id.et_serviceConfirmPass );


        spinneroccupation = (Spinner) findViewById ( R.id.SpinnerOccupation );
        btn_serviceSignUp = (Button) findViewById ( R.id.btn_serviceSignUp );

        mAuth = FirebaseAuth.getInstance();
        mprogress = new ProgressDialog(this);

        spinneroccupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("PLUMBER");
                    occupation = "PLUMBER";
                }
                if (position == 1) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("CARPANTER");
                    occupation = "CARPANTER";
                }
                if (position == 2) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("DRIVER");
                    occupation = "DRIVER";
                }
                if (position == 3) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("ELECTRICIAN");
                    occupation = "ELECTRICIAN";
                }
                if (position == 4) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("PAINTER");
                    occupation = "PAINTER";
                }
                if (position == 5) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("CAR MECHANIC");
                    occupation = "CAR MECHANIC";
                }
                if (position == 6) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("GARDENER");
                    occupation = "GARDENER";
                }
                if (position == 7) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("CONTRACTOR");
                    occupation = "CONTRACTOR";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_serviceSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();
            }
        });
    }

    private void startRegister() {


        final String nameString = editname.getText().toString().trim();
        String passString = editpass.getText().toString().trim();
        String confirmPassString = editconfirmPass.getText().toString().trim();
        String emailString = editEmail.getText().toString().trim();
        final String phonestring = editphoneno.getText().toString().trim();
        final String idcardstring = editidcardno.getText().toString().trim();
        final String addressstring = editaddress.getText().toString().trim();
        final String citystring = editcity.getText().toString().trim();
        final String startstring = editstartingtime.getText().toString().trim();
        final String endstring = editfinishingtime.getText().toString().trim();
        final String expertstring = editexpertise.getText().toString().trim();
        final String ratestring = editrate.getText().toString().trim();


        if(!TextUtils.isEmpty(nameString) && !TextUtils.isEmpty(passString) && !TextUtils.isEmpty(confirmPassString)  && !TextUtils.isEmpty(emailString) && !TextUtils.isEmpty(phonestring)
                && !TextUtils.isEmpty(idcardstring) && !TextUtils.isEmpty(addressstring) && !TextUtils.isEmpty(citystring) && !TextUtils.isEmpty(startstring) && !TextUtils.isEmpty(endstring)
                && !TextUtils.isEmpty(expertstring) && !TextUtils.isEmpty(ratestring)){

            //if(passString== confirmPassString){
                mprogress.setMessage("Creating account...");
                mprogress.show();

                mAuth.createUserWithEmailAndPassword(emailString , passString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = mDatabase.child(user_id);
                            //DatabaseReference locationDB = mDatabaseLocation.child(user_id);
                            current_user_db.child("serviceProviderName").setValue(nameString);
                            current_user_db.child("serviceProviderPhoneno").setValue(phonestring);
                            current_user_db.child("serviceProviderIdcardno").setValue(idcardstring);
                            current_user_db.child("serviceProviderAddress").setValue(addressstring);
                            current_user_db.child("serviceProviderCity").setValue(citystring);
                            current_user_db.child("serviceProviderStartingtime").setValue(startstring);
                            current_user_db.child("serviceProviderFinishingtime").setValue(endstring);
                            current_user_db.child("serviceProviderExpertise").setValue(expertstring);
                            current_user_db.child("serviceProviderRate").setValue(ratestring);
                            current_user_db.child("serviceProviderOccupation").setValue(occupation);

                            /*locationDB.child("ServiceProviderName").setValue(nameString);
                            locationDB.child("Phone").setValue(phonestring);
                            locationDB.child("Rate").setValue(ratestring);*/

                            mprogress.dismiss();
                            Intent intent = new Intent(ServiceProviderSignUp.this , ProviderMapActivity.class);
                            intent.putExtra("occup" , occupation);
                            intent.putExtra("name" , nameString);
                            intent.putExtra("phone" , phonestring);
                            intent.putExtra("rate" , ratestring);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }

                    }
                });
            //}
            /*else {

                Toast.makeText(getApplicationContext() , "Password does not match" , Toast.LENGTH_LONG).show();

            }*/
        }
        else {

            Toast.makeText(getApplicationContext() , "Fill all fields!!!" , Toast.LENGTH_LONG).show();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(ServiceProviderSignUp.this , ServiceProviderLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}