package com.example.mapsproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class AdminMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String userid,tno,date;
    int ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent in=getIntent();
        userid=in.getStringExtra("userid");
        tno=in.getStringExtra("tno");
        date=in.getStringExtra("date");
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
        try {
            mMap = googleMap;
            MyData md=new MyData();
            String locs=""+md.returnLatLng(tno,userid,date);
            String locations[]=locs.split(";");
            Toast.makeText(this, ""+locations[0]+"----"+locations[1], Toast.LENGTH_LONG).show();
            double lat=Double.parseDouble(locations[0]);
            double lon=Double.parseDouble(locations[1]);
            Geocoder g=new Geocoder(AdminMapActivity.this);
            List<Address> add=g.getFromLocation(lat,lon,1);
            Address a=add.get(0);
            String title=a.getAddressLine(0)+ "," + a.getLocality() + "," + a.getSubLocality() + "," + a.getPostalCode();
            LatLng l=new LatLng(lat,lon);
            CameraUpdate cu=CameraUpdateFactory.newLatLngZoom(l,50);
            mMap.animateCamera(cu);
            MarkerOptions m = new MarkerOptions();
            m.position(l);
            m.title(title);
            mMap.addMarker(m);

        }
        catch(Exception e)
        {
            System.out.println("ERROR----------->"+e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.adminmapmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.admin_m1)
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        else if (item.getItemId()==R.id.admin_m2)
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        else if (item.getItemId()==R.id.admin_m3)
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        return super.onOptionsItemSelected(item);
    }
}
