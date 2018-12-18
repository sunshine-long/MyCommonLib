package com.unht.myutils.myutils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于管理activity 退出app时使用
 * Created by KangLong on 2017/6/30.
 * 升级版本为：{@link ViewManager}
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);

    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);

    }

    public static void removeAllActivity() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();

    }

}
