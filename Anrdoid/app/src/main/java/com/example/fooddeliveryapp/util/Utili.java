package com.example.fooddeliveryapp.util;



import com.example.fooddeliveryapp.models.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utili {
    public static boolean isValidEmail(String email)
    {
        String regex="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }
    public static List<String>getGroups(List<? extends Model>from,String selector)
    {
        List<String> templist =new ArrayList<>();
        if(from==null)
        {
            return templist;
        }
        if(from.size()==0)
        {
            return templist;
        }
        Comparator<? extends  Model> comparator =from.get(0).getComparator(selector);
        for (Model temp:from) {
            String aux= temp.getComparatorValue(selector);
            if(!templist.contains(aux))
                templist.add(aux);
        }
        templist.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
             return  o1.compareTo(o2);
            }
        });
        List<String> result=new ArrayList<>();
        if(templist.size()>20) {
            for(float step =0.0f;step<(templist.size()-((templist.size()-1) / 21.0f))
                    ;step += (float) ((templist.size()-1) / 21.0f))
                result.add(templist.get((int)step));
        }
        else return templist;
        return result;
    }
    public static String capitalizeString(String str) {
        String retStr = str;
        try { // We can face index out of bound exception if the string is null
            retStr = str.substring(0, 1).toUpperCase() + str.substring(1);
        }catch (Exception e){}
        return retStr;
    }

}
