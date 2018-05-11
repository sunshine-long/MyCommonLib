package com.unht.myutils.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.Display;
import android.view.WindowManager;

import com.unht.myutils.app.APP;



import java.io.File;

import static com.unht.myutils.utils.CameraUtils.SAVED_IMAGE_DIR_PATH;

public class CommonUtils {

    /**
     * dp转px
     *
     * @param dpValue dp
     * @return px
     */
    public static int dp2px(float dpValue) {
        final float scale = APP.mAPP.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(float pxValue) {
        final float scale = APP.mAPP.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 判断设备是否具有虚拟导航栏
     *
     * @return 设备是否具有虚拟导航栏
     */
    public static boolean hasNavigationBar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) return true;
        WindowManager wm = (WindowManager) APP.mAPP.getSystemService(Context.WINDOW_SERVICE);
        Display d = wm.getDefaultDisplay();
        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        d.getRealMetrics(realDisplayMetrics);
        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);
        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;
        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
    }

    /**
     * 得到以px为单位的虚拟导航栏高度，若设备没有虚拟导航栏，返回0.
     *
     * @return 虚拟导航栏高度(px)，若设备没有虚拟导航栏，返回0.
     */
    public static int getNavigationBarHeightInPx() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) return dp2px(48);
        int navBarHeightInPx = 0;
        Resources rs = APP.mAPP.getResources();
        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0 && hasNavigationBar()) navBarHeightInPx = rs.getDimensionPixelSize(id);
        return navBarHeightInPx;
    }

    /**
     * 创建File对象，对应于data/data/${packageName}/cache/fileName.
     *
     * @param fileName 文件名
     * @return File
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File createImageFile(String fileName) {
        File outDir = new File(SAVED_IMAGE_DIR_PATH);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        return new File(outDir, fileName);
    }


    public static File createImageFile(String perent, String fileName) {

        File outDir = new File(perent);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        return new File(outDir, fileName);
    }


    /**
     * 获取屏幕尺寸
     * @return
     */
    public static Size getScreen() {
        WindowManager manager = (WindowManager) APP.mAPP
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        return new Size(height, width);
    }


    /**
     * 删除文件夹
     *
     * @param root
     */
    public static void deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }

    /**
     * @param context
     *            删除缓存
     */
    public static void clearAllCache(Context context) {
        deleteAllFiles(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteAllFiles(context.getExternalCacheDir());
        }
    }

}
