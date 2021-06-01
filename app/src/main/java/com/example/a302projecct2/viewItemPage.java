package com.example.a302projecct2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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
    ViewPager mViewPager;
    String[] images;
    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_page);
        images = getIntent().getStringArrayExtra("itemImages");
        ViewItemsViewHolder vh = new ViewItemsViewHolder();

        //Instance of Dataprovider class
        DataProviderClass data = new DataProviderClass(getBaseContext());

        mViewPager = (ViewPager)findViewById(R.id.ImageSlider);
        mViewPagerAdapter = new ViewPagerAdapter(viewItemPage.this,images);
        mViewPager.setAdapter(mViewPagerAdapter);

        vh.ItemTitle.setText(getIntent().getStringExtra("itemName"));
        vh.ItemDescription.setText(getIntent().getStringExtra("itemDescription"));
        vh.ItemPrice.setText(getIntent().getStringExtra("itemPrice"));

    }
    @Override
    public void onBackPressed () {
        Connectivity Con= new Connectivity(getBaseContext());
        if (!Con.isConnected()) {
            //showNotConnectedDialog();
            Toast.makeText(getBaseContext(), "Cannot connect to the internet", Toast.LENGTH_LONG).show();
        }
        else{
            String PreviousPage = getIntent().getStringExtra("prevPage");
            System.out.println(PreviousPage);
            if (PreviousPage.equals("Search")){
                Intent intent = new Intent(getBaseContext(),Homepage.class);
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