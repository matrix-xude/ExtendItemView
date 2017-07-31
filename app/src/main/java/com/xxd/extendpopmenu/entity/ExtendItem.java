package com.xxd.extendpopmenu.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by 河图 on 2017/7/28.
 * <p>
 * 此是对原始接口数据的重新封装，使得数据能匹配多层级、可展开、可复选的View
 * <p>
 * 备注：当有标题的接口数据会被封装成这里的一个层级，所以添加了一个标识符 isTitle，
 * 这是为了标识当前层级是标题转换过来的，还是content里面的内容转换的层级，title转换
 * 过来的此类没有code
 */

public class ExtendItem {


    // 当前的层级
    private int currentLevel;
    // 是否标题转换过来的层级
    private boolean isTitle;

    private String code;
    private String name;
    private List<ExtendItem> child;

    // 当前条目是否被选中，多选判断
    private boolean isChoice;
    // 缓存下一级对应的 code:object,只在解析的时候用到
    private Map<String, ExtendItem> cacheMap;

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExtendItem> getChild() {
        return child;
    }

    public void setChild(List<ExtendItem> child) {
        this.child = child;
    }

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public Map<String, ExtendItem> getCacheMap() {
        return cacheMap;
    }

    public void setCacheMap(Map<String, ExtendItem> cacheMap) {
        this.cacheMap = cacheMap;
    }
}
