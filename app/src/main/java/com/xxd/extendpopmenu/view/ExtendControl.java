package com.xxd.extendpopmenu.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.xxd.extendpopmenu.entity.ExtendData;
import com.xxd.extendpopmenu.entity.ExtendItem;
import com.xxd.extendpopmenu.fragments.ExtendFragment;
import com.xxd.extendpopmenu.listener.ExtendViewClickListener;
import com.xxd.extendpopmenu.utils.AssertUtil;
import com.xxd.extendpopmenu.utils.ExtendUtil;

import java.util.ArrayList;
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
    private ExtendFragment currentFragment;

    private ExtendViewClickListener listener;

    public ExtendControl(Activity activity, int layoutId, List<ExtendData> list) {
        mLayoutId = layoutId;
        mActivity = activity;
        mListData = list;
        fragmentMap = new HashMap();
    }

    /**
     * 展示某个类型的多级listView
     *
     * @param type
     */
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
                    // 每个fragment添加点击按钮监听事件
                    fragment.setOnExtendViewClickListener(listener);
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

    /**
     * 获取所有的标签
     *
     * @return
     */
    public List<ExtendData.Tag> getTags() {
        List<ExtendData.Tag> list = new ArrayList<>();
        if (!AssertUtil.isEmpty(mListData)) {
            for (ExtendData data : mListData) {
                List<ExtendData.Tag> tags = data.getTags();
                if (!AssertUtil.isEmpty(tags)) {
                    for (ExtendData.Tag t : tags) {
                        list.add(t);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 删除一个选中的Tag，此方法可以刷新界面
     *
     * @param tag
     */
    public void removeTag(ExtendData.Tag tag) {
        removeTagNoRefresh(tag);
        refreshView(currentFragment);
    }

    /**
     * 删除一个集合的标签，此方法可以刷新界面
     */
    public void revomeTag(List<ExtendData.Tag> tagList) {
        if (!AssertUtil.isEmpty(tagList)) {
            for (ExtendData.Tag tag : tagList) {
                removeTagNoRefresh(tag);
            }
            refreshView(currentFragment);
        }
    }

    private void removeTagNoRefresh(ExtendData.Tag tag) {
        List<ExtendItem> tagList = tag.getTagList();
        for (int i = tagList.size() - 1; i >= 0; i--) {
            ExtendItem item = tagList.get(i);
            if (i == tagList.size() - 1) { // 最后一级，直接改为false
                item.setChoice(false);
            } else {
                if (ExtendUtil.checkChildChoice(item)) { //上一级还有下一其他下一级
                    break;
                } else {
                    item.setChoice(false);
                }
            }
        }
    }


    // 刷新页面
    private void refreshView(ExtendFragment fragment) {
        if (fragment != null) {
            fragment.refreshView();
        }
    }

    /**
     * 设置View任意级item被点击的监听
     *
     * @param listener
     */
    public void setOnExtendViewClickListener(ExtendViewClickListener listener) {
        this.listener = listener;
    }
}
