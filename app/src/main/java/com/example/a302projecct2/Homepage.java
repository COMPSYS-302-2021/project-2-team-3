package com.example.a302projecct2;

import android.os.Bundle;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        viewholder vh = new viewholder();

        DataProviderClass data = new DataProviderClass(getBaseContext());
        TopPicksRecAdapter TopPicksAdapter = new TopPicksRecAdapter(data.getTopDishes(),getBaseContext());
        CategoryItemRecAdapter CatRecAdapter = new CategoryItemRecAdapter(getBaseContext(),data.getAllDishes(),data.getCusinesCategories());
        vh.RclTopDishes.setAdapter(TopPicksAdapter);
        LinearLayoutManager TopDishesManager = new LinearLayoutManager(this);
        TopDishesManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        vh.RclTopDishes.setLayoutManager(TopDishesManager);

        vh.RclCategories.setAdapter(CatRecAdapter);


        LinearLayoutManager CategoriesManager= new LinearLayoutManager(this);
        CategoriesManager.setOrientation(LinearLayoutManager.VERTICAL);
        vh.RclCategories.setLayoutManager(CategoriesManager);
    }

}