package com.example.a302projecct2.dataprovider;

import android.content.Context;

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
        allDishes = new ArrayList<ArrayList<ItemClass>>();
        ArrayList<ItemClass> japanese = generateData(ctx, "japanese_dishes");
//        ArrayList<ItemClass> indian = generateData(ctx, "indian_dishes");
//        ArrayList<ItemClass> italian = generateData(ctx, "italian_dishes");
        System.out.println("japanese: " + japanese.size());
//        System.out.println("indian: " + indian.size());
//        System.out.println("italian: " + italian.size());


        //Adding data is the issue
        allDishes.add(japanese);
//        allDishes.add(indian);
//        allDishes.add(italian);

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
    public ArrayList<ItemClass> generateData(Context ctx, String cusine){

        ArrayList<ItemClass> dishes = new ArrayList<ItemClass>();
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

        return dishes;
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







