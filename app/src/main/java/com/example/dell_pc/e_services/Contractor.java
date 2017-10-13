package com.example.dell_pc.e_services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Contractor extends AppCompatActivity {


    private DatabaseReference mdatabase;
    private ListView mUserlist;
    private ArrayList<String> mUser = new ArrayList<> (  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_contractor );
        mdatabase = FirebaseDatabase.getInstance ().getReference ().child ( "ServiceProvider" ).child ( "CONTRACTOR" );
        mUserlist = (ListView) findViewById ( R.id.contractor_list );
        final ArrayAdapter<String> arrayadaptor = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,mUser );
        mUserlist.setAdapter ( arrayadaptor );



        mdatabase.addChildEventListener ( new ChildEventListener () {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue (String.class);
                mUser.add ( value );
                arrayadaptor.notifyDataSetChanged ();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );


    }
}
