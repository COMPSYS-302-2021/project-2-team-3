package com.example.a302projecct2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Constructor takes in search term and we create instance of dataprovider and search through all
        //the names to find the dish that we are looking for whatever matches then save them in a array
        //of itemclass which is then passed into the intent of the
    }
}