package com.unht.myutils.utils;

import android.util.Log;

/**
 * @author KangLong
 * @date 2017/5/9
 * @description 日志打印工具类
 */

public class LogUtils {
    private static final boolean ISDEBUG = true;
    private static final String TAG = "LogUtils";

    /**
     * 打印一个debug等级的 log
     */
    public static void d(String tag, String msg) {
        if (ISDEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * 打印一个info等级的 log
     */
    public static void i(String tag, String msg) {
        if (ISDEBUG) {
            Log.i(tag, msg);
        }
    }


    /**
     * 打印一个warn等级的 log
     */
    public static void w(String tag, String msg) {
        if (ISDEBUG) {
            Log.w(tag, msg);
        }
    }

    /**
     * 打印一个error等级的 log
     */
    public static void e(String tag, String msg) {
        if (ISDEBUG) {
            Log.e(tag, msg);
        }
    }


}
