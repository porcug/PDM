package com.example.fooddeliveryapp.models.dbmodels;

import java.util.List;

public class Orders {
    public enum Status{CREATED,SEND,REFUSED,RECIVED,CONFIRMED,IN_PROGRESS,TO_CLIENT,DELIVERED};
    private int id;
    private String code;
    private String confirmCode;
    private Status status;
    private Address delivery_address;
    private List<Products> productsList;

}
