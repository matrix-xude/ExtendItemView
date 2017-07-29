package com.xxd.extendpopmenu.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xxd.extendpopmenu.adapter.ExtendAdapter;
import com.xxd.extendpopmenu.entity.ExtendData;
import com.xxd.extendpopmenu.entity.ExtendItem;
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
        width = bundle.getInt("width");
        height = bundle.getInt("height");
        extendData = (ExtendData) bundle.getSerializable("extendData");
        totalLevel = extendData.getTotalLevel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        return ll;
    }

    /**
     * 展示目标层级的listView
     *
     * @param targetLevel 目标的层级
     * @param itemList    目标层级的数据
     */
    private void showListView(final int targetLevel, final List<ExtendItem> itemList) {

        // 目标的listview是否已经init过
        boolean initListFlag = false;
        // 检测出需要展开的listview
        final ListView lv;
        View view = ll.getChildAt(targetLevel - 1);
        if (view == null) {  // 如果目标层级的listview没有创建
            lv = new ListView(getActivity());
            lv.setLayoutParams(new ViewGroup.LayoutParams(width / totalLevel, height));
        } else {  // 如果目标层级的listview已经存在
            lv = (ListView) view;
            initListFlag = true;
        }

        // 无论是否存在，都要重新创建adpater
        final ExtendAdapter adapter = new ExtendAdapter(getActivity(), itemList);
        lv.setAdapter(adapter);

        // 初始化没有init过的listview
        if (!initListFlag) {

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
                        for (int i = currentLevel; currentLevel < totalLevel; i++) {
                            View child = ll.getChildAt(i);
                            if (child !=null && child.getVisibility() == View.VISIBLE) {
                                child.setVisibility(View.GONE);
                            }
                        }
                        // 刷新当前listview
                        item.setChoice(false);
                        adapter.notifyDataSetChanged();

                    } else {  // 没有选中，此为选中

                        // 刷新当前listview
                        item.setChoice(false);
                        adapter.notifyDataSetChanged();
                        // 收起所有二级之后的子listview
                        for (int i = currentLevel; currentLevel < totalLevel; i++) {
                            View child = ll.getChildAt(i+1);
                            if (child !=null && child.getVisibility() == View.VISIBLE) {
                                child.setVisibility(View.GONE);
                            }
                        }
                        // 展示下级listview
                        showListView(currentLevel+1,item.getChild());

                    }
                }
            });
        }
    }

}
