package com.douyin.customview.fireworkstextview;

/**
 * Created by shishaoyan on 2018/7/26.
 */

public class Element {

    public int color;//颜色
    public Double direction;//方向
    public float speed;//速度
    public float x;//坐标
    public float y;
    public Element(int color, Double direction, float speed) {
        super();
        this.color = color;
        this.direction = direction;
        this.speed = speed;

    }
}
