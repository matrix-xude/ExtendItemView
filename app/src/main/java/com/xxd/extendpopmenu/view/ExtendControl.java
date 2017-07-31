package com.xxd.extendpopmenu.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import com.xxd.extendpopmenu.R;
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
    private int mWidth, mHeight;
    private int mLayoutId;
    private Map<String, ExtendFragment> fragmentMap;
    private Fragment currentFragment;

    public ExtendControl(Activity activity, int layoutId, List<ExtendData> list) {

        final View view = activity.findViewById(layoutId);
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        viewTreeObserver.addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                mWidth = view.getWidth();
                mHeight = view.getHeight();
            }
        });

        mLayoutId = layoutId;
        mActivity = activity;
        mListData = list;
        initData(list);
    }

    private void initData(List<ExtendData> list) {
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
