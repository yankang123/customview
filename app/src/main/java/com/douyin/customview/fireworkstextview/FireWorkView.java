package com.douyin.customview.fireworkstextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * Created by shishaoyan on 2018/7/26.
 */

    public class FireWorkView extends View {

        private final String TAG = this.getClass().getSimpleName();
        private EditText mEditText;
        private LinkedList<FireWork> fireWorks = new LinkedList<FireWork>();
        private int windSpeed;

        public FireWorkView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public void bindEditText(EditText editText) {
            this.mEditText = editText;
            mEditText.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    float[] coordinate = getCursorCoordinate();
                    launch(coordinate[0], coordinate[1], before == 0 ? -1 : 1);
                }

                private void launch(float f, float g, int i) {
                    final FireWork firework = new FireWork(new FireWork.Location(f, g), i);
                    firework.addAnimationEndListener(new FireWork.AnimationEndListener() {
                        @Override
                        public void onAinmationEnd() {
                            //动画结束后把firework移除，当没有firework时不会刷新页面
                            fireWorks.remove(firework);
                        }
                    });
                    fireWorks.add(firework);
                    firework.fire();
                    invalidate();

                }

                private float[] getCursorCoordinate() {
                /*
                 * 以下通过反射获取光标cursor的坐标。
                 * 首先观察到TextView的invalidateCursorPath()方法，它是光标闪动时重绘的方法。
                 * 方法的最后有个invalidate(bounds.left + horizontalPadding, bounds.top
                 * + verticalPadding, bounds.right + horizontalPadding,
                 * bounds.bottom + verticalPadding); 即光标重绘的区域，由此可得到光标的坐标
                 * 具体的坐标在TextView.mEditor.mCursorDrawable里，
                 * 获得Drawable之后用getBounds()得到Rect。 之后还要获得偏移量修正，通过以下三个方法获得：
                 * getVerticalOffset(),getCompoundPaddingLeft(),
                 * getExtendedPaddingTop()。
                 *
                 */

                    int xOffset = 0;
                    int yOffset = 0;
                    Class<?> clazz = EditText.class;
                    clazz = clazz.getSuperclass();// 获得 TextView 这个类
                    try {
                        Field editor = clazz.getDeclaredField("mEditor");
                        editor.setAccessible(true);
                        Object mEditor = editor.get(mEditText);
                        Class<?> editorClazz = Class.forName("android.widget.Editor");
                        Field drawables = editorClazz.getDeclaredField("mCursorDrawable");
                        drawables.setAccessible(true);
                        Drawable[] drawable = (Drawable[]) drawables.get(mEditor);
                        Method getVerticalOffset = clazz.getDeclaredMethod("getVerticalOffset", boolean.class);
                        Method getCompoundPaddingLeft = clazz.getDeclaredMethod("getCompoundPaddingLeft");
                        Method getExtendedPaddingTop = clazz.getDeclaredMethod("getExtendedPaddingTop");
                        getVerticalOffset.setAccessible(true);
                        getCompoundPaddingLeft.setAccessible(true);
                        getExtendedPaddingTop.setAccessible(true);

                        if (drawable != null) {
                            Rect bounds = drawable[0].getBounds();
                            try {
                                xOffset = Integer.parseInt(getCompoundPaddingLeft.invoke(mEditText) + "") + bounds.left;
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            yOffset = Integer.parseInt(getExtendedPaddingTop.invoke(mEditText) + "")
                                    + Integer.parseInt(getVerticalOffset.invoke(mEditText, false) + "") + bounds.bottom;

                        }
                    } catch (NoSuchFieldException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    float x = mEditText.getX() + xOffset;
                    float y = mEditText.getY() + yOffset;

                    return new float[]{x, y};
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable s) {
                    // TODO Auto-generated method stub

                }
            });

        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);

            for (int i = 0; i < fireWorks.size(); i++) {
                fireWorks.get(i).draw(canvas);
            }
            if (fireWorks.size() > 0)
                invalidate();
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
