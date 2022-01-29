package com.example.fooddeliveryapp.fragments.delivery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.map.Marker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapsFragment extends Fragment {


    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            for(int i=0;i<100;i++) {
                LatLng sydney = new LatLng((float) (45.61568575752174 + random.nextFloat() * (45.70114024963515 - 45.61568575752174)),
                        (float) (25.538717089037608 + random.nextFloat() * (25.675416001893144 - 25.538717089037608)));
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapFromVector(R.drawable.ic_baseline_lunch_dining_24)));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
             //   googleMap.clear();
            }
           map=googleMap;
        }
    };
    static Random random = new Random();
    GoogleMap map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        thread.start();
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

    }
    SupportMapFragment mapFragment;
    public void add(double latitudine,double longitudine)
    {
    }

    public void setMarkers(List<Marker> markers) {
        Markers = markers;
    }
    private boolean  alive= true;
    private List<Marker> Markers = new ArrayList<>();
    Thread thread =  new Thread(new Runnable() {
        @Override
        public void run() {
            while (alive) {

                try {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            update();
                        }
                    });


                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
    });
    public void update() {
        try {
            mapFragment =
                    (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            if (mapFragment != null) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull GoogleMap googleMap) {
                        googleMap.clear();
                        for (Marker m : Markers) {
                            m.addToMap(googleMap);
                        }
                    }
                });
            }
        } catch ( Exception e)
        {

        }
    }
    private BitmapDescriptor BitmapFromVector( int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(getContext(), vectorResId);
        vectorDrawable.setTint(Color.parseColor("#eeee00"));
        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        alive =false;
    }
}
