package com.douyin.customview.htmlBeatifulfirework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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
    private Paint mTrailPaint;
    private int mTrailAlpha = 255; // 初始拖影透明度
    private PorterDuffXfermode clearMode;
    private PorterDuffXfermode lighterMode;
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
        mTrailPaint = new Paint();
        clearMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        lighterMode = new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN);
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
            for (int j = 1; j < 255; j++) {
                mCanvas = mHolder.lockCanvas();
                radius = radius + 2;
                mTrailPaint.setXfermode(clearMode); // 使用 CLEAR 模式
                mTrailPaint.setColor(Color.argb(mTrailAlpha, 255, 255, 255));
                mTrailAlpha=mTrailAlpha - 1;
                if (mTrailAlpha<0){
                    mTrailAlpha =255;
                }
                mTrailPaint.setAlpha(mTrailAlpha);
                mTrailPaint.setXfermode(lighterMode); // 使用 LIGHTEN 模式
                mCanvas.drawRect(0, 0, mCanvas.getWidth(), mCanvas.getHeight(), mTrailPaint);

                for (int i = 0; i < count; i++) {
                    paint.setColor(Color.argb(255, 0, 0, 0));
                    float angle = 360f / count * i;
                    float radians = (float) Math.toRadians(angle);
                    //绘制拖影



                    // 计算粒子的坐标

                    float moveX = getWidth() / 2 + (float) (Math.cos(radians) * radius);                    float moveY = getHeight() / 2 + (float) (Math.sin(radians) * radius);

                    // 绘制粒子

                    mCanvas.drawCircle(moveX, moveY, 10, paint);

                }
                mHolder.unlockCanvasAndPost(mCanvas);
                Thread.sleep(2);
            }
            mCanvas = mHolder.lockCanvas();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != mCanvas) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
