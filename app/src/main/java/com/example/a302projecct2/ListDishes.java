package com.example.a302projecct2;

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

public class ListDishes extends AppCompatActivity {
    public class ListActivityViewHolder {

        private TextView CuisineName = findViewById(R.id.CuisineName);
        private RecyclerView RclDishes = findViewById(R.id.RclDishes);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListActivityViewHolder vh = new ListActivityViewHolder();

        SharedPreferences ShPref = getApplicationContext().getSharedPreferences("categoryName", Context.MODE_PRIVATE);
        String categoryName = ShPref.getString("Name","");
        
        //Instance of Dataprovider class
        DataProviderClass data = new DataProviderClass(getBaseContext());
        int pos = ShPref.getInt("cuisinePos", 0);
        ArrayList<ItemClass>  list = data.getAllDishes().get(pos);
        ListActivityRecAdapter Dishes = new ListActivityRecAdapter(getBaseContext(), list);


        //Setting up recycler view for top dishes
        vh.RclDishes.setAdapter(Dishes);
        LinearLayoutManager DishesManager = new LinearLayoutManager(this);
        DishesManager.setOrientation(LinearLayoutManager.VERTICAL);
        vh.RclDishes.setLayoutManager(DishesManager);
        vh.CuisineName.setText(categoryName);

        //Setting up recycler view for categories

        /**
         * If user searches for dish go to SearchActivity and show results*/

    }

    @Override
    public void onBackPressed () {
        Connectivity Con= new Connectivity(getBaseContext());
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