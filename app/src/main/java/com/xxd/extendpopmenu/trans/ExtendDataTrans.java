package com.xxd.extendpopmenu.trans;

import com.xxd.extendpopmenu.entity.BackExtendHeader;
import com.xxd.extendpopmenu.entity.BackExtendLevel1;
import com.xxd.extendpopmenu.entity.BackExtendLevel2;
import com.xxd.extendpopmenu.entity.ExtendData;
import com.xxd.extendpopmenu.entity.ExtendItem;
import com.xxd.extendpopmenu.utils.AssertUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxd on 2017/7/29.
 * <p>
 * 将接口返回的数据转化为ExtendView可以接收的数据
 */

public class ExtendDataTrans {

    public static List<ExtendData> parse(List<BackExtendHeader> back) {
        List<ExtendData> list = new ArrayList<>();
        if (!AssertUtil.isEmpty(back)) {
            for (int i = 0; i < back.size(); i++) {
                BackExtendHeader header = back.get(i);
                ExtendData data = new ExtendData();
                list.add(data);
                data.setType(header.getType());
                addTitle(data, header.getList());

            }
        }
        return list;
    }

    private static void addTitle(ExtendData data, List<BackExtendLevel1> levelList1) {
        if (AssertUtil.isEmpty(levelList1))
            return;
        // 第一级
        data.setTotalLevel(1);
        // 放入header信息
        List<ExtendItem> itemList = new ArrayList<>();
        data.setList(itemList);
        for (BackExtendLevel1 level1 : levelList1) {
            String title = level1.getTitle();
            if (AssertUtil.isEmpty(title)) { // 没有标题，直接进入下一级
                addContent(data, itemList, level1.getContentList(), 0);
            } else {  // 有标题，标题作为1级
                ExtendItem item = new ExtendItem();
                item.setCurrentLevel(1);
                item.setTitle(true);
                item.setName(title);
                item.setCurrentLevel(1);
                List<ExtendItem> childItemList = new ArrayList<>();
                item.setChild(childItemList);
                itemList.add(item);
                addContent(data, childItemList, level1.getContentList(), 1);
            }
        }
    }

    private static void addContent(ExtendData data, List<ExtendItem> itemList, List<BackExtendLevel2> levelList2, int lastLevel) {
        if (AssertUtil.isEmpty(levelList2))
            return;
        // 总层级+1
        data.setTotalLevel(lastLevel + 1);
        // 遍历level2集合赋值到新的集合中
        for (BackExtendLevel2 level2 : levelList2) {
            ExtendItem item = new ExtendItem();
            itemList.add(item);
            item.setCurrentLevel(lastLevel + 1);
            item.setCode(level2.getCode());
            item.setName(level2.getName());
            if (!AssertUtil.isEmpty(level2.getContentList())) { // 如果接口数据有下一级
                List<ExtendItem> childItemList = new ArrayList<>();
                item.setChild(childItemList);
                addContent(data, childItemList, level2.getContentList(), lastLevel + 1);
            }
        }
    }

}
