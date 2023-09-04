
package com.douyin.customview.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * 图像工具类
 */
public class BitmapUtil {

    private static final String TAG = BitmapUtil.class.getSimpleName();
    public static Bitmap readBitmap(Resources res, int resID) {
        return readBitmap(res, resID, null);
    }

    /**
     * 读取图片资源
     *
     * @param res
     * @param resID
     * @param options
     * @return
     */
    public static Bitmap readBitmap(Resources res, int resID, Options options) {
        if (res == null) {
            return null;
        }
        try {
            return BitmapFactory.decodeResource(res, resID, options);
        } catch (OutOfMemoryError e) {

            System.gc();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
