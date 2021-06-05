package com.example.a302projecct2;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a302projecct2.dataprovider.DataProviderClass;
import com.example.a302projecct2.dataprovider.ItemClass;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private String searchQuery;
    private ArrayList<ArrayList<ItemClass>> allDishes;
    private DataProviderClass data;
    private ArrayList<ItemClass> searchResults;

    public class searchViewHolder{
        private TextView txtSearch = findViewById(R.id.txtSearch);
        private RecyclerView RclSearch = findViewById(R.id.RclSearch);
    }



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchViewHolder vh = new searchViewHolder();
        data = new DataProviderClass(getBaseContext());
        searchResults = new ArrayList<ItemClass>();

        //Getting search query from shared preferences
        SharedPreferences SearchPref = getApplicationContext().getSharedPreferences("SearchQuery", Context.MODE_PRIVATE);
        searchQuery = SearchPref.getString("Query","");

        allDishes  = data.getAllDishes();

        //Search through all dishes to find dish that contains that name
        for(int i = 0; i<allDishes.size(); i++){
            ArrayList<ItemClass> cuisine = allDishes.get(i);
            for (ItemClass itemClass : cuisine) {
                if (itemClass.getItemName().toLowerCase().contains(searchQuery.toLowerCase())) {
                    searchResults.add(itemClass);
                }
            }
        }

        //If there are no results show message
        //else populate recyclerview of search results
        if(searchResults.size() == 0){
            vh.txtSearch.setText("No search results for \n" + searchQuery);
        }
        else{
            SearchActivityAdapter searchAdapter = new SearchActivityAdapter(getBaseContext(), searchResults);
            vh.RclSearch.setAdapter(searchAdapter);
            LinearLayoutManager SearchManager= new LinearLayoutManager(this);
            SearchManager.setOrientation(LinearLayoutManager.VERTICAL);
            vh.RclSearch.setLayoutManager(SearchManager);
            vh.txtSearch.setText("Search results for \n" + searchQuery);
        }
    }

    /**
     * Used to check when going back to a previous page
     * It checks whether the device is connected to the
     * internet before going back to the according page it came from
     */
    public void onBackPressed () {
        Connectivity Con= new Connectivity(getBaseContext());
        if (!Con.isConnected()) {
            Toast.makeText(getBaseContext(), "Cannot connect to the internet", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(getBaseContext(),Homepage.class);
            startActivity(intent);

        }
    }
}