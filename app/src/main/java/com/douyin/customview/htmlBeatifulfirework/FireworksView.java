package com.douyin.customview.htmlBeatifulfirework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.douyin.customview.R;

/**
 * yankang.
 */
public class FireworksView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    //用于绘图的canvas
    private Canvas mCanvas;
    //子线程标志位
    private boolean mIsDrawing;
    Paint paint;
    private int count = 10; // 粒子数量
    private int radius = 20; // 初始半径

    public FireworksView(Context context) {
        super(context);
        init();
    }

    public FireworksView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FireworksView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.white));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {

        draw();

    }

    private void draw() {
        try {
            for (int j = 1; j < 10; j++) {
                mCanvas = mHolder.lockCanvas();
                mCanvas.drawColor(getResources().getColor(R.color.teal_200));
                radius = radius + j * 10;
                for (int i = 0; i < count; i++) {
                    // 计算粒子的角度
                    float angle = 360f / count * i;
                    float radians = (float) Math.toRadians(angle);

                    // 计算粒子的坐标
                    float moveX = 350 + (float) (Math.cos(radians) * radius);
                    float moveY = 350 + (float) (Math.sin(radians) * radius);

                    // 绘制粒子

                    mCanvas.drawCircle(moveX, moveY, 5, paint);
                }
                mHolder.unlockCanvasAndPost(mCanvas);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != mCanvas) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
