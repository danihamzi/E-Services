package com.example.dell_pc.e_services;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ServiceProviderLogin extends Activity {

    private EditText et_serviceEmail , et_servicePass ;
    private Button btn_serviceLogin , btn_serviceToSignUp;
    private Spinner spinneroccupation;

    private FirebaseAuth mAuth ;
    private DatabaseReference mDatabase ;
    private ProgressDialog mProgress ;
    private String occupation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_login);

        btn_serviceLogin = (Button)findViewById(R.id.btn_ServiceLogin);
        btn_serviceToSignUp = (Button)findViewById(R.id.btn_serviceToSignUp);

        et_serviceEmail = (EditText)findViewById(R.id.editTextServiceEmail);
        et_servicePass = (EditText)findViewById(R.id.editTextServicePass);

        spinneroccupation = (Spinner) findViewById ( R.id.SpinnerOccupation1 );

        mAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);

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

        btn_serviceToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceProviderLogin.this , ServiceProviderSignUp.class);
                startActivity(intent);
                finish();
            }
        });

        btn_serviceLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {
        String email = et_serviceEmail.getText().toString().trim();
        String password = et_servicePass.getText().toString().trim();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

            mProgress.setMessage("Checking Login...");
            mProgress.show();

            mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        mProgress.dismiss();
                        checkUserExist();
                    }
                    else {
                        mProgress.dismiss();
                        Toast.makeText(ServiceProviderLogin.this , "Error Login " , Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
        else {
            Toast.makeText(ServiceProviderLogin.this , "Fill all fields " , Toast.LENGTH_LONG).show();
        }

    }

    private void checkUserExist() {

        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild(user_id)){

                    Intent mainIntent = new Intent(ServiceProviderLogin.this , ProviderMapActivity.class);
                    mainIntent.putExtra("occup" , occupation);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);

                }
                else {
                    Toast.makeText(ServiceProviderLogin.this , "Setup your account " , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void onBackPressed() {
        Intent intent = new Intent(ServiceProviderLogin.this , MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
