package com.example.fooddeliveryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.models.CategoryItem;
import com.example.fooddeliveryapp.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListAdapter
        extends
        RecyclerView.Adapter<RestaurantListAdapter.ViewHolder> {

    List<Restaurant> items =new ArrayList<>();



    public void setItem(Restaurant item,int position)
    {
        if(position>getItemCount())
            position=getItemCount();
        if(position<0)
            position =0;
        items.add(position,item);
        notifyItemInserted(position);
    }

    public RestaurantListAdapter()
    {
    }
    public RestaurantListAdapter(List<Restaurant> items) {
        this.items =items;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_layout1, parent, false);

        // Return a new holder instance
        RestaurantListAdapter.ViewHolder viewHolder = new RestaurantListAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListAdapter.ViewHolder holder, int position) {
        Restaurant item =items.get(position);
        holder.titleTextView.setText(item.getTitle());
        holder.descriptionTextView.setText(item.getDescription());
        Picasso.get().load(item.getImageUrl()).
                resize(100,100).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        ImageView imageView;
        public TextView descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView =  itemView.findViewById(R.id.textView_title);
            descriptionTextView =  itemView.findViewById(R.id.textView_description);
            imageView =  itemView.findViewById(R.id.imageView);
        }
    }
}

