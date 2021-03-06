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
    public class ListDishesViewHolder {

        private final TextView CuisineName = findViewById(R.id.CuisineName);
        private final RecyclerView RclDishes = findViewById(R.id.RclDishes);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListDishesViewHolder vh = new ListDishesViewHolder();

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
        vh.CuisineName.setText(categoryName + "\n Cuisine");

    }

    /**
     * Used to check when going back to a previous page
     * It checks whether the device is connected to the
     * internet before going back to the according page it came from
     */
    @Override
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