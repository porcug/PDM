package com.example.fooddeliveryapp.models;

import java.util.Random;

public class Restaurant {
        String imageUrl="https://thispersondoesnotexist.com/image?r=";
        String title ="fghuyt";
        String description="ghjygh";

        public String getImageUrl() {
                return imageUrl+Integer.toString(r.nextInt());
        }

        public String getTitle() {
                return title;
        }

        public String getDescription() {
                return description;
        }
        private static Random r=new Random();
}
