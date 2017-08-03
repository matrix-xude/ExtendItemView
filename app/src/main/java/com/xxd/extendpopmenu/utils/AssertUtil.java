package com.xxd.extendpopmenu.utils;

import java.util.List;

/**
 * Created by xxd on 2017/7/28.
 */

public class AssertUtil {

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static boolean isEmpty( String str) {
        return str == null || "".equals(str);
    }

    public static void dodo(){}
}
