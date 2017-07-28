package com.xxd.extendpopmenu.entity;

import com.xxd.extendpopmenu.Utils.AssertUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxd on 2017/7/28.
 */

public class ExtendData {

    // 当前数据的类型，用于表示应该展开哪个
    private int type;
    // 展开的类型名称
    private String typeName;
    //总共的层级数
    private int totalLevel;
    // 数据
    private List<ExtendItem> list;

    /**
     * 获取标签数据
     */
    public List<Tag> getTags() {
        List<Tag> tagList = new ArrayList<>();

        List<ExtendItem> tempList = list;
        while (!AssertUtil.isEmpty(tempList)) {
            for (int i = 0; i < list.size(); i++) {
                ExtendItem item = list.get(i);
                if (item.isChoice()) {
                    Tag tag = new Tag();
                    tag.addItem(item);
                    while (!AssertUtil.isEmpty(item.getChild())) {

                    }
                }
            }
        }

        return tagList;
    }

    // 筛选出需要的tag数据
    private void filterItem(ExtendItem itme, Tag lastTag, List<Tag> tagList) {

    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTotalLevel() {
        return totalLevel;
    }

    public void setTotalLevel(int totalLevel) {
        this.totalLevel = totalLevel;
    }

    public List<ExtendItem> getList() {
        return list;
    }

    public void setList(List<ExtendItem> list) {
        this.list = list;
    }

    /**
     * 标签数据
     */
    class Tag {

        // 用于存储tag数据,按层级顺序依次添加item
        private List<ExtendItem> tagList;

        public Tag() {
            this.tagList = new ArrayList<>();
        }

        // 添加item，用于标签展示
        public void addItem(ExtendItem item) {
            tagList.add(item);
        }


        // 获取标签的文字（用于展示数据）
        public String generateTagContent() {
            StringBuilder sb = new StringBuilder();
            if (!AssertUtil.isEmpty(tagList)) {
                for (int i = 0; i < tagList.size(); i++) {
                    ExtendItem item = tagList.get(i);
                    sb.append(item.getName());
                    if (i != tagList.size() - 1) {
                        sb.append(">");
                    }
                }
            }
            return sb.toString();
        }
    }
}
