package com.marlon.module.common.base;


import android.support.annotation.Keep;
import android.view.View;

/**
 * @desc IViewDelegate
 * @author Marlon
 * @date 2019/1/11
 */
@Keep
public interface IViewDelegate {

    BaseFragment getFragment(String name);

    View getView(String name);

}
