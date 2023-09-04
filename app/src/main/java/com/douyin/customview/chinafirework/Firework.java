package com.douyin.customview.chinafirework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Firework {
    private final float x;
    private final float y;
    private final int color;
    private final Particle[] particles;
    private boolean exploded = false;

    public Firework(int maxX, int maxY) {
        Random random = new Random();
        x = random.nextInt(maxX);
        y = 200;

        color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        particles = new Particle[50];
        for (int i = 0; i < 50; i++) {
            particles[i] = new Particle(x, y, random.nextInt(360), random.nextFloat() * 10, color);
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        if (!exploded) {
            for (Particle particle : particles) {
                particle.move();
                particle.draw(canvas, paint);
            }

            exploded = true;
        }
    }

    public boolean isBurnedOut() {
        return exploded && particles[0].isBurnedOut();
    }
}
