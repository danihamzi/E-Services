package com.example.dell_pc.e_services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Contractor extends AppCompatActivity {

    Query mQueryService ;
    private DatabaseReference carpanter;
    RecyclerView mServiceList ;
    public int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_contractor);

        carpanter=FirebaseDatabase.getInstance ().getReference ().child("CONTRACTOR");

        mServiceList = (RecyclerView)findViewById(R.id.service_list);

        mServiceList.setHasFixedSize(true);
        mServiceList.setLayoutManager(new LinearLayoutManager(this));





        id = getIntent().getExtras().getInt("ID");
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(id==2) {
            mQueryService = carpanter.orderByChild("serviceProviderCity").equalTo("taxila");
        }
        else if(id==1)
        {
            mQueryService = carpanter.orderByChild("serviceProviderCity").equalTo("wahcantt");
        }

        else
        {
            mQueryService = carpanter.orderByChild("serviceProviderOccupation").equalTo("CONTRACTOR");
        }



        FirebaseRecyclerAdapter<ServiceProvider,ServiceViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ServiceProvider, ServiceViewHolder>(

                ServiceProvider.class,
                R.layout.cardview,
                ServiceViewHolder.class,
                mQueryService

        ) {
            @Override
            protected void populateViewHolder(ServiceViewHolder viewHolder, ServiceProvider model, int position) {

                viewHolder.setServiceProviderName(model.getServiceProviderName());
                viewHolder.setServiceProviderCity(model.getServiceProviderCity());
                viewHolder.setServiceProviderPhoneno(model.getServiceProviderPhoneno());


            }
        };

        mServiceList.setAdapter(firebaseRecyclerAdapter);

    }






    public static class ServiceViewHolder extends RecyclerView.ViewHolder{

        View mView ;


        public ServiceViewHolder(View itemView) {


            super(itemView);
            mView = itemView ;

        }

        public void setServiceProviderName (String serviceProviderName){
            TextView txt_Name = (TextView) mView.findViewById(R.id.txt_serviceProviderName);
            txt_Name.setText(serviceProviderName);
        }
        public void setServiceProviderCity (String serviceProviderCity){
            TextView txt_Email = (TextView) mView.findViewById(R.id.txt_serviceProviderCity);
            txt_Email.setText(serviceProviderCity);
        }
        public void setServiceProviderPhoneno (String serviceProviderPhoneno){
            TextView txt_Price = (TextView) mView.findViewById(R.id.txt_serviceProviderphoneno);
            txt_Price.setText(serviceProviderPhoneno);
        }
    }
}
