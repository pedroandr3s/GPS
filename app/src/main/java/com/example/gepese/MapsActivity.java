package com.example.gepese;

import com.google.android.gms.maps.model.LatLng;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import android.content.Intent;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.gepese.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final int LOCATION_PERMISSION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent intent = getIntent();
        String latitud1 = intent.getStringExtra("latitud1");
        String longitud1 = intent.getStringExtra("longitud1");
        String latitud2 = intent.getStringExtra("latitud2");
        String longitud2 = intent.getStringExtra("longitud2");
        String latitud3 = intent.getStringExtra("latitud3");
        String longitud3 = intent.getStringExtra("longitud3");

        agregarMarcador(latitud1, longitud1);
        agregarMarcador(latitud2, longitud2);
        agregarMarcador(latitud3, longitud3);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mostrarUbicacionActual();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST);
        }
    }

    private void agregarMarcador(String latitud, String longitud) {
        if (latitud != null && longitud != null) {
            LatLng coordenadas = new LatLng(Double.parseDouble(latitud), Double.parseDouble(longitud));
            mMap.addMarker(new MarkerOptions().position(coordenadas).title("Marcador"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenadas));
        }
    }

    private void mostrarUbicacionActual() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            double latitudActual = location.getLatitude();
                            double longitudActual = location.getLongitude();
                            LatLng ubicacionActual = new LatLng(latitudActual, longitudActual);

                            mMap.addMarker(new MarkerOptions()
                                    .position(ubicacionActual)
                                    .title("Ubicación Actual")
                                    .snippet("Lat: " + latitudActual + ", Lng: " + longitudActual));

                            mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacionActual));
                        } else {
                        }
                    });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST);
        }
    }
}
