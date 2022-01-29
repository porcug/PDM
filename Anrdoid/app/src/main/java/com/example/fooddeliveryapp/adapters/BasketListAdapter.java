package com.example.fooddeliveryapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.models.Order;

import java.util.ArrayList;
import java.util.List;

public class BasketListAdapter extends
        RecyclerView.Adapter<BasketListAdapter.ViewHolder> {



    List<Order> order= new ArrayList<>();
    public BasketListAdapter(List<Order> orderList,Context context)
    {
        order.clear();
        order= orderList;
        order.removeIf(value->value.getCount()==0);
        this.context=context;
    }
    Context context;
    @NonNull
    @Override
    public BasketListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_command, parent, false);

        // Return a new holder instance
        BasketListAdapter.ViewHolder viewHolder = new BasketListAdapter.ViewHolder(contactView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull BasketListAdapter.ViewHolder holder, int position) {
        Order item=order.get(position);
        int pozition =position;
        holder.name.setText(item.getRestaurant().getTitle());
        holder.list.setVisibility(item.isShow_list()?View.VISIBLE:View.GONE);
        holder.more.setImageResource(item.isShow_list()?R.drawable.ic_baseline_arrow_circle_up_24:R.drawable.ic_baseline_arrow_circle_down_24);
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.list.getVisibility()==View.VISIBLE)
                {
                    item.setShow_list(false);
                    holder.list.setVisibility(View.GONE);
                }
                else
                {
                    item.setShow_list(true);
                    holder.list.setVisibility(View.VISIBLE);
                }
                holder.more.setImageResource(item.isShow_list()?R.drawable.ic_baseline_arrow_circle_up_24:R.drawable.ic_baseline_arrow_circle_down_24);

            }
        });
        holder.total.setText(String.format("%.02f",item.getPrice()));

        holder.nr_product.setText(Integer.toString(item.getCount()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               delete(pozition);

            }
        });
        BasketProductListAdapter bla= new BasketProductListAdapter(item.getProductList(),context);
        bla.setCb(new BasketProductListAdapter.Callback() {
            @Override
            public void callback() {
                order.removeIf(value->value.getCount()==0);
                notifyDataSetChanged();
            }
        });
        holder.list.setLayoutManager(new LinearLayoutManager(context));
        holder.list.setAdapter(bla);
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback!=null)
                {
                    callback.onCallBack(item);
                }
            }
        });

    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private Callback callback;

    public interface Callback{
        public void onCallBack(Order order);
    }
    private void delete(int position)
    {
      //  AlertDialog.Builder dialog =new AlertDialog.Builder(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                order.remove(position);

                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    public int getItemCount() {
        return order.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name ,title ,nr_product ,total;
        Button next;
        ImageView more , delete;
        RecyclerView list ;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view =itemView.findViewById(R.id.linear_layout);
            name = itemView.findViewById(R.id.title);
            title = itemView.findViewById(R.id.restauran_title);
            nr_product = itemView.findViewById(R.id.text_nr_product);
            total = itemView.findViewById(R.id.text_total);
            next = itemView.findViewById(R.id.button);
            more = itemView.findViewById(R.id.imageView6);
            delete = itemView.findViewById(R.id.imageView7);
            list = itemView.findViewById(R.id.productList);
        }
    }
    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order.clear();
        this.order.addAll(order);
    }

}
