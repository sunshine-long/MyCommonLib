package com.marlon.module.common.di.component;

import android.app.Activity;

import com.marlon.module.common.di.module.ActivityModule;
import com.marlon.module.common.di.scope.ActivityScope;

import dagger.Component;


/**
 * @desc ActivityComponent
 * @author Marlon
 * @date 2018/12/25
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

}
