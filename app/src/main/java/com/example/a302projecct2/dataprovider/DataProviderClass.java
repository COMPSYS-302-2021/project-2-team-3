package com.example.a302projecct2.dataprovider;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.a302projecct2.JsonFuncs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
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
    private SharedPreferences.Editor editor;
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

        prefs = ctx.getSharedPreferences("com.example.a302projecct2", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            prefs.edit().putBoolean("firstrun", false).apply();
            Toast.makeText(ctx, "FIRST RUN", Toast.LENGTH_SHORT).show();
            topPicks = new ArrayList<ItemClass>();
            topPicks.add(allDishes.get(0).get(0));
            topPicks.add(allDishes.get(1).get(0));
            topPicks.add(allDishes.get(2).get(0));
            System.out.println("topPicks from constructor length" + topPicks.size());

            topPicksPrefs = ctx.getSharedPreferences("topPicks", MODE_PRIVATE);
            editor = topPicksPrefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(topPicks);
            editor.putString("topDishes", json);
            editor.apply();
        }


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

    /**
     * Get current top dishes item
     * @return
     */
    public ArrayList<ItemClass> getTopDishes(){
        SharedPreferences topPicksPref;
        ArrayList<ItemClass> topDishes = new ArrayList<ItemClass>();
        Gson gson = new Gson();
        topPicksPref = ctx.getSharedPreferences("topPicks", MODE_PRIVATE);
        String json = topPicksPref.getString("topDishes", null);
        Type type = new TypeToken<ArrayList<ItemClass>>(){}.getType();
        return gson.fromJson(json, type);
    }

    /**
     * Updates the arraylist for top dishes
     */
    public void updateTopDishes(ItemClass selectedItem){

        ArrayList<ItemClass> updatedTopPicks = new ArrayList<ItemClass>();
        Boolean containsItem = false;

        //Getting current top picks
        ArrayList<ItemClass> topDishes = new ArrayList<ItemClass>();
        topDishes = getTopDishes();


        //Check if current top dishes contains the item that was bought
        for(int i=0; i<topDishes.size(); i++){
            if(topDishes.get(i).getItemName().equals(selectedItem.getItemName())){
                containsItem = true;
                selectedItem = topDishes.get(i);
                topDishes.remove(i);
                break;
            }
        }

        //update the top picks variable
        if(containsItem){
            updatedTopPicks.add(selectedItem);
            updatedTopPicks.addAll(topDishes);
        }else{

            updatedTopPicks.add(selectedItem);
            for(int i=0; i<topDishes.size()-1; i++){
                updatedTopPicks.add(topDishes.get(i));
            }

        }

        /**
         * Commiting back to shared preferences
         */
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("topPicks", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(updatedTopPicks);
        editor.putString("topDishes", json);
        editor.apply();

    }


}









