package com.marlon.module.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * @author by kanglong
 * @date on 2019/2/21.
 */
public abstract class BasePageFragment extends Fragment {
    //当前View是否创建了。
    protected boolean isViewInitiated;
    //当前数据是否加载了。
    protected boolean isDataInitiated;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        prepareFetchData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }
    //即使预加载了第二页，但是网络请求不会被预加载
    public abstract void getData();

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (getUserVisibleHint() && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            getData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    protected void showToast(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

}
