package com.example.a302projecct2;

import androidx.appcompat.app.AppCompatActivity;
import com.example.a302projecct2.dataprovider.*;

import android.os.Bundle;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        DataProviderClass data = new DataProviderClass(getBaseContext());

        
    }
}