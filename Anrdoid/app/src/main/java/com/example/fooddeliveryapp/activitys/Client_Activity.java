package com.example.fooddeliveryapp.activitys;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.adapters.ButtonListAdapter;
import com.example.fooddeliveryapp.fragments.client.BasketFragmnet;
import com.example.fooddeliveryapp.fragments.client.OtherDetailsFragment;
import com.example.fooddeliveryapp.fragments.client.RestaurantListFragment;
import com.example.fooddeliveryapp.fragments.client.SalesItemFragment;
import com.example.fooddeliveryapp.fragments.delivery.MapsFragment;
import com.example.fooddeliveryapp.map.GpsLocation;
import com.example.fooddeliveryapp.map.Marker;
import com.example.fooddeliveryapp.models.Order;
import com.example.fooddeliveryapp.models.Restaurant;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Client_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        RecyclerView recyclerView = findViewById(R.id.recicleview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        items.add(new ButtonListAdapter.Button_item("Restaurant", R.drawable.ic_baseline_restaurant_24,
                new ButtonListAdapter.onItemClick() {
                    @Override
                    public void click() {
                        Restaurant();
                    }
                }));
        items.add(new ButtonListAdapter.Button_item("Products", R.drawable.ic_baseline_restaurant_menu_24,
                new ButtonListAdapter.onItemClick() {
                    @Override
                    public void click() {
                        Products();
                    }
                }));
        items.add(new ButtonListAdapter.Button_item("Basket",
                R.drawable.ic_baseline_shopping_basket_24, new ButtonListAdapter.onItemClick() {
            @Override
            public void click() {
                Basket();
            }
        }));
        items.add(new ButtonListAdapter.Button_item("Confirm", R.drawable.ic_baseline_check_24, new ButtonListAdapter.onItemClick() {
            @Override
            public void click() {
                Confirm();
            }
        }));
        items.add(new ButtonListAdapter.Button_item("Status", R.drawable.ic_baseline_map_24, new ButtonListAdapter.onItemClick() {
            @Override
            public void click() {
                Status();
            }
        }));

        adapter = new ButtonListAdapter(items);
        recyclerView.setAdapter(adapter);
        Restaurant();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                m.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                // r.nextFloat()*40;
                // r.nextFloat()*40;
                //  Marker m = new Marker();
                m.setStart(new LatLng(r.nextFloat() * 40, r.nextFloat() * 40));
                m.setFinish(new LatLng(r.nextFloat() * 40, r.nextFloat() * 40));
                marker.add(m);
                while (true) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (ActivityCompat.checkSelfPermission(Client_Activity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Client_Activity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            fusedLocationProviderClient.getLastLocation();
    }
});

                }
            }
        }).start();
    }
    Marker m = new Marker();
    FusedLocationProviderClient fusedLocationProviderClient;
    List<Marker> marker= Collections.synchronizedList(new ArrayList<>());
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    Restaurant restaurant=null;
    ButtonListAdapter adapter ;
    List<ButtonListAdapter.Button_item>items =new ArrayList<>();
   static public List<Order> orderList =new ArrayList<>();
   private  void Reset(String string)
   {
       for (ButtonListAdapter.Button_item item: items) {
           item.setSelected(false);
           if(item.getTitle()==string)
               item.setSelected(true);
       }
       adapter.notifyDataSetChanged();
   }
    private void Restaurant()
    {
        Reset("Restaurant");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fg = new RestaurantListFragment();

        ft.replace(R.id.fragmentContainerView2, fg);
        ft.commit();
    }
    public void Products()
    {
        Reset("Products");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SalesItemFragment fg = new SalesItemFragment();
            fg.setRestaurant(restaurant);
        ft.replace(R.id.fragmentContainerView2, fg);
        ft.commit();
    }
    private void Basket()
    {
       Reset("Basket");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BasketFragmnet fg = new BasketFragmnet ();
        //fg.setRestaurant(restaurant);
        ft.replace(R.id.fragmentContainerView2, fg);
        ft.commit();

    }
    private void Confirm()
    {
       Reset("Confirm");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
       OtherDetailsFragment fg = new OtherDetailsFragment();
      //  fg.setRestaurant(restaurant);
        ft.replace(R.id.fragmentContainerView2, fg);
        ft.commit();

    }
    private void Status()
    {
       Reset("Status");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MapsFragment fg = new MapsFragment();
        fg.setMarkers(marker);


        //  fg.setRestaurant(restaurant);
        ft.replace(R.id.fragmentContainerView2, fg);
        ft.commit();
    }

}