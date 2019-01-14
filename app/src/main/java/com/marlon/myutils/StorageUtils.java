package com.marlon.myutils;


import android.os.Environment;

import java.io.File;

/**
 * @author Marlon
 */
public class StorageUtils {
    /**
     * 得到sdcard的路径
     *
     * @return 返回一个字符串数组   下标0:内置sdcard   下标1:外置sdcard
     */
    public static String[] getSDCardPath() {
        String[] sdCardPath = new String[2];
        File sdFile = Environment.getExternalStorageDirectory();
        File[] files = sdFile.getParentFile().listFiles();
        for (File file : files) {
            if (file.getAbsolutePath().equals(sdFile.getAbsolutePath())) {//外置
                sdCardPath[1] = sdFile.getAbsolutePath();
            } else if (file.getAbsolutePath().contains("sdcard")) {//得到内置sdcard
                sdCardPath[0] = file.getAbsolutePath();
            }
        }
        return sdCardPath;
    }

}
