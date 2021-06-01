package com.example.a302projecct2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imgLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//
//        Intent intent  = new Intent(this, Homepage.class);
//        startActivity(intent);
        imgLoading = findViewById(R.id.imgLoading);
//        imgLoading.setImageResource(R.drawable.feastcuisine);


    }
}