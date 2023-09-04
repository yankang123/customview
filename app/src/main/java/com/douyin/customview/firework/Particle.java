package com.douyin.customview.firework;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Particle {
    private float x;
    private float y;
    private final float speedX;
    private final float speedY;
    private final int color;
    private final RectF rectF = new RectF();

    public Particle(float x, float y, float angle, float speed, int color) {
        this.x = x;
        this.y = y;
        this.speedX = (float) (speed * Math.cos(Math.toRadians(angle)));
        this.speedY = (float) (speed * Math.sin(Math.toRadians(angle)));
        this.color = color;
    }

    public void move() {
        x += speedX;
        y += speedY;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        rectF.set(x - 5, y - 5, x + 5, y + 5);
        canvas.drawOval(rectF, paint);
    }
}
