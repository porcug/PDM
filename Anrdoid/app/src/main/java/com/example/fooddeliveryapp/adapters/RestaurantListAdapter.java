package com.example.fooddeliveryapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.extern.EllipsizingTextView;
import com.example.fooddeliveryapp.models.CategoryItem;
import com.example.fooddeliveryapp.models.Model;
import com.example.fooddeliveryapp.models.Restaurant;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RestaurantListAdapter
        extends
        RecyclerView.Adapter<RestaurantListAdapter.ViewHolder> {

    List<Restaurant> items =new ArrayList<>();

    String comparator="";
    public interface OnItemClickListener {
        void onItemClick(Restaurant item,int poz);
    }
    public void setItem(List<Restaurant> item)
    {
        items.clear();
        items.addAll(item);
        notifyDataSetChanged();
    }
    public int jumpTo(String item)
    {
        for(int i=0;i<items.size();i++)
        {
            if(item.compareTo(items.get(i).getComparatorValue(comparator))==0)
            {
                return i;
            }
        }
        return 0;
    }
    public String getGroupText(int index)
    {
       return items.get(index).getComparatorValue(comparator);
    }
    public void sortBy(String item)
    {
        comparator =item;
        if(items.size()>1)
            items.sort( items.get(0).getComparator(item));

        notifyDataSetChanged();
    }


    AdapterView.OnItemClickListener itemClickListener =null;

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
        int parentWidth = holder.titleTextView.getWidth();
        holder.titleTextView.setText(item.getTitle());

        holder.descriptionTextView.setEllipsize(TextUtils.TruncateAt.END);
        holder.descriptionTextView.setText(item.getDescription());
    //holder
        holder.progressBar.setVisibility(View.VISIBLE);
        holder.favorite.setRating(item.isFavorite()?1:0);
        holder.popularity.setRating(item.getPopularity());
        Picasso.get().load(item.getImageUrl()).
                transform(new Resize(holder.imageView.getWidth(),holder.imageView.getHeight())).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
                holder.imageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Exception e) {
                holder.progressBar.setVisibility(View.GONE);
                holder.imageView.setVisibility(View.VISIBLE);
                holder.imageView.setImageResource(R.drawable.ic_baseline_broken_image_24);
            }
        });
        holder.bind(item, position);
    }

    private class Resize implements Transformation {
            Resize(int width,int height)
            {originalViewWidth=width>0?width:500;
            originalViewHeight=height>0?width:500;}
        int originalViewWidth;
        int originalViewHeight ;
        public Bitmap transform(Bitmap source) {

                double aspectRatio =(double)source.getHeight()/(double)source.getWidth();

                int transformViewHeight =(int) (originalViewHeight*aspectRatio);
                aspectRatio =1/aspectRatio;
                int transformViewWidth = (int) (originalViewWidth*aspectRatio);
                System.out.print("transformViewHeight= "+transformViewHeight);
                System.out.print("transformViewWidth= "+transformViewWidth);
                Bitmap scaledBitmap=Bitmap.createBitmap(originalViewWidth,originalViewHeight,Bitmap.Config.ARGB_8888);
                if(originalViewHeight<transformViewHeight) {
                    scaledBitmap = Bitmap.createScaledBitmap(source, transformViewWidth, originalViewHeight, false);
                }
                else {
                    scaledBitmap = Bitmap.createScaledBitmap(source, originalViewWidth, transformViewHeight, false);
                }
                if(scaledBitmap!=source)
                    source.recycle();
                return scaledBitmap;
            }

            @Override
            public String key() {
                return "resizer";
            }
        };


    @Override
    public int getItemCount() {
        return items.size();
    }
    private RestaurantListAdapter.OnItemClickListener listener =null;
    public void setOnItemCLickListener(RestaurantListAdapter.OnItemClickListener itemClickListener) {
        listener = itemClickListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        ImageView imageView;
        public EllipsizingTextView descriptionTextView;
        public ProgressBar progressBar;
        public RatingBar favorite,popularity;
        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView =  itemView.findViewById(R.id.textView_title);
            descriptionTextView =  itemView.findViewById(R.id.textView_description);
            imageView =  itemView.findViewById(R.id.imageView);
            progressBar = itemView.findViewById(R.id.progressBar_imageLoading);
            favorite= itemView.findViewById(R.id.favorite);
            popularity = itemView.findViewById(R.id.raiting);
        }
        void bind(Restaurant item,int poz) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onItemClick(item, poz);
                }
            });

        }
    }
}

