package com.unht.myutils.utils;

import android.content.Context;
import android.widget.Toast;

/**
 *
 * @author KangLong
 * @date 2017/7/7
 */

public class ToastUtils {

    public static void show(Context context, String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private static Toast toast = null;
    private static volatile ToastUtils toastUtils;

    private ToastUtils(Context context) {
        toast = Toast.makeText(context.getApplicationContext(), null, Toast.LENGTH_SHORT);
    }

    public static ToastUtils getInstance(Context context) {
        if (toastUtils == null) {
            synchronized (ToastUtils.class) {
                if (toastUtils == null) {
                    toastUtils = new ToastUtils(context);
                }
            }
        }
        return toastUtils;
    }

    public void showMessage(int toastMsg) {
        toast.setText(toastMsg);
        toast.show();
    }

    public void showMessage(String toastMsg) {
        toast.setText(toastMsg);
        toast.show();
    }

    public void toastCancel() {
        if (null != toast) {
            toast.cancel();
            toast = null;
        }
        toastUtils = null;
    }
}
