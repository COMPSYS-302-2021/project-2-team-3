package com.example.a302projecct2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a302projecct2.dataprovider.DataProviderClass;
import com.example.a302projecct2.dataprovider.ItemClass;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private String searchQuery;
    private ArrayList<ItemClass[]> allDishes;
    private DataProviderClass data;
    private ArrayList<ItemClass> searchResults;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        //Constructor takes in search term and we create instance of dataprovider and search through all
        //the names to find the dish that we are looking for whatever matches then save them in a array
        //of itemclass which is then passed into the intent of the

        searchQuery = getIntent().getStringExtra("searchQuery");
        allDishes  = data.getAllDishes();

        //Search through all dishes to find dish that contains that name
        //Maybe add a field that contains an array of "tags" for each item
        for(int i = 0; i<allDishes.size(); i++){
            ItemClass[] cuisine = allDishes.get(i);
            for (ItemClass itemClass : cuisine) {
                if (itemClass.getItemName().contains(searchQuery)) {
                    searchResults.add(itemClass);
                }
            }
        }

        //If the length of searchResults is 0 then show textview saying "No Search Results"
        //and set recycler view as gone, otherwise set textxview to gone and show results
        //Can maybe use same recyclerview adapter from list activity




    }
}