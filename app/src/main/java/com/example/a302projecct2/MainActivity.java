package com.example.a302projecct2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] info = getResources().getStringArray(R.array.a);
//                String item = info[0];
                System.out.println(info[0]);
                System.out.println(info[0].getClass().getSimpleName());
            }
        });
    }
}