package com.douyin.customview.flower;

import android.os.Bundle;
import android.os.Handler;

import com.douyin.customview.R;

import androidx.appcompat.app.AppCompatActivity;

public class FlowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);
        HeartView heartView = findViewById(R.id.heartView);
        Handler handler = new Handler();
        for (int i=0;i<10;i++)
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                heartView.reDraw();
            }
        }, 3000);

    }

}