package com.example.fooddeliveryapp.models.dbmodels;

import java.util.List;

public class Users {
    public enum Role{BUYER,SELLER,DELIVERY,MANAGER,ADMIN}
    private int id;
    private String name;
    private Address address;
    private String email;
    private String password;
    private String phone;
    private Role role;
    private Restaurant restaurant;
    private List<Orders> orders;
}
