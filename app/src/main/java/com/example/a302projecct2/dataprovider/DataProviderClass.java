package com.example.a302projecct2.dataprovider;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.a302projecct2.JsonFuncs;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DataProviderClass {

    private CategoryClass[] cuisinesCategories;
    private ArrayList<ArrayList<ItemClass>> allDishes;
    private ArrayList<ItemClass> topPicks;
    private String[] cusines = {"japanese_dishes", "indian_dishes", "italian_dishes"};
    private SharedPreferences prefs;
    private SharedPreferences topPicksPrefs;
    private Context ctx;


    public DataProviderClass(Context ctx) {
        this.ctx = ctx;
        cuisinesCategories = new CategoryClass[]{
                new CategoryClass("Japanese", "https://cdn.icon-icons.com/icons2/230/PNG/256/Japan_JP_JPN_392_Flag1_26102.png"),
                new CategoryClass("Indian", "https://cdn.icon-icons.com/icons2/2087/PNG/512/india_icon_127891.png"),
                new CategoryClass("Italian", "https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/256/Italy-Flag-icon.png")
        };
        JsonFuncs funcs = new JsonFuncs(ctx);
        allDishes = new ArrayList<ArrayList<ItemClass>>();
        ArrayList<ItemClass> japanese = funcs.generateData(ctx, "japanese_dishes");
        ArrayList<ItemClass> indian = funcs.generateData(ctx, "indian_dishes");
        ArrayList<ItemClass> italian = funcs.generateData(ctx, "italian_dishes");


        //Adding data is the issue
        allDishes.add(japanese);
        allDishes.add(indian);
        allDishes.add(italian);

        topPicks = new ArrayList<ItemClass>();
        topPicks.add(allDishes.get(0).get(0));
        topPicks.add(allDishes.get(1).get(0));
        topPicks.add(allDishes.get(2).get(0));
        System.out.println("topPicks from constructor length" + topPicks.size());

        topPicksPrefs = ctx.getSharedPreferences("topPicks", MODE_PRIVATE);
        SharedPreferences.Editor editor = topPicksPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(topPicks);
        editor.putString("topDishes", json);
        editor.apply();




        //Saving all of the arrays of cusines into one array list
//        for(int i=0; i<cusines.length; i++){
//            if(generateData(ctx, cusines[i]) == null){
//                System.out.println("Value is null");
//            }
//            System.out.println((generateData(ctx, cusines[i])).toString());
//            allDishes.add(generateData(ctx, cusines[i])); //Issue here
//        }
    }


    public CategoryClass[] getCusinesCategories(){
        return this.cuisinesCategories;
    }

    public  ArrayList<ArrayList<ItemClass>> getAllDishes(){
        return this.allDishes;
    }

    //Gets the longest array of food items
    public int getLongestArrayVal(ArrayList<ArrayList<ItemClass>> arr){
        int[] lengths = new int[arr.size()];
        for (int i=0; i<arr.size(); i++){
            lengths[i] = arr.get(i).size();
        }
        Arrays.sort(lengths);
        return lengths[lengths.length - 1];


    }
}







