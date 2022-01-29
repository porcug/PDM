package com.example.fooddeliveryapp.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Marker{
    LatLng start;
    LatLng finish;
    LatLng position;
    BitmapDescriptor bstart ,bfinish,bposition;
    public void addToMap(GoogleMap map)
        {
            MarkerOptions marker;

            marker = new MarkerOptions();
            if(bfinish!=null)
                marker.icon(bfinish);
            if(finish!=null) {
                marker.position(finish);
                map.addMarker(marker);
            }

            marker = new MarkerOptions();
            if(bstart!=null)
                marker.icon(bstart);
            if( start!=null) {
                marker.position(start);
                map.addMarker(marker);
            }

            marker = new MarkerOptions();
            if(bposition!=null)
                marker.icon(bposition);
            if(position!=null) {
                marker.position(position);
                map.addMarker(marker);
            }
        }

    public void updatePos(LatLng pos)
        {
            position = pos ;
        }

    public void setStart(LatLng start) {
        this.start = start;
    }

    public void setFinish(LatLng finish) {
        this.finish = finish;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public void setBstart(BitmapDescriptor bstart) {
        this.bstart = bstart;
    }

    public void setBfinish(BitmapDescriptor bfinish) {
        this.bfinish = bfinish;
    }

    public void setBposition(BitmapDescriptor bposition) {
        this.bposition = bposition;
    }

    public static  BitmapDescriptor BitmapFromVector(Context context, int vectorResId ,Integer color) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setTint(color!=null?color:0);
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
    }


