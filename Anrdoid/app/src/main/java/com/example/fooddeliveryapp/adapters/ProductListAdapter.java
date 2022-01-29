package com.example.fooddeliveryapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.extern.EllipsizingTextView;
import com.example.fooddeliveryapp.models.Product;
import com.example.fooddeliveryapp.models.Restaurant;
import com.example.fooddeliveryapp.util.Utili;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class ProductListAdapter extends
        RecyclerView.Adapter<ProductListAdapter.ViewHolder>{

    Restaurant restaurant=null;
    List<Product> productList =new ArrayList<>();

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        Refresh();
    }

    public void Refresh()
    {
        if(restaurant!=null)
        {
            productList.clear();
            productList.addAll(restaurant.getProducts());
            notifyDataSetChanged();
        }
    }
    private Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout


        // Return a new holder instance
        ViewHolder viewHolder ;
                switch (viewType)
                        {
                            case 0:{
                                View contactView = inflater.inflate(R.layout.restaurant_details_layout, parent, false);
                                viewHolder =new RestaurantHolder(contactView);
                            }break;
                            case 1:{
                                View contactView = inflater.inflate(R.layout.item_category_list, parent, false);
                                viewHolder =new CategoryList(contactView);
                            }break;
                            default :{
                                View contactView = inflater.inflate(R.layout.item_layout2, parent, false);
                                viewHolder =new ProductHolder(contactView);
                            }

                                break;
                        }
        return viewHolder;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if(holder instanceof RestaurantHolder)
            {
                RestaurantHolder rest=  ((RestaurantHolder) holder);
                if(restaurant==null)
                    return;
                rest.title.setText(restaurant.getTitle());
                rest.description.setText(restaurant.getDescription());
                rest.rating.setRating(restaurant.getPopularity());
                rest.favorite.setRating(restaurant.isFavorite()?1:0);
                Picasso.get().load(restaurant.getImageUrl()).
                        transform(new Resize(rest.imageView.getWidth(),rest.imageView.getHeight())).into(rest.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                      //  holder.progressBar.setVisibility(View.GONE);
                        rest.imageView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                      //  holder.progressBar.setVisibility(View.GONE);
                        rest.imageView.setVisibility(View.VISIBLE);
                        rest.imageView.setImageResource(R.drawable.ic_baseline_broken_image_24);
                    }
                });
            }
            if(holder instanceof CategoryList)
            {
                OptionListAdapter optionListAdapter =new OptionListAdapter();
                RecyclerView recyclerView = ((CategoryList) holder).recyclerView;
                recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
                recyclerView.setAdapter(optionListAdapter);
                optionListAdapter.setItems(Utili.getGroups(productList,"Category"));
            }
            if(holder instanceof ProductHolder)
            {
                ProductHolder ph=((ProductHolder) holder);
                Product pr=productList.get(position-2);
                ph.title.setText(pr.getTitle());
                ph.description.setText(pr.getDescription());
                ph.price.setText(String.format("%.2f",pr.getPrice()));
                ph.favorite.setRating(pr.isFavorite()?1:0);
                ph.cantitate=0;
                ph.favorite.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if( motionEvent.getAction() ==MotionEvent.ACTION_UP)
                        { pr.setFavorite(!pr.isFavorite());
                        ph.favorite.setRating(pr.isFavorite()?1:0);
                        }
                        return true;
                    }
                });
                ph.popularity.setRating(pr.getRating());

                ph.up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ph.up();
                    }
                });
                ph.down.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ph.down();
                    }
                });
                ph.updateColor();
                ph.addtobascket.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(ph.cantitate!=0&&toBasket!=null)
                        {
                            toBasket.addToBasket(pr,ph.cantitate);
                        }
                        ph.cantitate =0;
                        ph.updateColor();
                    }
                });
            }

    }

    public void setToBasket(AddToBasket toBasket) {
        this.toBasket = toBasket;
    }

    private AddToBasket toBasket=null;
    public interface AddToBasket{
        public void addToBasket(Product product,int ammount);
    }
    @Override
    public int getItemViewType(int position) {
        if(position>=2)
        {
            return 2;
        }
        return position;
    }

    @Override
    public int getItemCount() {
        return productList.size()+2;
    }
    public class RestaurantHolder extends ViewHolder
    {


        public RatingBar favorite, rating;
        public TextView title,description;
        public ImageView imageView;
        public RestaurantHolder(@NonNull View parent) {
            super(parent);
            imageView= parent.findViewById(R.id.imageView4);
            title = parent.findViewById(R.id.textView2);
            description =  parent.findViewById(R.id.textView3);
            // Inflate the custom layout
            favorite= parent.findViewById(R.id.favorite);
            rating =parent.findViewById(R.id.raiting);



        }
    }
    public class ProductHolder extends ViewHolder
    {
        public ImageView imageView;
        public ProgressBar progressBar;
        public TextView title;
        public EllipsizingTextView description;
        public RatingBar favorite;
        public RatingBar popularity;
        public ImageView down;
        public TextView ammount;
        public ImageView up;
        public TextView price;
        public ImageView addtobascket;
        public int cantitate=0;
        public void up()
        {
            if(cantitate<=100)
            {
                cantitate++;

            }
            updateColor();
        }
        public void down()
        {
            if(cantitate>0) {
                cantitate--;
            }
            updateColor();
        }
        public void updateColor()
        {
            if(cantitate>0&&cantitate<100) {
                up.setColorFilter(Color.parseColor("#0D40F1"));
                down.setColorFilter(Color.parseColor("#0D40F1"));
            }
            if(cantitate<=0)
            {
                down.setColorFilter(null);
                up.setColorFilter(Color.parseColor("#0D40F1"));
                addtobascket.setColorFilter(null);
            }
            if(cantitate>=100)
            {
                down.setColorFilter(Color.parseColor("#0D40F1"));
                up.setColorFilter(null);
            }
            ammount.setText(String.format("%d",cantitate));
            if(cantitate>0)
            {
                addtobascket.setColorFilter(Color.parseColor("#0D40F1"));
            }
        }
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            progressBar = itemView.findViewById(R.id.progressBar_imageLoading);
            title = itemView.findViewById(R.id.textView_title);
            description = itemView.findViewById(R.id.textView_description);
            favorite = itemView.findViewById(R.id.favorite);
            popularity = itemView.findViewById(R.id.raiting);
            down = itemView.findViewById(R.id.imageView8);
            ammount = itemView.findViewById(R.id.textView6);
            up = itemView.findViewById(R.id.imageView9);
            price = itemView.findViewById(R.id.textView7);
            addtobascket = itemView.findViewById(R.id.imageView5);
        }

    }
    public class CategoryList extends ViewHolder
    {

        RecyclerView recyclerView;
        public CategoryList(@NonNull View itemView) {
            super(itemView);
           recyclerView =itemView.findViewById(R.id.recicleView);
        }
    }
    public abstract class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
