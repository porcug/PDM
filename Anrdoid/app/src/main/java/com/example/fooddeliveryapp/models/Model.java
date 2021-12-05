package com.example.fooddeliveryapp.models;

import com.google.gson.Gson;

public abstract class Model {
        public abstract String toJson();
        protected String toJson(Object o)
        {
            Gson gson= new Gson();
           return gson.toJson(o);
        }

}
