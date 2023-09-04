package com.douyin.customview.flower;

import android.os.Bundle;

import com.douyin.customview.R;

import androidx.appcompat.app.AppCompatActivity;

public class FlowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);
        HeartView heartView = findViewById(R.id.heartView);

    }
}