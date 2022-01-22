package com.example.fooddeliveryapp.models;

import android.media.audiofx.AudioEffect;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Product extends Model {
    int id;

    String title;
    String categorie;
    float price;
    List<String> images= new ArrayList<>();
    String description;
    float rating ;
    boolean favorite;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        if(rating > 5)
            this.rating = 5;
        this.rating = rating;

    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        images.clear();
        this.images .addAll( images);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public List<String> getGroups() {
        List<String> groupby=new ArrayList<>();
        groupby.add("Title");
        groupby.add("Description");
        groupby.add("Price");
        groupby.add("Favorite");
        groupby.add("Avail");
        groupby.add("Popularity");
        groupby.add("Category");
        return groupby;

    }

    @Override
    public Comparator<? extends Model> getComparator(String groupby) {
        return null;
    }

    @Override
    public String getComparatorValue(String groupby) {
        switch(groupby)
        {
            case "Title":
                return getTitle();
            case "Descripion":
                return getDescription();
            case "Price":
                return Float.toString(getPrice());
            case "Category":
                return getCategorie();
            case "Favorite":
//                return Boolean.toString(isFavorite());
            case "Avail":
//                return Boolean.toString(isOpen());
            case "Popularity":
//                return Float.toString(getPopularity());

        }
        return getTitle();
    }
}
