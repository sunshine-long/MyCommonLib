package com.marlon.module.common.utils;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Size;

/**
 * @author Marlon
 * @desc
 * @date 2018/11/13
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ScreenUtils {

    private ScreenUtils() {
        throw new UnsupportedOperationException("It's not support");
    }

    /**
     * 获取屏幕尺寸 px
     *
     * @return
     */
    public static Size getScreenSizePx() {
        DisplayMetrics dm = getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return new Size(height, width);
    }

    /**
     * 获取屏幕管理类
     *
     * @return
     */
    @NonNull
    private static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }

    /**
     * 获取屏幕密度density
     *
     * @return
     */
    public static float getDensity() {
        DisplayMetrics dm = getDisplayMetrics();
        return dm.density;
    }

    /**
     * 获取屏幕密度 densityDpi
     *
     * @return
     */
    public static float getDensityDpi() {
        DisplayMetrics dm = getDisplayMetrics();
        return dm.densityDpi;
    }

    /**
     * 获取屏幕的高度 单位DPI
     *
     * @return
     */
    public static Size getScreenSizeDp() {
        DisplayMetrics dm = getDisplayMetrics();
        // 屏幕宽度（像素）
        int width = dm.widthPixels;
        // 屏幕高度（像素）
        int height = dm.heightPixels;
        // 屏幕密度（0.75 / 1.0 / 1.5）
        float density = dm.density;
        // 屏幕宽度(dp)
        int screenWidth = (int) (width / density);
        // 屏幕高度(dp)
        int screenHeight = (int) (height / density);
        return new Size(screenHeight, screenWidth);
    }

    /**
     * 获取屏幕宽度 单位DPI
     *
     * @return
     */
    public static int getScreenWidthDP() {
        return getScreenSizeDp().getWidth();
    }

    /**
     * 获取屏幕高度 单位DPI
     *
     * @return
     */
    public static int getScreenHeightDP() {
        return getScreenSizeDp().getHeight();
    }

    /**
     * DP转PIX
     *
     * @param dp
     * @return
     */
    public static float dpToPixel(float dp) {
        return dp * getDensity();
    }

    /**
     * Pix转DP
     *
     * @param pixel
     * @return
     */
    public static float pixelToDp(float pixel) {
        return pixel / getDensity();
    }

}
