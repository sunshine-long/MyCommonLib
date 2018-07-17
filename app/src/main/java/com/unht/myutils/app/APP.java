package com.unht.myutils.app;

import android.app.Application;

/**
 * @author Marlon
 * @date 2017/12/14
 */

public class APP extends Application {
    public static APP instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static APP getInstance() {
        return instance;
    }
}
