package com.marlon.module.common.di.component;


import com.marlon.module.common.base.BaseApplication;
import com.marlon.module.common.di.module.AppModule;
import com.marlon.module.common.di.module.HttpModule;
import com.marlon.module.common.network.BaseApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @desc AppComponent
 * @author Marlon
 * @date 2018/12/26
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    BaseApplication getContext();  // 提供App的Context

    BaseApiService retrofitHelper();  //提供http的帮助类

}
