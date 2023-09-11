package com.douyin.customview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.douyin.customview.htmlBeatifulfirework.BeatifulFireworkActivity;
import com.douyin.customview.view.BombView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private BombView mBombView;

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            mBombView.startBomb();
            getUIHandler().postDelayed(task, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, BeatifulFireworkActivity.class);
        startActivity(intent);
//        mBombView = (BombView) findViewById(R.id.bombview);
//
//        getUIHandler().postDelayed(task, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBombView.release();
    }

    public Handler getUIHandler() {
        Handler handler = this.getWindow().getDecorView().getHandler();
        if (handler == null) {
            handler = new Handler();
        }
        return handler;
    }
}