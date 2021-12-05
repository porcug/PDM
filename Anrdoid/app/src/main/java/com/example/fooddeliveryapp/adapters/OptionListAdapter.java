package com.example.fooddeliveryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.models.CategoryItem;

import java.util.List;

public class OptionListAdapter extends
        RecyclerView.Adapter<OptionListAdapter.ViewHolder> {

    List<CategoryItem> items;
    public OptionListAdapter(List<CategoryItem> items)
    {
        this.items =items;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_category_layout_1, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryItem categoryItem =items.get(position);
        if(categoryItem.isSelected())
            holder.layout.setBackgroundResource(R.drawable.category_active_item);
        else
            holder.layout.setBackgroundResource(R.drawable.category_normal_item);
        holder.nameTextView.setText(categoryItem.getText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public LinearLayout layout;


        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView =  itemView.findViewById(R.id.item_textview);
            layout =  itemView.findViewById(R.id.item_layout);
        }
    }
}