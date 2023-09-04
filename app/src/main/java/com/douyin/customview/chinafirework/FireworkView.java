package com.douyin.customview.chinafirework;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FireworkView extends View {
    private final List<Firework> fireworks = new ArrayList<>();
    private final Random random = new Random();
    private final Paint paint = new Paint();

    public FireworkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 清空画布
        canvas.drawColor(Color.BLACK);

        // 绘制烟花
        List<Firework> fireworksToRemove = new ArrayList<>();
        for (Firework firework : fireworks) {
            firework.draw(canvas, paint);
            if (firework.isBurnedOut()) {
                fireworksToRemove.add(firework);
            }
        }
        fireworks.removeAll(fireworksToRemove);

        // 添加新的烟花
        if (random.nextInt(10) == 0) {
            fireworks.add(new Firework(getWidth(), getHeight()));
        }

        // 刷新视图
        invalidate();
    }
}
