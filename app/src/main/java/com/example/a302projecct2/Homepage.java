package com.example.a302projecct2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a302projecct2.dataprovider.DataProviderClass;

public class Homepage extends AppCompatActivity {
    public class viewholder {
        private EditText SearchBarTxt = findViewById(R.id.SearchBarTxt);
        private ImageView SearchBtn = findViewById(R.id.SearchBtn);
        private RecyclerView RclTopDishes = findViewById(R.id.RclTopDishes);
        private RecyclerView RclCategories = findViewById(R.id.RclCategories);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        viewholder vh = new viewholder();

        //Instance of Dataprovider class
        DataProviderClass data = new DataProviderClass(getBaseContext());

        //Creating recycler view adapters for top dishes and category items
        TopPicksRecAdapter TopPicksAdapter = new TopPicksRecAdapter(data.getTopDishes(),getBaseContext());
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
        vh.SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = vh.SearchBarTxt.getText().toString();
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                intent.putExtra("searchQuery", searchQuery);
                //intent.putExtra("searchQuery", searchQuery);
                SharedPreferences SPref = getSharedPreferences("SearchQuery", Context.MODE_PRIVATE);
                SharedPreferences.Editor SearchEditor = SPref.edit();
                SearchEditor.putString("Query",searchQuery);
                SearchEditor.commit();
                startActivity(intent);
                /**
                 * Change it so we create data variable in searchACtivity class
                 */
            }
        });
    }
    @Override
    public void onBackPressed () {

    }


}