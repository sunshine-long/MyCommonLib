package com.marlon.module.common.base;

import android.support.annotation.Keep;

/**
 * @desc IApplicationDelegate
 * @author Marlon
 * @date 2019/1/11
 */
@Keep
public interface IApplicationDelegate {

    void onCreate();

    void onTerminate();

    void onLowMemory();

    void onTrimMemory(int level);

}
