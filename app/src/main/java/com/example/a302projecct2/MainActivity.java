package com.example.a302projecct2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent  = new Intent(this, Homepage.class);
        startActivity(intent);

//        btn = findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String[] info = getResources().getStringArray(R.array.a);
////                String item = info[0];
//                System.out.println(info[0]);
//                System.out.println(info[0].getClass().getSimpleName());
//            }
//        });
    }
}