package com.example.fooddeliveryapp.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.adapters.ButtonListAdapter;
import com.example.fooddeliveryapp.fragments.client.RestaurantListFragment;
import com.example.fooddeliveryapp.fragments.delivery.MapsFragment;
import com.example.fooddeliveryapp.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Delivery_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        RecyclerView recyclerView=findViewById(R.id.recicleview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        items.add(new ButtonListAdapter.Button_item("Find restaurant", R.drawable.ic_baseline_search_24,
                new ButtonListAdapter.onItemClick() {
                    @Override
                    public void click() {
                        findRestaurants();
                    }
                }));
        items.add(new ButtonListAdapter.Button_item("Pick product", R.drawable.ic_baseline_restaurant_menu_24,
                new ButtonListAdapter.onItemClick() {
                    @Override
                    public void click() {

                    }
                }));
        items.add(new ButtonListAdapter.Button_item("Deliver to client",
                R.drawable.ic_baseline_location_on_24, new ButtonListAdapter.onItemClick() {
            @Override
            public void click() {

            }
        }));
        items.add(new ButtonListAdapter.Button_item("Confirm", R.drawable.ic_baseline_check_24, new ButtonListAdapter.onItemClick() {
            @Override
            public void click() {

            }
        }));
        items.add(new ButtonListAdapter.Button_item("Call", R.drawable.ic_baseline_phone_24, new ButtonListAdapter.onItemClick() {
            @Override
            public void click() {

            }
        }));

        adapter = new ButtonListAdapter(items);
        recyclerView.setAdapter(adapter);
      //  Delivery();
    }

    private void findRestaurants()
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MapsFragment fg = new MapsFragment();

        ft.replace(R.id.fragmentContainerView2, fg);
        ft.commit();
        fg.add( 0,0);
    }
    Restaurant restaurant=null;
    ButtonListAdapter adapter ;
    List<ButtonListAdapter.Button_item> items =new ArrayList<>();
}