package com.marlon.module.common.di.component;

import android.app.Activity;

import com.marlon.module.common.di.module.FragmentModule;
import com.marlon.module.common.di.scope.FragmentScope;

import dagger.Component;

/**
 * @desc FragmentComponent
 * @author Marlon
 * @date 2018/12/26
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();


}
