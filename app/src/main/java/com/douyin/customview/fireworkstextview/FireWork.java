package com.douyin.customview.fireworkstextview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by shishaoyan on 2018/7/26.
 */

public class FireWork {

    private final String TAG = this.getClass().getSimpleName();
    private final static int DEFAULT_ELEMENT_COUNT = 12;// 默认 粒子的数量
    private final static float DEFAULT_ELEMENT_SIZE = 8;// 默认 粒子的尺寸
    private final static int DEFAULT_DURATION = 400;// 默认 动画间隔时间
    private final static float DEFAULT_LAUNCH_SPEED = 18;// 默认 粒子 加载时的 速度
    private final static float DEFAULT_WIND_SPEED = 6;// 默认 风的 素的
    private final static float DEFAULT_GRAVITY = 6;// 默认 重力大小

    private Paint mPaint;// 画笔

    private int count;// 粒子数量
    private int duration;// 间隔时间
    private int[] colors;// 颜色库
    private int color;

    private float launchSpeed;
    private int windDirection;// 1 or -1
    private float windSpeed;
    private float grivaty;
    private Location location;
    private float elemetSize;

    private ValueAnimator animator;
    private float animatorValue;

    private ArrayList<Element> elements = new ArrayList<Element>();
    private AnimationEndListener listener;

    public FireWork(Location location, int windDirection) {
        this.location = location;
        this.windDirection = windDirection;
        colors = baseColors;
        duration = DEFAULT_DURATION;
        grivaty = DEFAULT_GRAVITY;
        elemetSize = DEFAULT_ELEMENT_SIZE;
        launchSpeed = DEFAULT_LAUNCH_SPEED;
        windSpeed = DEFAULT_WIND_SPEED;
        count = DEFAULT_ELEMENT_COUNT;
        init();

    }

    private void init() {

        Random random = new Random();
        color = colors[random.nextInt(colors.length)];
        // 给每一个火花 设定一个随机的方向 0 - 180
        for (int i = 0; i < count; i++) {
            elements.add(new Element(color, Math.toRadians(random.nextInt(180)), random.nextFloat() * launchSpeed));
        }
        mPaint = new Paint();
        mPaint.setColor(color);

    }

    public void fire() {
        animator = ValueAnimator.ofInt(1, 0);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                animatorValue =   Float.parseFloat(animation.getAnimatedValue()+"") ;
                // 重点 计算每一个 火花的位置
                for(Element element :elements){
                    element.x = (float) (element.x + Math.cos(element.direction)*element.speed*animatorValue + windSpeed*windDirection);
                    element.y = (float) (element.y - Math.sin(element.direction)*element.speed*animatorValue + grivaty*(1-animatorValue));
                }
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                listener.onAinmationEnd();
            }
        });
        animator.start();
    }

    public void draw(Canvas canvas){
        mPaint.setAlpha((int) (225*animatorValue));
        for(Element element :elements){
            canvas.drawCircle(location.x + element.x, location.y + element.y, elemetSize, mPaint);
        }

    }

    public void setCount(int count){
        this.count = count;
    }

    public void setColors(int colors[]){
        this.colors = colors;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public void addAnimationEndListener(AnimationEndListener listener){
        this.listener = listener;
    }
    private static final int[] baseColors = { 0xFFFF43, 0x00E500, 0x44CEF6, 0xFF0040, 0xFF00FFB7, 0x008CFF, 0xFF5286,
            0x562CFF, 0x2C9DFF, 0x00FFFF, 0x00FF77, 0x11FF00, 0xFFB536, 0xFF4618, 0xFF334B, 0x9CFA18 };

    interface AnimationEndListener {
        void onAinmationEnd();
    }

    static class Location {
        public float x;
        public float y;

        public Location(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
