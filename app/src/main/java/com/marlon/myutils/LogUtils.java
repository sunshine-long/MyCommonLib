package com.marlon.myutils;

import com.orhanobut.logger.Logger;

/**
 * @author KangLong
 * @date 2017/5/9
 * @description 日志打印工具类
 */

public class LogUtils {
    private static final boolean ISDEBUG = true;
    private static final String TAG = LogUtils.class.getSimpleName();

    /**
     * 打印一个debug等级的 log
     */
    public static void d(String tag, String msg) {
        if (ISDEBUG) {
            Logger.t(tag).d(msg);
        }
    }

    /**
     * 打印一个info等级的 log
     */
    public static void i(String tag, String msg) {
        if (ISDEBUG) {
            Logger.t(tag).i(msg);

        }
    }


    /**
     * 打印一个warn等级的 log
     */
    public static void w(String tag, String msg) {
        if (ISDEBUG) {
            Logger.t(tag).w(msg);
        }
    }

    /**
     * 打印一个error等级的 log
     */
    public static void e(String tag, String msg) {
        if (ISDEBUG) {
            Logger.t(tag).e(msg);
        }
    }


}
