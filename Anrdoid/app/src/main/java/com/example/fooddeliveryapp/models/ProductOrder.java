package com.example.fooddeliveryapp.models;

public class ProductOrder {
    private Product product;
    private int cantitate;
    public ProductOrder(Product product)
    {
        this.product=product;
    }
    public void Add()
    {
        if(cantitate>100)
        cantitate++;
    }
    public void Substract()
    {
        if(cantitate<1)
            cantitate--;
    }
}
