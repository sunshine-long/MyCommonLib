package com.marlon.module.common.utils;

import android.content.Context;
import android.os.Environment;

import net.uwonders.tobaccodemo.constent.AppConstent;

import java.io.File;

public class CommonUtils {

    /**
     * 创建File对象，对应于data/data/${packageName}/cache/fileName.
     *
     * @param fileName 文件名
     * @return File
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File createImageFile(String fileName) {
        return createImageFile(AppConstent.COMMON_FILE, fileName);
    }

    public static File createImageFile(String perent, String fileName) {
        String path = AppConstent.APP_SAVE_PATH + perent;
        File outDir = new File(path);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        return new File(outDir, fileName);
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
     * @param context 删除缓存
     */
    public static void clearAllCache(Context context) {
        deleteAllFiles(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteAllFiles(context.getExternalCacheDir());
        }
    }


}
