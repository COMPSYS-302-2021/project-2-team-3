package com.example.a302projecct2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.a302projecct2.dataprovider.DataProviderClass;
import com.example.a302projecct2.dataprovider.ItemClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class viewItemPage extends AppCompatActivity {
    public class ViewItemsViewHolder {

        private ViewPager ImageSlider = findViewById(R.id.ImageSlider);
        private TextView ItemTitle = findViewById(R.id.ItemTitle);
        private TextView ItemDescription = findViewById(R.id.ItemDescription);
        private TextView ItemPrice = findViewById(R.id.ItemPrice);
        private Button buyButton = findViewById(R.id.buyButton);
    }
    ViewPager mViewPager;
    String[] images;
    ViewPagerAdapter mViewPagerAdapter;
    String itemName, itemPrice, itemDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_page);

        itemName = getIntent().getStringExtra("itemName");
        itemDescription = getIntent().getStringExtra("itemDescription");
        itemPrice = getIntent().getStringExtra("itemPrice");
        images = getIntent().getStringArrayExtra("itemImages");
        ViewItemsViewHolder vh = new ViewItemsViewHolder();

        //Instance of Dataprovider class
        DataProviderClass data = new DataProviderClass(getBaseContext());

        mViewPager = (ViewPager)findViewById(R.id.ImageSlider);
        mViewPagerAdapter = new ViewPagerAdapter(viewItemPage.this,images);
        mViewPager.setAdapter(mViewPagerAdapter);


        vh.ItemTitle.setText(itemName);
        vh.ItemDescription.setText(itemDescription);
        vh.ItemPrice.setText(itemPrice);
        vh.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewItemPage.this, "Purchase Successful", Toast.LENGTH_SHORT).show();

                /**
                 * Getting current top picks
                 */
                SharedPreferences topPicksPref;
                ArrayList<ItemClass> topDishes = new ArrayList<ItemClass>();
                //Creating recycler view adapters for top dishes and category items
                Gson gson = new Gson();
                topPicksPref = getSharedPreferences("topPicks", MODE_PRIVATE);
                String json = topPicksPref.getString("topDishes", null);
                Type type = new TypeToken<ArrayList<ItemClass>>(){}.getType();
                topDishes = gson.fromJson(json, type);

                /**
                 * Updating with the new item
                 */
                ArrayList<ItemClass> updatedTopPicks = new ArrayList<ItemClass>();
                ItemClass item = new ItemClass(itemName, itemDescription, itemPrice, images);
                updatedTopPicks.add(item);
                for(int i=0; i<topDishes.size()-1; i++){
                    updatedTopPicks.add(topDishes.get(i));
                }

                /**
                 * Commiting back to shared preferences
                 */
                SharedPreferences sharedPreferences = getSharedPreferences("topPicks", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                json = gson.toJson(updatedTopPicks);
                editor.putString("topDishes", json);
                editor.apply();

            }
        });

    }

    @Override
    public void onBackPressed () {
        Connectivity Con= new Connectivity(getBaseContext());
        if (!Con.isConnected()) {
            Toast.makeText(getBaseContext(), "Cannot connect to the internet", Toast.LENGTH_LONG).show();
        }
        else{
            String PreviousPage = getIntent().getStringExtra("prevPage");
            if (PreviousPage.equals("Search")){
                Intent intent = new Intent(getBaseContext(),SearchActivity.class);
                startActivity(intent);
            }
            else if(PreviousPage.equals("List")){
                Intent intent = new Intent(getBaseContext(),ListDishes.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getBaseContext(),Homepage.class);
                startActivity(intent);
            }
        }
    }


}