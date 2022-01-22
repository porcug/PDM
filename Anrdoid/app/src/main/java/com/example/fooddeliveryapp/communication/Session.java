package com.example.fooddeliveryapp.communication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.fooddeliveryapp.activitys.Client_Activity;
import com.example.fooddeliveryapp.activitys.Login_Activity;
import com.example.fooddeliveryapp.models.Login_model;
import com.example.fooddeliveryapp.models.Model;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Session {




    public static void login(String email, String password, Boolean save) throws Exception {

        throw new Exception("Wrong Username and password combination");
    }
    public static List<Model>getList(String url)
    {
        List<Model>result = new ArrayList<>();
        return result;
    }

    public static Intent getActivity(Context context )
    {
        Intent intent =new Intent(context, Client_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
       return intent;
    }

    public static void autoLogin(Activity c)
    {
        File cred= new File(c.getFilesDir(),"/data.cred");
        try {
             Gson gson =new Gson();
           Login_model credin;
          FileReader reader= new FileReader(cred);
            credin = gson.fromJson(reader, Login_model.class);
           Session.login(credin.getEmail(),credin.getPassword(),true);


        } catch (Exception  e) {
            Intent intent =new Intent(c, Login_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(intent);
            return;
        }
        c.startActivity(Session.getActivity(c));


    }

    public static void recovery_st1(String email)
    {}

}
