package com.example.mapsproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback ,LocationListener{

    private GoogleMap mMap;
    Button btn_start;
    LatLng l;
    int count=0;
    MarkerOptions m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ActivityCompat.requestPermissions(MapsActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET,Manifest.permission.ACCESS_COARSE_LOCATION},1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btn_start=findViewById(R.id.startbtn);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(l==null)
                    Toast.makeText(MapsActivity.this, "Wait...Your GPS is not Working", Toast.LENGTH_SHORT).show();
                else
                {
                    count=1;
                    Toast.makeText(MapsActivity.this, "Start the journey", Toast.LENGTH_SHORT).show();
                    CameraUpdate cu=CameraUpdateFactory.newLatLngZoom(l,20);
                    mMap.animateCamera(cu);
                    m = new MarkerOptions();
                    m.position(l);
                    m.title("your location");
                    mMap.addMarker(m);
                }
            }
        });
        LocationManager lm=(LocationManager)getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "Wait", Toast.LENGTH_SHORT).show();
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,0, this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.mapmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.satellite)
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        else if(item.getItemId()==R.id.terrain)
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        else if(item.getItemId()==R.id.hybrid)
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        System.out.println("On Location Changed called");
        if(count==0)
        {
            l=new LatLng(location.getLatitude(),location.getLongitude());
            Toast.makeText(this, "Your GPS is Started", Toast.LENGTH_SHORT).show();
        }
        else
        {
            m.visible(false);
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            MyData md=new MyData();
            SharedPreferences sp=getSharedPreferences("myprefs",MODE_PRIVATE);
            String id=""+sp.getString("id","kuchbi");
            String tnum=md.returntno(id);
            String response=md.savelocations(tnum,id,""+location.getLatitude(),""+location.getLongitude(),date);
            l=new LatLng(location.getLatitude(),location.getLongitude());
            CameraUpdate cu=CameraUpdateFactory.newLatLngZoom(l,20);
            mMap.animateCamera(cu);
            m = new MarkerOptions();
            m.position(l);
            m.title("your location");
            mMap.addMarker(m);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
