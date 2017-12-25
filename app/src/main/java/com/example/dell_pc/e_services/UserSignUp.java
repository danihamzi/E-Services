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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserSignUp extends AppCompatActivity {

    private EditText et_userName , et_userEmail , et_userPass , et_userConfirmPass ;
    private Button btn_userSignUp ;

    private FirebaseAuth mAuth ;
    private ProgressDialog mprogress ;
    private DatabaseReference mDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mprogress = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        et_userName = (EditText)findViewById(R.id.et_userName) ;
        et_userEmail = (EditText)findViewById(R.id.et_userEmail) ;
        et_userPass = (EditText)findViewById(R.id.et_userPass) ;
        et_userConfirmPass = (EditText)findViewById(R.id.et_userConfirmPass) ;

        btn_userSignUp = (Button)findViewById(R.id.btn_userSignUp) ;

        btn_userSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();
            }
        });

    }

    private void startRegister() {


        final String nameString = et_userName.getText().toString().trim();
        String passString = et_userPass.getText().toString().trim();
        String confirmPassString = et_userConfirmPass.getText().toString().trim();
        String emailString = et_userEmail.getText().toString().trim();


        if(!TextUtils.isEmpty(nameString) && !TextUtils.isEmpty(passString) && !TextUtils.isEmpty(confirmPassString)  && !TextUtils.isEmpty(emailString)){

            //if(passString== confirmPassString){
            mprogress.setMessage("Creating account...");
            mprogress.show();

            mAuth.createUserWithEmailAndPassword(emailString , passString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        String user_id = mAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_db = mDatabase.child(user_id);
                        current_user_db.child("UserName").setValue(nameString);

                        mprogress.dismiss();
                        Intent intent = new Intent(UserSignUp.this , getservice.class);
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
}
