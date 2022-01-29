package com.example.fooddeliveryapp.models;


import android.os.Build;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Restaurant extends Model {

        private String title ="";
        private String description="";
        private String address = "";
        private Location location=new Location();
        private float minimum_amount=0.0f;
        private float delivery_fee=0.0f;
        private List<WorkingInterval> program;
        private boolean favorite=false;
        private String imageUrl="";
        private List<Product> productList;
        private float lat;

        public float getLat() {
                return lat;
        }

        public void setLat(float lat) {
                this.lat = lat;
        }

        public float getLon() {
                return lon;
        }

        public void setLon(float lon) {
                this.lon = lon;
        }

        private float lon;



        public float getPopularity() {
                return popularity;
        }

        public void setPopularity(float popularity) {
                if(popularity>10)
                {
                        popularity=10;
                }
                this.popularity = popularity;
        }

        private float popularity;

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public Location getLocation() {
                return location;
        }

        public void setLocation(Location location) {
                this.location = location;
        }

        public float getMinimum_amount() {
                return minimum_amount;
        }

        public void setMinimum_amount(float minimum_amount) {
                this.minimum_amount = minimum_amount;
        }

        public float getDelivery_fee() {
                return delivery_fee;
        }

        public void setDelivery_fee(float delivery_fee) {
                this.delivery_fee = delivery_fee;
        }

        public List<WorkingInterval> getProgram() {
                return program;
        }

        public void setProgram(List<WorkingInterval> program) {
                this.program = program;
        }

        public boolean isFavorite() {
                return favorite;
        }

        public void setFavorite(boolean favort) {
                this.favorite = favort;
        }


        public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public List<Product> getProducts()
        {
                ArrayList<Product> result =new ArrayList<>();

                return result;
        }
        public String getImageUrl() {
                return imageUrl;
        }

        public String getTitle() {
                return title;
        }

        public String getDescription() {
                return description;
        }

        public boolean isOpen() {
                Calendar time = Calendar.getInstance();
                time.getTime();
                int minutes = time.get(Calendar.MINUTE) * 60 + time.get(Calendar.HOUR_OF_DAY);
                WorkingInterval.Days curent_day=WorkingInterval.Days.values()[time.get(Calendar.DAY_OF_WEEK)-1];
                for (WorkingInterval i : getProgram()) {
                        if(i.getDay() == curent_day)
                        {
                                if(i.getStop()<i.getStart())
                                {
                                        if(minutes>=i.getStop()&&minutes<i.getStart())
                                        {
                                                return false;
                                        }
                                        return true;
                                }
                                if(i.getStop()>i.getStart())
                                {
                                        if(minutes<i.getStop()&&minutes>=i.getStart())
                                        {
                                                return false;
                                        }
                                        return true;
                                }
                                return true;
                        }

                }
                return false;
        };

        @Override
        public List<String> getGroups() {
                List<String> groupby=new ArrayList<>();
                groupby.add("Title");
                groupby.add("Minimal amount");
                groupby.add("Delivery Fee");
                groupby.add("Favorite");
                groupby.add("Avail");
                groupby.add("Popularity");
                return groupby;
        }

        @Override
        public Comparator<Restaurant> getComparator(String groupby) {

                Comparator<Restaurant> comparator =new Comparator<Restaurant>() {
                        @Override
                        public int compare(Restaurant o1, Restaurant o2) {
                               switch(groupby)
                                {
                                        case "Title":
                                                return o1.getTitle().compareTo(o2.getTitle());
                                        case "Minimal amount":
                                                return Float.compare(o1.getMinimum_amount()*100,o2.getMinimum_amount()*100);
                                        case "Delivery Fee":
                                                return Float.compare(o1.getDelivery_fee()*100,o2.getDelivery_fee()*100);
                                        case "Favorite":
                                                return Boolean.compare(o2.isFavorite(),o1.isFavorite());
                                        case "Avail":
                                                return Boolean.compare(o2.isOpen(),o1.isOpen());
                                        case "Popularity":
                                                return Float.compare(o2.getPopularity(),o1.getPopularity());

                                }
                                return o1.getTitle().compareTo(o2.getTitle());
                        }
                };
                return comparator;

        }

        @Override
        public String getComparatorValue(String groupby) {
                switch(groupby)
                {
                        case "Title":
                                return getTitle();
                        case "Minimal amount":
                                return Float.toString(getMinimum_amount());
                        case "Delivery Fee":
                                return Float.toString(getDelivery_fee());
                        case "Favorite":
                                return Boolean.toString(isFavorite());
                        case "Avail":
                                return Boolean.toString(isOpen());
                        case "Popularity":
                                return Float.toString(getPopularity());

                }
                return getTitle();
        }



}
