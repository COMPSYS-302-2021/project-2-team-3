package com.example.a302projecct2.dataprovider;

import android.content.Context;

import com.example.a302projecct2.JsonFuncs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataProviderClass {

    private CategoryClass[] cuisinesCategories;
    private ArrayList<ArrayList<ItemClass>> allDishes;
    private String[] cusines = {"japanese_dishes", "indian_dishes", "italian_dishes"};


    public DataProviderClass(Context ctx) {
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

        //Saving all of the arrays of cusines into one array list
//        for(int i=0; i<cusines.length; i++){
//            if(generateData(ctx, cusines[i]) == null){
//                System.out.println("Value is null");
//            }
//            System.out.println((generateData(ctx, cusines[i])).toString());
//            allDishes.add(generateData(ctx, cusines[i])); //Issue here
//        }
    }

    //Randomly generate top dishes
    public ItemClass[] getTopDishes(){
        ItemClass[] topDishes = new ItemClass[allDishes.size()];
        int pos = (int) (Math.random()*getLongestArrayVal(allDishes));

        //Save item from each cusine into one array for top picks
        for(int i=0; i<allDishes.size(); i++){
            topDishes[i] = allDishes.get(i).get(pos);
        }
        return topDishes;

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







