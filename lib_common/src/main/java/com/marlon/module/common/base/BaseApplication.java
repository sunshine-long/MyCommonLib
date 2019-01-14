package com.marlon.module.common.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.marlon.module.common.di.component.AppComponent;
import com.marlon.module.common.di.component.DaggerAppComponent;
import com.marlon.module.common.di.module.AppModule;
import com.marlon.module.common.di.module.HttpModule;
import com.marlon.module.common.utils.ClassUtils;
import com.marlon.module.common.utils.PreferencesUtils;
import com.marlon.module.common.utils.Utils;

import net.uwonders.greendao.DaoMaster;
import net.uwonders.greendao.DaoSession;

import java.util.List;


/**
 * @author Marlon
 * @desc BaseApplication
 * @date 2019/1/4
 */
public class BaseApplication extends Application {
    public static final String ROOT_PACKAGE = "com.uwonders.module";
    private static BaseApplication instance;
    public static AppComponent appComponent;
    private List<IApplicationDelegate> mAppDelegateList;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);
        PreferencesUtils.init();
        setDatabase();
        mAppDelegateList = ClassUtils.getObjectsWithInterface(this, IApplicationDelegate.class, ROOT_PACKAGE);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onCreate();
        }
    }

    public static synchronized BaseApplication getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }


    /**
     * 设置greenDAO
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "tobacco-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public  DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTerminate();
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTrimMemory(level);
        }
    }
}

