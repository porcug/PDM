package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

public class gpsTracker extends AppCompatActivity {

    private TextView tv_lat,tv_lon,tv_alt,tv_speed, tv_accuracy,tv_sensor,tv_update,tv_address;
    Switch sw_location_update,sw_gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_tracker);
        tv_lat =findViewById(R.id.tv_lat);
        tv_lon =findViewById(R.id.tv_lon);
        tv_alt =findViewById(R.id.tv_altitude);
        tv_speed =findViewById(R.id.tv_speed);
        tv_accuracy=findViewById(R.id.tv_accuracy);
        tv_sensor=findViewById(R.id.tv_sensor);
        tv_address = findViewById(R.id.tv_address);
        tv_update = findViewById(R.id.tv_updates);
        sw_location_update=findViewById(R.id.sw_locationsupdates);
        sw_gps =findViewById(R.id.sw_gps);
    }
}