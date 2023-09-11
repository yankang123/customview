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
    int x = 300;
    int y = 300;
int radius =30;
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
        paint = new Paint(); //设置一个笔刷大小是3的红色的画笔
        paint.setColor(Color.RED);
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
        if (canvas != null) {
            for (int i = 0; i < count; i++) {
                double angle = 360.0 / count * i;
                double radians = Math.toRadians(angle);
                int moveX = (int) (x + Math.cos(radians) * radius);
                int moveY = (int) (y + Math.sin(radians) * radius);

                canvas.drawCircle(moveX, moveY, 5, paint);
            }

        }
    }
public void setRadius(int radius){
        this.radius = radius;
}

}
