package com.example.fooddeliveryapp.fragments.client;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.activitys.Client_Activity;
import com.example.fooddeliveryapp.adapters.BasketListAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasketFragmnet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasketFragmnet extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BasketFragmnet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment basket.
     */
    // TODO: Rename and change types and number of parameters
    public static BasketFragmnet newInstance(String param1, String param2) {
        BasketFragmnet fragment = new BasketFragmnet();
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
        return inflater.inflate(R.layout.fragment_basket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        basket_list=view.findViewById(R.id.basket_list);
        basketListAdapter =new BasketListAdapter(Client_Activity.orderList,getContext());
        basket_list.setLayoutManager(new LinearLayoutManager(getContext()));
        basket_list.setAdapter(basketListAdapter);
    }
    BasketListAdapter basketListAdapter;

    private RecyclerView basket_list;

    @Override
    public void onPause() {
      //  Client_Activity.orderList.clear();
      //  Client_Activity.orderList.addAll(basketListAdapter.getOrder());
        super.onPause();
    }
}