package com.example.fooddeliveryapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.models.Order;

import java.util.ArrayList;
import java.util.List;

public class BasketProductListAdapter extends RecyclerView.Adapter<BasketProductListAdapter.ViewHolder>{

    public List<Order.Products> getProductsList() {
        return productsList;
    }

    public BasketProductListAdapter(List<Order.Products> productsList) {
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_command_product, parent, false);

        // Return a new holder instance
        BasketProductListAdapter.ViewHolder viewHolder = new BasketProductListAdapter.ViewHolder(contactView);
        return viewHolder;

    }
    List<Order.Products> productsList =new ArrayList<>();
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order.Products products = productsList.get(position);
        holder.title.setText(products.getProduct().getTitle());
        int pozition =position;
        int nr=products.getNumber();

        holder.pret.setText(String.format("%.2f",products.getProduct().getPrice()));
        holder.number.setText(Integer.toString(nr));
        if(nr<1000) {
            holder.add.setColorFilter(Color.parseColor("#0D40F1"));
        } else {
            holder.add.setColorFilter(null);
        }
        if(nr>1) {
            holder.sub.setColorFilter(Color.parseColor("#0D40F1"));
        } else {
            holder.sub.setColorFilter(null);
        }
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delete(pozition);


            }
        });
        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(products.getNumber()>1)
                {
                    products.setNumber(products.getNumber()-1);

                }
                if(cb!=null)
                    cb.callback();
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(products.getNumber()<1000)
                {
                    products.setNumber(products.getNumber()+1);

                }
                if(cb!=null)
                    cb.callback();
            }
        });

    }

    public BasketProductListAdapter(List<Order.Products> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    Context context;
    private void delete(int position)
    {
        //  AlertDialog.Builder dialog =new AlertDialog.Builder(context);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                productsList.remove(position);
                if(cb!=null)
                    cb.callback();
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
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title , number, pret;
        public ImageView add,sub,del;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView11);
            number = itemView.findViewById(R.id.textView12);
            pret = itemView.findViewById(R.id.textView13);
            add = itemView.findViewById(R.id.imageView10);
            sub = itemView.findViewById(R.id.imageView11);
            del = itemView.findViewById(R.id.imageView12);
        }
    }

    public void setCb(Callback cb) {
        this.cb = cb;
    }

    private Callback cb=null;
    public interface Callback
    {
        public void callback();
    }

}
