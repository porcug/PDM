package com.example.fooddeliveryapp.fragments.client;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.activitys.Client_Activity;
import com.example.fooddeliveryapp.adapters.ProductListAdapter;
import com.example.fooddeliveryapp.models.Order;
import com.example.fooddeliveryapp.models.Product;
import com.example.fooddeliveryapp.models.Restaurant;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SalesItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SalesItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SalesItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SalesItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SalesItemFragment newInstance(String param1, String param2) {
        SalesItemFragment fragment = new SalesItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sales_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        itemList = view.findViewById(R.id.recicleView);
        refreshLayout = view.findViewById(R.id.refreshlayout);
        productListAdapterts= new ProductListAdapter();
        productListAdapterts.setToBasket(new ProductListAdapter.AddToBasket() {
            @Override
            public void addToBasket(Product product, int ammount) {
                if(order!=null)
                {
                    order.Add(product, ammount);
                }
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                productListAdapterts.setRestaurant(restaurant);
                productListAdapterts.Refresh();
                refreshLayout.setRefreshing(false);

            }
        });

        itemList.setLayoutManager(new LinearLayoutManager(getContext()));
        if(this.restaurant!=null)
            productListAdapterts.setRestaurant(restaurant);
        itemList.setAdapter(productListAdapterts);

        super.onViewCreated(view, savedInstanceState);
    }
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView itemList;
    private ProductListAdapter productListAdapterts;
    private Order order=null;
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        if(productListAdapterts!=null)
        productListAdapterts.setRestaurant(restaurant);
        for( Order o:Client_Activity.orderList)
        {
           if(o.getRestaurant()==restaurant)
            {order = o;break;}
        }
        if(order==null)
        {
            order=new Order(restaurant);
            Client_Activity.orderList.add(order);
        }

    }

    private Restaurant restaurant=null;


}