package com.douyin.customview.surfacefirework;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class FireworkView extends SurfaceView implements SurfaceHolder.Callback {
    private FireworkThread fireworkThread;
    private List<Firework> fireworks = new ArrayList<>();

    public FireworkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        fireworkThread = new FireworkThread(getHolder());
        fireworkThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // 不需要实现
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        fireworkThread.setRunning(false);
        while (retry) {
            try {
                fireworkThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // 重试
                Log.i("yankang",e.toString());
            }
        }
    }

    private class FireworkThread extends Thread {
        private final SurfaceHolder surfaceHolder;
        private boolean isRunning = true;

        public FireworkThread(SurfaceHolder holder) {
            surfaceHolder = holder;
        }

        public void setRunning(boolean running) {
            isRunning = running;
        }

        @Override
        public void run() {
            while (isRunning) {
                Canvas canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        updateFireworks();
                        Log.i("yankang","run");
                        drawFireworks(canvas);
                    }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

    private void updateFireworks() {
        List<Firework> fireworksToRemove = new ArrayList<>();
        for (Firework firework : fireworks) {
            firework.update();
            if (firework.isBurnedOut()) {
                fireworksToRemove.add(firework);
            }
        }
        fireworks.removeAll(fireworksToRemove);
    }

    private void drawFireworks(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        for (Firework firework : fireworks) {
            firework.draw(canvas);
        }
    }

    public void launchFirework(int maxX, int maxY) {
        fireworks.add(new Firework(maxX, maxY));
    }
}
