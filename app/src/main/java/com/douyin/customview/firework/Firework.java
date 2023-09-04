package com.douyin.customview.firework;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Firework {
    private final float x;
    private float y;
    private float speedY;
    private final int color;
    private final int maxParticles = 50;
    private final Particle[] particles;
    private boolean burnedOut = false;

    public Firework(int maxX, int maxY) {
        Random random = new Random();
        x = random.nextInt(maxX);
        y = maxY;

        color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        speedY = -random.nextFloat() * 10 - 5;

        particles = new Particle[maxParticles];
        for (int i = 0; i < maxParticles; i++) {
            particles[i] = new Particle(x, y, random.nextInt(360), random.nextFloat() * 5, color);
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        if (y <= 0) {
            burnedOut = true;
            return;
        }

        for (Particle particle : particles) {
            particle.move();
            particle.draw(canvas, paint);
        }

        y += speedY;
    }

    public boolean isBurnedOut() {
        return burnedOut;
    }
}
