package com.example.fooddeliveryapp.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public Order(Restaurant restaurant)
    {
        this.restaurant=restaurant;
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Products> getProductList() {
        return productList;
    }

    public boolean isShow_list() {
        return show_list;
    }
    public float getPrice()
    {
        float price =0.0f;
        for ( Products t:
             productList) {
            price+=(t.getNumber()*t.getProduct().getPrice());
        }
        return price;
    }
    public void setShow_list(boolean show_list) {
        this.show_list = show_list;
    }
    public int getCount()
    {
        int nr=0;
        for (Products p:
                productList ) {
            nr+=p.getNumber();
        }
        return nr;
    }

    public boolean show_list =false;
    private Restaurant restaurant;
    private List<Products> productList= new ArrayList<>();
    public void Add(Product product,int ammount)
    {
        for (Products p:
            productList ) {
            if(p.getProduct()==product)
            {
                p.addAmount(ammount);
                return;
            }
        }
        productList.add(new Products(product,ammount));
    }
    public class Products {
        public Product getProduct() {
            return product;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        Product product;
        int number;
        Products(Product p)
        {
            this.product=p;
        }
        Products(Product p,int nr)
        {
            this.product=p;
            number =nr;
        }
        public void addAmount(int ammount)
        {
            number+=ammount;
            if(number<0)
                number=0;
        }
    }

}
