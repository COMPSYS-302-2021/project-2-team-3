package com.example.a302projecct2.dataprovider;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.a302projecct2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DataProviderClass {

    private CategoryClass[] cuisines;
    //ItemClass[][] allItems;
    private ArrayList<ItemClass[]> allDishes;


    public DataProviderClass(Context ctx) {

        //Allows us to use getResources function
        AppCompatActivity ac = new AppCompatActivity();

        //TODO: Assign values and create dataset for both categories and items
        cuisines = new CategoryClass[]{
                new CategoryClass("Japanese", "https://cdn.icon-icons.com/icons2/230/PNG/256/Japan_JP_JPN_392_Flag1_26102.png"),
                new CategoryClass("Indian", "https://cdn.icon-icons.com/icons2/2087/PNG/512/india_icon_127891.png"),
                new CategoryClass("Italian", "https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/256/Italy-Flag-icon.png")
        };


        //Maybe try for loops to store data, need size of array for each item
        //String[] japaneseDishNames = ac.getResources().getStringArray(R.array.japanese_dishes);
        ItemClass[] japaneseDishes = generateData(ctx, "japanese_dishes");
        ItemClass[] italianDishes = generateData(ctx, "italian_dishes");
        ItemClass[] indianDishes = generateData(ctx, "indian_dishes");









    }



    public String LoadJsonFromAsset(Context ctx){
        String json = null;
        try{
            InputStream in = ctx.getAssets().open("dishes.json");
            int size = in.available();
            byte[] bbuffer = new byte[size];
            in.read(bbuffer);
            in.close();
            json= new String(bbuffer, "UTF-8");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

    //Function is called to get the cuisine information we want
    public ItemClass[] generateData(Context ctx, String cusine){

        List<ItemClass> dishes = new ArrayList<ItemClass>();
        try {
            JSONObject obj = new JSONObject(LoadJsonFromAsset(ctx));
            JSONArray array = obj.getJSONArray(cusine);

            for(int i=0; i<array.length(); i++){
                JSONObject dish = array.getJSONObject(i);
                dish.getJSONArray("dishImages").toString();
                ItemClass itemDish = new ItemClass(dish.getString("dishName"),
                            dish.getString("dishDescription"),
                            dish.getString("dishPrice"),
                            conv2StringArr(dish.getJSONArray("dishImages")));
                dishes.add(itemDish);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return (ItemClass[]) dishes.toArray();
    }

    //Used to convert JSONArray of images to a String array
    public String[] conv2StringArr(JSONArray jsonArray){
        List<String> list = new ArrayList<String>();

        for(int i=0; i < jsonArray.length(); i++) {
            try {
                list.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list.toArray(new String[list.size()]);
    }


}







