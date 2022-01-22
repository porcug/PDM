package com.example.fooddeliveryapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.R;

import java.util.ArrayList;
import java.util.List;
public class ButtonListAdapter extends
        RecyclerView.Adapter< ButtonListAdapter.ViewHolder> {
    @NonNull
    @Override
    public ButtonListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.button_list_item, parent, false);

        // Return a new holder instance
        ButtonListAdapter.ViewHolder viewHolder = new ButtonListAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonListAdapter.ViewHolder holder, int position) {
       Button_item item = items.get(position);
       holder.textView.setText(item.getTitle());
       holder.imageView.setImageResource(item.getImage_id());
        holder.imageView.setColorFilter(Color.parseColor("#0D40F1"));
        holder.imageView.setBackgroundColor(Color.parseColor("#ffffff"));
        if(item.isSelected())
        holder.imageView.setBackgroundColor(Color.parseColor("#cccccc"));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.itemClick.click();
            }
        });
    }

    public ButtonListAdapter(List<Button_item> items)
    {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public View view;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
           imageView = itemView.findViewById(R.id.imageView14);
           textView = itemView.findViewById(R.id.textView14);
            view = itemView;
        }

    }


    onItemClick itemClick=null;
    public  interface onItemClick {
        public void click();
    }

    public static class Button_item {
        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public int getImage_id() {
            return Image_id;
        }

        public void setImage_id(int image_id) {
            Image_id = image_id;
        }

        public boolean isSelected() {
            return selected;
        }

        public onItemClick getItemClick() {
            return itemClick;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
        private onItemClick itemClick;
        boolean selected;
        String Title;
        int Image_id;

        public Button_item( String title, int image_id,onItemClick itemClick) {
            this.itemClick = itemClick;
            Title = title;
            Image_id = image_id;
        }
    }

    public List<Button_item> items =new ArrayList<>();
}


