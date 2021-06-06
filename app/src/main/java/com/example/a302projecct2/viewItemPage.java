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

        private final ViewPager ImageSlider = findViewById(R.id.ImageSlider);
        private final TextView ItemTitle = findViewById(R.id.ItemTitle);
        private final TextView ItemDescription = findViewById(R.id.ItemDescription);
        private final TextView ItemPrice = findViewById(R.id.ItemPrice);
        private final Button buyButton = findViewById(R.id.buyButton);

    }
    ViewPager mViewPager;
    String[] images;
    ViewPagerAdapter mViewPagerAdapter;
    String itemName, itemPrice, itemDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_page);

        ViewItemsViewHolder vh = new ViewItemsViewHolder();

        //Getting information passed in from intents
        itemName = getIntent().getStringExtra("itemName");
        itemDescription = getIntent().getStringExtra("itemDescription");
        itemPrice = getIntent().getStringExtra("itemPrice");
        images = getIntent().getStringArrayExtra("itemImages");


        //Instance of Dataprovider class
        DataProviderClass data = new DataProviderClass(getBaseContext());

        //Setting adapter for viewpager for showing images
        mViewPager = (ViewPager)findViewById(R.id.ImageSlider);
        mViewPagerAdapter = new ViewPagerAdapter(viewItemPage.this,images);
        mViewPager.setAdapter(mViewPagerAdapter);


        //Setting the information in the layout file
        vh.ItemTitle.setText(itemName);
        vh.ItemDescription.setText(itemDescription);
        vh.ItemPrice.setText(itemPrice);
        vh.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewItemPage.this, "Purchase Successful", Toast.LENGTH_SHORT).show();
                data.updateTopDishes(new ItemClass(itemName, itemDescription, itemPrice, images));
            }
        });

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