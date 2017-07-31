package com.xxd.extendpopmenu.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xxd.extendpopmenu.adapter.ExtendAdapter;
import com.xxd.extendpopmenu.entity.ExtendData;
import com.xxd.extendpopmenu.entity.ExtendItem;
import com.xxd.extendpopmenu.utils.AssertUtil;
import com.xxd.extendpopmenu.utils.ExtendUtil;

import java.util.List;

/**
 * Created by xxd on 2017/7/29.
 */

public class ExtendFragment extends Fragment {

    private int width, height;
    private int totalLevel;
    private ExtendData extendData;

    private LinearLayout ll;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取数据
        Bundle bundle = getArguments();
        extendData = (ExtendData) bundle.getSerializable("extendData");
        totalLevel = extendData.getTotalLevel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                width = ll.getMeasuredWidth();
                height = ll.getMeasuredHeight();
                showListView(1, extendData.getList());
            }
        });
        return ll;
    }

    /**
     * 展示目标层级的listView
     *
     * @param targetLevel 目标的层级
     * @param itemList    目标层级的数据
     */
    public void showListView(final int targetLevel, final List<ExtendItem> itemList) {

        // 检测出需要展开的listview
        final ListView lv;
        View view = ll.getChildAt(targetLevel - 1);
        if (view == null) {  // 如果目标层级的listview没有创建
            lv = new ListView(getActivity());
            lv.setLayoutParams(new ViewGroup.LayoutParams(width / totalLevel, height));
            ll.addView(lv);
        } else {  // 如果目标层级的listview已经存在
            lv = (ListView) view;
            lv.setVisibility(View.VISIBLE);
        }

        // 无论是否存在，都要重新创建adpater
        final ExtendAdapter adapter = new ExtendAdapter(getActivity(), itemList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ExtendItem item = itemList.get(position);
                // 当前的层级
                int currentLevel = item.getCurrentLevel();

                if (item.isChoice()) { // 已经被选中了，此为反选

                    // 清除所有子listView被选中的状态
                    ExtendUtil.cleanChildChoice(item);
                    // 收起所有子listview
                    for (int i = currentLevel; i < totalLevel; i++) {
                        View child = ll.getChildAt(i);
                        if (child != null && child.getVisibility() == View.VISIBLE) {
                            child.setVisibility(View.GONE);
                        }
                    }
                    // 刷新当前listview
                    item.setChoice(false);
                    adapter.notifyDataSetChanged();

                } else {  // 没有选中，此为选中

                    // 刷新当前listview
                    item.setChoice(true);
                    adapter.notifyDataSetChanged();
                    // 收起所有二级之后的子listview
                    for (int i = currentLevel; i < totalLevel; i++) {
                        View child = ll.getChildAt(i + 1);
                        if (child != null && child.getVisibility() == View.VISIBLE) {
                            child.setVisibility(View.GONE);
                        }
                    }
                    // 有下级，则展示下级listview
                    if (!AssertUtil.isEmpty(item.getChild()))
                        showListView(currentLevel + 1, item.getChild());

                }
            }
        });
    }

}
