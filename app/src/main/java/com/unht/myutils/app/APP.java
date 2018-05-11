package com.unht.myutils.app;

import android.app.Application;

/**
 * @author Marlon
 * @date 2017/12/14
 */

public class APP extends Application {
    public static APP mAPP;

    @Override
    public void onCreate() {
        super.onCreate();
        mAPP = this;
    }

    public static APP getAPP() {
        return mAPP;
    }
}
