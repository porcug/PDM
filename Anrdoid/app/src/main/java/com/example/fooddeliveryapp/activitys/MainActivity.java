package com.example.fooddeliveryapp.activitys;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.communication.Session;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Session.autoLogin(MainActivity.this);
            }
        }).start();


    }
}