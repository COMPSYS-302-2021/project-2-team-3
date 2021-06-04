package com.example.a302projecct2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a302projecct2.dataprovider.DataProviderClass;
import com.example.a302projecct2.dataprovider.ItemClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Homepage extends AppCompatActivity {
    public class viewholder {

        private RecyclerView RclTopDishes = findViewById(R.id.RclTopDishes);
        private RecyclerView RclCategories = findViewById(R.id.RclCategories);
        private SearchView searchView = findViewById(R.id.searchView);
        private SharedPreferences topPicksPref;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        viewholder vh = new viewholder();

        //Instance of Dataprovider class
        DataProviderClass data = new DataProviderClass(getBaseContext());

        ArrayList<ItemClass> topDishes = new ArrayList<ItemClass>();
        //Creating recycler view adapters for top dishes and category items
        Gson gson = new Gson();
        vh.topPicksPref = getSharedPreferences("topPicks", MODE_PRIVATE);
        String json = vh.topPicksPref.getString("topDishes", null);
        Type type = new TypeToken<ArrayList<ItemClass>>(){}.getType();
        topDishes = gson.fromJson(json, type);



        TopPicksRecAdapter TopPicksAdapter = new TopPicksRecAdapter(topDishes,getBaseContext());
        CategoryItemRecAdapter CatRecAdapter = new CategoryItemRecAdapter(getBaseContext(),data.getAllDishes(),data.getCusinesCategories());

        //Setting up recycler view for top dishes
        vh.RclTopDishes.setAdapter(TopPicksAdapter);
        LinearLayoutManager TopDishesManager = new LinearLayoutManager(this);
        TopDishesManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        vh.RclTopDishes.setLayoutManager(TopDishesManager);

        //Setting up recycler view for categories
        vh.RclCategories.setAdapter(CatRecAdapter);
        LinearLayoutManager CategoriesManager= new LinearLayoutManager(this);
        CategoriesManager.setOrientation(LinearLayoutManager.VERTICAL);
        vh.RclCategories.setLayoutManager(CategoriesManager);

        /**
         * If user searches for dish go to SearchActivity and show results
         */
        vh.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                SharedPreferences SPref = getSharedPreferences("SearchQuery", Context.MODE_PRIVATE);
                SharedPreferences.Editor SearchEditor = SPref.edit();
                SearchEditor.putString("Query",query);
                SearchEditor.commit();
                startActivity(intent);
                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

//        vh.SearchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String searchQuery = vh.SearchBarTxt.getText().toString();
//                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
//                //intent.putExtra("searchQuery", searchQuery);
//                SharedPreferences SPref = getSharedPreferences("SearchQuery", Context.MODE_PRIVATE);
//                SharedPreferences.Editor SearchEditor = SPref.edit();
//                SearchEditor.putString("Query",searchQuery);
//                SearchEditor.commit();
//                startActivity(intent);
//                /**
//                 * Change it so we create data variable in searchACtivity class
//                 */
//            }
//        });
    }
    @Override
    public void onBackPressed () {

    }


}