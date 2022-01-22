package com.example.fooddeliveryapp.fragments.client;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.activitys.Client_Activity;
import com.example.fooddeliveryapp.adapters.OptionListAdapter;
import com.example.fooddeliveryapp.adapters.RestaurantListAdapter;
import com.example.fooddeliveryapp.adapters.SpinnerAdapter;
import com.example.fooddeliveryapp.fragments.login.Login_fragment;
import com.example.fooddeliveryapp.models.CategoryItem;
import com.example.fooddeliveryapp.models.Restaurant;
import com.example.fooddeliveryapp.util.Utili;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RestaurantListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RestaurantListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestaurantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RestaurantListFragment newInstance(String param1, String param2) {
        RestaurantListFragment fragment = new RestaurantListFragment();
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
        return inflater.inflate(R.layout.fragment_client_restaurant, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewRestaurante =view.findViewById(R.id.recyclerViewOption);
        categoryList =view.findViewById(R.id.recyclerViewCategory);
        optionSpinner=view.findViewById(R.id.optionSpinner);
        view.findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionSpinner.setVisibility(View.INVISIBLE);
                optionSpinner.performClick();
               // optionSpinner.setVisibility(View.GONE);
            }
        });
        recyclerViewRestaurante.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
               int firstpoz= ((LinearLayoutManager) recyclerViewRestaurante.getLayoutManager()).
                        findFirstCompletelyVisibleItemPosition();
               optionListAdapter.setSelected(categoryAdapter.getGroupText(firstpoz));
            }
        });

        ArrayList<Restaurant> item =new ArrayList();
        optionListAdapter=new OptionListAdapter();
        for(int i=0;i<100;i++)
        {
            item.add(new com.example.fooddeliveryapp.models.test.Restaurant());
        }
      //  Utili.getGroups(item,);
        optionSpinner.setAdapter(new SpinnerAdapter(getContext(),sortingOption));
        optionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryAdapter.sortBy(sortingOption.get(position));
                optionListAdapter.setItems(Utili.getGroups(item,sortingOption.get(position)));
                positionselected = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                categoryAdapter.sortBy(sortingOption.get(0));
                optionListAdapter.setItems(Utili.getGroups(item,sortingOption.get(0)));
                positionselected = 0;
            }

        });
    //    OptionListAdapter optionsAdapter=new OptionListAdapter();
        categoryList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        categoryList.setAdapter(optionListAdapter);
        optionListAdapter.setOnItemCLickListener(new OptionListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CategoryItem item,int notused) {
                       int poz= categoryAdapter.jumpTo(item.getText());


                recyclerViewRestaurante.getLayoutManager().smoothScrollToPosition(recyclerViewRestaurante,new RecyclerView.State(),poz);

            }
        });
        categoryAdapter=new RestaurantListAdapter();
        categoryAdapter.setItem(item);
        categoryAdapter.setOnItemCLickListener(new RestaurantListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Restaurant item, int poz) {
                ((Client_Activity)getActivity()).setRestaurant(item);
                ((Client_Activity)getActivity()).Products();
            }
        });
        recyclerViewRestaurante.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewRestaurante.setAdapter(categoryAdapter);
        refreshLayout=view.findViewById(R.id.mainListRefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                item.clear();
                for(int i=0;i<100;i++)
                {
                    item.add(new com.example.fooddeliveryapp.models.test.Restaurant());
                }

                categoryAdapter.setItem(item);

                categoryAdapter.sortBy(sortingOption.get(positionselected));
                optionListAdapter.setItems(Utili.getGroups(item,sortingOption.get(positionselected)));
            refreshLayout.setRefreshing(false);
            }
        });
    }
    final List<String> sortingOption =new Restaurant().getGroups();
    RestaurantListAdapter categoryAdapter;
    RecyclerView recyclerViewRestaurante,categoryList;
    Spinner optionSpinner;
    OptionListAdapter optionListAdapter;
    int positionselected=0;
    SwipeRefreshLayout refreshLayout;
}