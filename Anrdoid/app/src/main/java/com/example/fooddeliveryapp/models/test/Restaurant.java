package com.example.fooddeliveryapp.models.test;


import com.example.fooddeliveryapp.models.Product;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Restaurant extends com.example.fooddeliveryapp.models.Restaurant {
    static Lorem lorem = LoremIpsum.getInstance();
    static Random random = new Random();
    public Restaurant ()
    {
        super();

        super.setTitle(lorem.getTitle(1,5));
        super.setImageUrl("https://loremflickr.com/"+Integer.toString(1080+random.nextInt(840))+"/"+Integer.toString(1080+random.nextInt(840))+"/food?lock="+Integer.toString(random.nextInt()));
        super.setDescription(lorem.getTitle(5,20));
        super.setPopularity(random.nextFloat()*10);
        super.setFavorite(random.nextBoolean());
        //45.69367953108681, 25.538717089037608
       // 45.70114024963515, 25.667332433354048
      //  45.63032417299258, 25.675416001893144
      //  45.61568575752174, 25.583032365439777
     //   45.61568575752174+random.nextFloat()*(45.70114024963515-45.61568575752174);
        super.setLon((float) (45.61568575752174+random.nextFloat()*(45.70114024963515-45.61568575752174)));
        super.setLat((float)(25.538717089037608+random.nextFloat()*(25.675416001893144-25.538717089037608)));
    }
    @Override
    public List<Product> getProducts()
    {
        List<Product> list= new ArrayList<>();
        for(int i=0;i<random.nextInt(25)+25;i++)
        list.add(new com.example.fooddeliveryapp.models.test.Product());
        return list;
    }
}
