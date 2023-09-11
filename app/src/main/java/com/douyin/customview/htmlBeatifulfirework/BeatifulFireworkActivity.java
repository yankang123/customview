package com.douyin.customview.htmlBeatifulfirework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceView;

import com.douyin.customview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BeatifulFireworkActivity extends AppCompatActivity {

    private Random random;
    private FireworksView fireworksView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beatiful_firework);
        fireworksView = findViewById(R.id.surfaceView);


    }



}