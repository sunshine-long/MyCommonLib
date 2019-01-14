package com.marlon.myutils;

import android.content.Context;
import android.os.Environment;

import com.marlon.myutils.app.App;
import com.marlon.otherutils.AppUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import androidx.annotation.NonNull;

import static com.marlon.myutils.CommonUtils.SDCARDPATH;

/**
 * @author Marlon
 * @desc
 * @date 2018/11/13
 */
public class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("It's not support");
    }

    /**
     * 创建File对象，对应于data/data/${packageName}/cache/fileName.
     *
     * @param fileName 文件名
     * @return File
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File createFile(String fileName) {
        return createFile(AppUtils.getAppInfo(App.getInstance()).getName(), fileName);
    }


    public static File createFile(String perent, String fileName) {
        File outDir = new File(SDCARDPATH + perent);
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

    /**
     * 将Assets中的文件Copy到SD卡中
     *
     * @param context 上下文
     * @param srcPath assets中的文件名/或者文件夹
     * @param dstPath 要存储到SD卡的文件路径
     * @return
     */
    public static boolean copyAssetsToDst(Context context, @NonNull String srcPath, @NonNull String dstPath) {
        try {
            String fileNames[] = context.getAssets().list(srcPath);
            if (fileNames.length > 0) {
                File file = createFile(dstPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                for (String fileName : fileNames) {
                    // assets 文件夹下的目录
                    if (!srcPath.equals("")) {
                        copyAssetsToDst(context, srcPath + File.separator + fileName, dstPath + File.separator + fileName);
                    } else { // assets 文件夹
                        copyAssetsToDst(context, fileName, dstPath + File.separator + fileName);
                    }
                }
            } else {
                File outFile = createFile(dstPath);
                InputStream is = context.getAssets().open(srcPath);
                FileOutputStream fos = new FileOutputStream(outFile);
                byte[] buffer = new byte[1024];
                int byteCount;
                while ((byteCount = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, byteCount);
                }
                fos.flush();
                is.close();
                fos.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
