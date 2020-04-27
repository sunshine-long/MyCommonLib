package com.marlon.myutils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * @author Marlon
 * @desc
 * @date 2018/11/13
 */
public class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("It's not support");
    }

    private static final String TAG = "CommonUtils";

    /**
     * 创建File对象，对应于data/data/${packageName}/cache/fileName.
     *
     * @param fileName 文件名
     * @return File
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File createImageFile(Context context,String fileName) {
        return createImageFile(context,null, fileName);
    }

    /**
     * 自定义文件存储路径
     *
     * @param perent
     * @param fileName
     * @return
     */
    public static File createImageFile(Context context,String perent, String fileName) {
        String perentPath = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            perentPath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + perent;
        } else {
            perentPath = "" + File.separator + perent;
        }
        File outDir = new File(perentPath);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File file = new File(outDir, fileName);
        try {
            if (!file.createNewFile()) {
                Log.e(TAG, "createImagePathUri: File already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    /**
     * Android 10 之前删除文件夹
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
