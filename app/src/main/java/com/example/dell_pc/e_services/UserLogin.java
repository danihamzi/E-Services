package com.example.dell_pc.e_services;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class UserLogin extends AppCompatActivity {

    private Button btn_uerLogin , btn_userToSignUp ;
    private EditText et_userEmail , et_userPass ;

    private FirebaseAuth mAuth ;
    private DatabaseReference mDatabase ;
    private ProgressDialog mProgress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mProgress = new ProgressDialog(this);

        btn_uerLogin = (Button)findViewById(R.id.btn_userLogin);
        btn_userToSignUp = (Button)findViewById(R.id.btn_userToSignUp);

        et_userEmail = (EditText)findViewById(R.id.editTextEmail);
        et_userPass = (EditText)findViewById(R.id.editTextPass);

        btn_userToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this , UserSignUp.class);
                startActivity(intent);
                finish();
            }
        });

        btn_uerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {
        String email = et_userEmail.getText().toString().trim();
        String password = et_userPass.getText().toString().trim();

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
                        Toast.makeText(UserLogin.this , "Error Login " , Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
        else {
            Toast.makeText(UserLogin.this , "Fill all fields " , Toast.LENGTH_LONG).show();
        }

    }

    private void checkUserExist() {

        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild(user_id)){

                    Intent mainIntent = new Intent(UserLogin.this , getservice.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);

                }
                else {
                    Toast.makeText(UserLogin.this , "Setup your account " , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
