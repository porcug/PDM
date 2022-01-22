package com.example.fooddeliveryapp.models;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;

public abstract class Model {
        public abstract List<String> getGroups();
        public abstract Comparator<? extends Model> getComparator(String groupby);
        public  Comparator<? extends Model> getComparator() {
                return getComparator(getGroups().get(0));
        };
        public abstract String getComparatorValue(String groupby);
        public  String getComparatorValue()
        {
              return  getComparatorValue(getGroups().get(0));
        }

}
