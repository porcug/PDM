package com.example.fooddeliveryapp.activitys;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.fooddeliveryapp.models.Order;
import com.example.fooddeliveryapp.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Client_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        RecyclerView recyclerView=findViewById(R.id.recicleview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

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
    }

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
    }
}