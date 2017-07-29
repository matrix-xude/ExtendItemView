package com.xxd.extendpopmenu.utils;

import com.xxd.extendpopmenu.entity.ExtendData;
import com.xxd.extendpopmenu.entity.ExtendItem;

import java.util.List;

/**
 * Created by xxd on 2017/7/29.
 */

public class ExtendUtil {

    public static void cleanChildChoice(ExtendItem item) {
        if (item == null || AssertUtil.isEmpty(item.getChild()))
            return;
        cleanChildChoice(item.getChild());

    }

    public static void cleanChildChoice(List<ExtendItem> list) {
        if (AssertUtil.isEmpty(list))
            return;
        for (int i = 0; i < list.size(); i++) {
            ExtendItem item = list.get(i);
            item.setChoice(false);
            cleanChildChoice(item);
        }
    }


}
