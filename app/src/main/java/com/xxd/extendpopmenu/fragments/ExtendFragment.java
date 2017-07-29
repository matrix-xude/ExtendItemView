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

import com.xxd.extendpopmenu.HomeActivity;
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        int totalLevel = extendData.getTotalLevel();
        for (int i = 0; i < totalLevel; i++) {
            ListView lv = new ListView(getActivity());
            lv.setLayoutParams(new ViewGroup.LayoutParams(width / totalLevel, height));
            ll.addView(lv);

        }
        return ll;
    }

    private void initListView(int lvWidth, int lvHeight, int totalLevel, final List<ExtendItem> itemList) {
        final ListView lv = new ListView(getActivity());
        lv.setLayoutParams(new ViewGroup.LayoutParams(lvWidth, lvHeight));

        final ExtendAdapter adapter = new ExtendAdapter(getActivity(), itemList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ExtendItem item = itemList.get(position);
                // 当前的层级
                int currentLevel = item.getCurrentLevel();

                if (item.isChoice()) { // 已经被选中了，此为反选
                    View child = ll.getChildAt(currentLevel - 1);
                    if (child == null) { // 没有子listView
                        item.setChoice(false);
                        adapter.notifyDataSetChanged();
                    } else {
                        // 清除所有子listView被选中的状态
                        ExtendUtil.cleanChildChoice(item);
                        item.setChoice(false);
                        child.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                    }
                } else {  // 没有选中，此为选中
                    View child = ll.getChildAt(currentLevel - 1);
                    if (child == null) {// 没有子listView
                    }
                }
            }
        });
    }

}
