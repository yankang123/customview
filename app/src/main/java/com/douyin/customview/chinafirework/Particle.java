package com.douyin.customview.chinafirework;
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
    private float alpha = 255;
    private boolean burnedOut = false;

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
        alpha -= 5;
        if (alpha <= 0) {
            burnedOut = true;
        }
    }

    public boolean isBurnedOut() {
        return burnedOut;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setAlpha((int) alpha);
        rectF.set(x - 5, y - 5, x + 5, y + 5);
        canvas.drawOval(rectF, paint);
    }
}
