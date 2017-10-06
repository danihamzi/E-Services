package com.example.dell_pc.e_services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_alldata extends AppCompatActivity {

   DatabaseReference databaseServiceProvider;
    private  ListView listViewServiceprovider;
    private  List<ServiceProvider> serviceProviderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_view_alldata );
        databaseServiceProvider = FirebaseDatabase.getInstance ().getReference ("ServiceProvider");

        listViewServiceprovider = (ListView) findViewById ( R.id.listViewOccupations );
        serviceProviderList = new ArrayList<> (  );
    }

    @Override
    protected void onStart() {
        super.onStart ();

        databaseServiceProvider.addValueEventListener ( new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
           serviceProviderList.clear ();
                for(DataSnapshot serviceproviderSnapshot : dataSnapshot.getChildren ())
                {
                    ServiceProvider serviceProvider = serviceproviderSnapshot.getValue (ServiceProvider.class);

                    serviceProviderList.add(serviceProvider);


                }
                ServiceProviderList adapter = new ServiceProviderList ( View_alldata.this, serviceProviderList );
                listViewServiceprovider.setAdapter ( adapter );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );


    }
}
