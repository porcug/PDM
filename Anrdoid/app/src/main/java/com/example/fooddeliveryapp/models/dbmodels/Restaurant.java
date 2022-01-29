package com.example.fooddeliveryapp.models.dbmodels;

import com.example.fooddeliveryapp.models.Location;
import com.example.fooddeliveryapp.models.WorkingInterval;

import java.util.List;

public class Restaurant {
    private String title ="";
    private String description="";
    private String address = "";
    private Location location=new Location();
    private float minimum_amount=0.0f;
    private float delivery_fee=0.0f;
    private List<WorkingInterval> program;
    private boolean favorite=false;
    private String imageUrl="";
    private List<Orders> ordersList;
    private float lat,lon;
}
