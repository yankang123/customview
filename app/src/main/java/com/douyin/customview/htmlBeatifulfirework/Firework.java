package com.douyin.customview.htmlBeatifulfirework;

import java.util.Random;

public class Firework {
    private float x;
    private float y;
    private float radius;
    private float speed;
    private Random random =new Random();
    public Firework(float x, float y) {
        this.x = x;
        this.y = y;
        this.radius = 2;
        this.speed = 2 + random.nextFloat() * 2;
    }

    public void update() {
        y -= speed;
        radius *= 0.98f;
    }

    public boolean isDone() {
        return radius <= 0;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }
}