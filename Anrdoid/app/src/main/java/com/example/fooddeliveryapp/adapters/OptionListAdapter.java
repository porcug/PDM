package com.example.fooddeliveryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.models.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class OptionListAdapter extends
        RecyclerView.Adapter<OptionListAdapter.ViewHolder> {

    List<CategoryItem> items = new ArrayList<>();

    public void setItems(List<String> items) {
        this.items.clear();
        for (String x : items
        ) {
            this.items.add(new CategoryItem(x));
        }
        notifyDataSetChanged();
    }

    public OptionListAdapter() {

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
        CategoryItem categoryItem = items.get(position);
        if (categoryItem.isSelected())
            holder.layout.setBackgroundResource(R.drawable.category_active_item);
        else
            holder.layout.setBackgroundResource(R.drawable.category_normal_item);
        holder.nameTextView.setText(categoryItem.getText());
        holder.bind(categoryItem, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemCLickListener(OnItemClickListener itemClickListener) {
        listener = itemClickListener;
    }

    public int getSelected() {
        return selected;
    }

    private int selected;

    public void setSelected(String valoare)
    {
        boolean changed=false;
        if(items.size()<=0)
            return;
        items.get(items.size()-1).setSelected(false);
        for (int i = 0; i < items.size()-1; i++) {
            items.get(i).setSelected(false);

            if(!changed)
            if(items.get(i+1).getText().compareTo(valoare)>=0) {
                changed = true;
                items.get(i).setSelected(true);
                selected =i;
            }

            notifyItemChanged(i);
        }
        if(!changed) {
            items.get(items.size() - 1).setSelected(true);
            selected =items.size() - 1;
        }
        notifyItemChanged(items.size()-1);
    }
    public interface OnItemClickListener {
        void onItemClick(CategoryItem item,int poz);
    }
    private OnItemClickListener listener =null;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public LinearLayout layout;


        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView =  itemView.findViewById(R.id.item_textview);
            layout =  itemView.findViewById(R.id.item_layout);
        }
        public void bind(CategoryItem item, int poz) {


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null)
                        listener.onItemClick(item,poz);
                    for(int i=0;i<items.size();i++)
                        if(items.get(i).isSelected())
                        {
                            items.get(i).setSelected(false);
                            notifyItemChanged(i);
                        }
                    item.setSelected(true);
                    notifyItemChanged(poz);
                }
            });
        }
    }
}