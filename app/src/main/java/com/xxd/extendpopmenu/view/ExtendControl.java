package com.xxd.extendpopmenu.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.xxd.extendpopmenu.entity.ExtendData;
import com.xxd.extendpopmenu.fragments.ExtendFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxd on 2017/7/31.
 */

public class ExtendControl {

    private Activity mActivity;
    private List<ExtendData> mListData;
    private int mLayoutId;
    private Map<String, ExtendFragment> fragmentMap;
    private Fragment currentFragment;

    public ExtendControl(Activity activity, int layoutId, List<ExtendData> list) {
        mLayoutId = layoutId;
        mActivity = activity;
        mListData = list;
        fragmentMap = new HashMap();
    }

    public void showByType(String type) {
        if (mActivity == null || mListData == null)
            return;

        FragmentTransaction ft = mActivity.getFragmentManager().beginTransaction();

        ExtendFragment fragment = fragmentMap.get(type);
        if (fragment == null) { // 还没有被初始化
            for (ExtendData data : mListData) {
                if (type.equals(data.getType())) {
                    fragment = new ExtendFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("extendData", data);
                    fragment.setArguments(bundle);
                    fragmentMap.put(type, fragment);
                    ft.add(mLayoutId, fragment);
                    break;
                }
            }
        }

        if (currentFragment != null) { // 隐藏上一个fragment
            ft.hide(currentFragment);
        }

        currentFragment = fragment;
        ft.show(currentFragment);
        ft.commit();
    }

}
