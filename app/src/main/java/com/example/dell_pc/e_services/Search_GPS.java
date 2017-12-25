package com.example.dell_pc.e_services;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search_GPS extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{


    private GoogleMap mMap;

    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastLocation ;

    public static final int REQUEST_LOCATION_CODE = 99 ;

    private Marker currentLocationMarker , serviceMarker ;

    private LatLng currentLatLng , serviceLatLng ;

    private DatabaseReference mDatabase ;
    private FirebaseAuth mAuth ;
    private String occup ;
    private static final int[] COLORS = new int[]{R.color.colorPrimaryDark,R.color.colorPrimary,R.color.colorAccent,R.color.primary_dark_material_light};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search__gps);
        //occup = getIntent().getExtras().getString("occup");

        mAuth = FirebaseAuth.getInstance() ;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Location") ;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            checkLocationPermission();
        }

        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager ().findFragmentById ( R.id.Map );
        mapFragment.getMapAsync ( this );

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case REQUEST_LOCATION_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    //permission is granted
                    if(ContextCompat.checkSelfPermission(this , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        if(client == null){

                            buildGoogleApiCLient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else {

                    //permission is denied

                    Toast.makeText(this , "Permission denied" , Toast.LENGTH_LONG).show();
                }

                return;
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            buildGoogleApiCLient();
            mMap.setMyLocationEnabled(true);
        }

        final MarkerOptions markerOptions1 = new MarkerOptions() ;

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    String key = ds.getKey();

                    DatabaseReference keyReference = FirebaseDatabase.getInstance().getReference().child("Location").child(key);

                    keyReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            double latitude = dataSnapshot.child("latitude").getValue(double.class);
                            double longitude = dataSnapshot.child("longitude").getValue(double.class);
                            String ServiceName = dataSnapshot.child("ServiceProviderName").getValue(String.class);
                            String Phone = dataSnapshot.child("Phone").getValue(String.class);
                            String Rate = dataSnapshot.child("Rate").getValue(String.class);
                            String Occupation = dataSnapshot.child("Occupation").getValue(String.class);

                            serviceLatLng = new LatLng(latitude , longitude);
                            markerOptions1.position(serviceLatLng);
                            markerOptions1.title("Details");
                            markerOptions1.snippet(ServiceName + "\n" + Occupation + "\n" + Rate + "\n" + Phone);
                            markerOptions1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                            serviceMarker = mMap.addMarker(markerOptions1);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(getApplicationContext());
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(getApplicationContext());
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(getApplicationContext());
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }



    protected synchronized void buildGoogleApiCLient(){

        client = new GoogleApiClient.Builder ( this )
                .addConnectionCallbacks ( this )
                .addOnConnectionFailedListener ( this )
                .addApi ( LocationServices.API )
                .build ();

        client.connect ();

    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location ;

        if(currentLocationMarker != null){
            currentLocationMarker.remove();
        }

        currentLatLng = new LatLng(location.getLatitude() , location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions() ;

        markerOptions.position(currentLatLng);
        markerOptions.title("Your location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        currentLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(15));

        if(client != null){

            LocationServices.FusedLocationApi.removeLocationUpdates(client , (com.google.android.gms.location.LocationListener) this);
        }


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();

        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {

            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest , this);
        }
    }

    public Boolean checkLocationPermission(){

        if(ContextCompat.checkSelfPermission(this , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.ACCESS_FINE_LOCATION)){

                ActivityCompat.requestPermissions(this , new String[] {Manifest.permission.ACCESS_FINE_LOCATION} , REQUEST_LOCATION_CODE);
            }
            else {

                ActivityCompat.requestPermissions(this , new String[] {Manifest.permission.ACCESS_FINE_LOCATION} , REQUEST_LOCATION_CODE);
            }
            return false ;
        }
        else {
            return false ;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
