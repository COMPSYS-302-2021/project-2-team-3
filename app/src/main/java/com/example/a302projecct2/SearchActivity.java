package com.example.a302projecct2;

import android.annotation.SuppressLint;
import android.content.Intent;
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
        //Constructor takes in search term and we create instance of dataprovider and search through all
        //the names to find the dish that we are looking for whatever matches then save them in a array
        //of itemclass which is then passed into the intent of the

        searchQuery = getIntent().getStringExtra("searchQuery");
        allDishes  = data.getAllDishes();

        //Search through all dishes to find dish that contains that name
        //Maybe add a field that contains an array of "tags" for each item
        for(int i = 0; i<allDishes.size(); i++){
            ArrayList<ItemClass> cuisine = allDishes.get(i);
            for (ItemClass itemClass : cuisine) {
                if (itemClass.getItemName().contains(searchQuery)) {
                    searchResults.add(itemClass);
                }
            }
        }

        if(searchResults.size() == 0){
            vh.txtSearch.setText("No search results for \n" + searchQuery);
        }
        else{
            SearchActivityAdapter searchAdapter = new SearchActivityAdapter(getBaseContext(), searchResults);
            vh.RclSearch.setAdapter(searchAdapter);
            LinearLayoutManager SearchManager= new LinearLayoutManager(this);
            SearchManager.setOrientation(LinearLayoutManager.VERTICAL);
            vh.RclSearch.setLayoutManager(SearchManager);
        }



    }
    @Override
    public void onBackPressed () {
        Connectivity Con = new Connectivity(getBaseContext());
        if (!Con.isConnected()) {
            //showNotConnectedDialog();
            Toast.makeText(getBaseContext(), "Cannot connect to the internet", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(getBaseContext(),Homepage.class);
            startActivity(intent);
        }
    }
}