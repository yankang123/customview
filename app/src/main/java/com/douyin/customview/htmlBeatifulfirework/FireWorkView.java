package com.douyin.customview.htmlBeatifulfirework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class FireWorkView extends View {
    Paint paint;
    int x = 100;
    int y = 100;

    public FireWorkView(Context context) {
        super(context);

        paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
    }

    public FireWorkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
    }

    public FireWorkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawFires(canvas);

    }

    public void drawFires(Canvas canvas) {
        // 初始半径，以及粒子数量
        int count = 10;
        int radius = 30;
        if (canvas != null) {
            for (int i = 0; i < count; i++) {
                double angle = 360.0 / count * i;
                double radians = Math.toRadians(angle);
                int moveX = (int) (x + Math.cos(radians) * radius);
                int moveY = (int) (y + Math.sin(radians) * radius);

                canvas.drawCircle(moveX, moveY, 2, paint);
            }

        }
    }


}
