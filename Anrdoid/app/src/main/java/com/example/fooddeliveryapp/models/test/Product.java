package com.example.fooddeliveryapp.models.test;


import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Product extends com.example.fooddeliveryapp.models.Product {
    static Lorem lorem = LoremIpsum.getInstance();
    static Random random = new Random();
    static List<String> catergorie=null;
    public Product()
    {
        super();
        if(catergorie==null)
        { catergorie =new ArrayList<>();
            for(int i=0;i<5+random.nextInt(10);i++)
        {
            catergorie.add(lorem.getWords(1,5));
        }
        }

        super.setDescription(lorem.getTitle(10,30));
        super.setTitle(lorem.getTitle(1,5));
        List<String> images= new ArrayList<>();
        String word= lorem.getWords(1);
        for(int i=0;i<random.nextInt(10)+1;i++)
        {
            images.add("https://loremflickr.com/"+Integer.toString(1080+random.nextInt(840))+"/"+Integer.toString(1080+random.nextInt(840))+"/"+word+"?lock="+Integer.toString(random.nextInt()));
        }
        super.setImages(images);
        super.setCategorie(catergorie.get(random.nextInt(catergorie.size())));
        super.setPrice(random.nextFloat()*50);
        super.setFavorite(random.nextBoolean());
        super.setRating(random.nextFloat()*5);

       // super.setImageUrl("https://loremflickr.com/"+Integer.toString(1080+random.nextInt(840))+"/"+Integer.toString(1080+random.nextInt(840))+"/food?lock="+Integer.toString(random.nextInt()));

    }
}
