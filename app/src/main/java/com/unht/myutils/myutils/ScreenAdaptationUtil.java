package com.unht.myutils.myutils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * 自定义设备的Density  用户屏幕适配，该适配方案，来源于今日头条的适配方案
 * <p>
 * 使用方法在基类的Activity中的onCreate方法中调用即可，注意使用时要在setContentView()之前！
 *
 * @author Marlon
 * @desc
 * @date 2018/7/19
 */
public class ScreenAdaptationUtil {
    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;
    //假设 设计图360dp 我们根据实际设计图的尺寸修改
    private static int designWidth = 360;

    public static void setCustomDensity(Activity activity, final Application application) {
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = displayMetrics.density;
            sNoncompatScaledDensity = displayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        //假设 设计图360dp 我们根据实际设计图的尺寸修改
        float targetDensity = displayMetrics.widthPixels / designWidth;
        float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        int targetDensityDpi = (int) (160 * targetDensity);
        displayMetrics.density = targetDensity;
        displayMetrics.scaledDensity = targetScaledDensity;
        displayMetrics.densityDpi = targetDensityDpi;

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}
