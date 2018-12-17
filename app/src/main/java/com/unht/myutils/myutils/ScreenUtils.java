package com.unht.myutils.myutils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.WindowManager;

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
    public static Size getScreenSizePx(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return new Size(height, width);
    }

    /**
     * 获取屏幕管理类
     *
     * @param context
     * @return
     */
    @NonNull
    private static DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * 获取屏幕密度density
     *
     * @param context
     * @return
     */
    public static float getDensity(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        return dm.density;
    }

    /**
     * 获取屏幕密度 densityDpi
     *
     * @param context
     * @return
     */
    public static float getDensityDpi(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        return dm.densityDpi;
    }

    /**
     * 获取屏幕的高度 单位DPI
     *
     * @param context
     * @return
     */
    public static Size getScreenSizeDp(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
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
     * @param context
     * @return
     */
    public static int getScreenWidthDP(Context context) {
        return getScreenSizeDp(context).getWidth();
    }

    /**
     * 获取屏幕高度 单位DPI
     * @param context
     * @return
     */
    public static int getScreenHeightDP(Context context) {
        return getScreenSizeDp(context).getHeight();
    }

}
