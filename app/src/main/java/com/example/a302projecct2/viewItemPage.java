package com.example.a302projecct2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.a302projecct2.dataprovider.DataProviderClass;

public class viewItemPage extends AppCompatActivity {
    public class ViewItemsViewHolder {

        private ViewPager ImageSlider = findViewById(R.id.ImageSlider);
        private TextView ItemTitle = findViewById(R.id.ItemTitle);
        private TextView ItemDescription = findViewById(R.id.ItemDescription);
        private TextView ItemPrice = findViewById(R.id.ItemPrice);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_page);
        ViewItemsViewHolder vh = new ViewItemsViewHolder();

        //Instance of Dataprovider class
        DataProviderClass data = new DataProviderClass(getBaseContext());

        vh.ItemTitle.setText(getIntent().getStringExtra("itemName"));
        vh.ItemDescription.setText(getIntent().getStringExtra("itemDescription"));
        vh.ItemPrice.setText(getIntent().getStringExtra("itemPrice"));

    }

}