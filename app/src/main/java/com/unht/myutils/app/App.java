package com.unht.myutils.app;
import android.app.Application;

/**
 * @author Marlon
 * @date 2017/12/14
 */

public class App extends Application {
    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
