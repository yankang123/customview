package com.douyin.customview.surfacefirework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.Random;

public class Firework {
    private float x;
    private float y;
    private float speedX;
    private float speedY;
    private int color;
    private Path path = new Path();
    private boolean burnedOut = false;

    public Firework(int maxX, int maxY) {
        Random random = new Random();
        x = random.nextInt(maxX);
        y = random.nextInt(maxY / 2) + maxY / 2; // 仅在视图的底半部分显示

        color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        float angle = (float) (random.nextInt(360) * Math.PI / 180);
        float speed = random.nextFloat() * 10 + 5;
        speedX = (float) (speed * Math.cos(angle));
        speedY = (float) (speed * Math.sin(angle));
    }

    public void update() {
        x += speedX;
        y += speedY;
        if (y < 0) {
            burnedOut = true;
        }
    }

    public boolean isBurnedOut() {
        return burnedOut;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        path.reset();
        path.moveTo(x, y);
        float tailX = x - speedX * 5; // 尾迹长度
        float tailY = y - speedY * 5; // 尾迹长度
        path.lineTo(tailX, tailY);
        canvas.drawPath(path, paint);
    }
}
