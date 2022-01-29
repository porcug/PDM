package com.example.fooddeliveryapp.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class GpsLocation {
    static LocationManager locationManager;

    @SuppressLint("MissingPermission")
    public GpsLocation(LocationManager locationManager) {
        if (this.locationManager == null) {
            this.locationManager = locationManager;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, getLocation);

    }
    static Location lastLocation;
    static GetLocation getLocation =new GetLocation();
    private static class GetLocation implements LocationListener
    {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            lastLocation =location;
        }
    }

    public Location getLastLocation() {
        return lastLocation;
    }
}
