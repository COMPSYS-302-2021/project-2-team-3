package com.example.a302projecct2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 3000;
    private ImageView imgLoading;
    private boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        System.out.println("Starting application");
//        Connectivity conn = new Connectivity(getBaseContext());
//        while (!connected){
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if(conn.isConnected()){
//                        connected = true;
//                        System.out.println("Connected to internet");
//                    }
//                }
//            }, SPLASH_TIME);
//        }
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);




    }
}