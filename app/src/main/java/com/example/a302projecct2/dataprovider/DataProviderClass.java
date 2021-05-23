package com.example.a302projecct2.dataprovider;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.a302projecct2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class DataProviderClass {

    private CategoryClass[] cuisines;
    //ItemClass[][] allItems;
    private ArrayList<ItemClass[]> allDishes;


    public DataProviderClass() {

        //Allows us to use getResources function
        AppCompatActivity ac = new AppCompatActivity();

        //TODO: Assign values and create dataset for both categories and items
        cuisines = new CategoryClass[]{
                new CategoryClass("Japanese", "https://cdn.icon-icons.com/icons2/230/PNG/256/Japan_JP_JPN_392_Flag1_26102.png"),
                new CategoryClass("Indian", "https://cdn.icon-icons.com/icons2/2087/PNG/512/india_icon_127891.png"),
                new CategoryClass("Italian", "https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/256/Italy-Flag-icon.png")
        };


        //Maybe try for loops to store data, need size of array for each item
        String[] japaneseDishNames = ac.getResources().getStringArray(R.array.japanese_dishes);










    }

}





