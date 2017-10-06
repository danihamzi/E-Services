package com.example.dell_pc.e_services;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL-PC on 10/6/2017.
 */

public class ServiceProviderList extends ArrayAdapter<ServiceProvider> {

    private Activity context;
    private List<ServiceProvider> serviceproviderlist;


    public ServiceProviderList(Activity context, List<ServiceProvider> serviceproviderlist )
    {
        super(context,R.layout.list_layout,serviceproviderlist);
        this.context = context;
        this.serviceproviderlist = serviceproviderlist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater ();

         View listViewItem = inflater.inflate ( R.layout.list_layout,null,true );

        TextView textViewId = (TextView)listViewItem.findViewById ( R.id.textView11 );
        TextView textViewName = (TextView)listViewItem.findViewById ( R.id.textviewname );
        TextView textViewPhoneno = (TextView)listViewItem.findViewById ( R.id.textViewphoneno );
        TextView textViewCity = (TextView)listViewItem.findViewById ( R.id.textViewcity );
        TextView textViewOccupation = (TextView)listViewItem.findViewById ( R.id.textViewoccupation );

        ServiceProvider serviceProvider = serviceproviderlist.get ( position );

        textViewId.setText ( serviceProvider.getServiceProviderId () );
        textViewName.setText ( serviceProvider.getServiceProviderName () );
        textViewPhoneno.setText ( serviceProvider.getServiceProviderPhoneno () );
        textViewCity.setText ( serviceProvider.getServiceProviderCity () );
        textViewOccupation.setText ( serviceProvider.getServiceProviderOccupation ()  );

        return listViewItem;

    }
}
