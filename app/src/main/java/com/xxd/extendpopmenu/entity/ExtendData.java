package com.xxd.extendpopmenu.entity;

import com.xxd.extendpopmenu.utils.AssertUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxd on 2017/7/28.
 */

public class ExtendData implements Serializable{

    // 当前数据的类型，用于表示应该展开哪个
    private String type;
    // 展开的类型名称
    private String typeName;
    //总共的层级数
    private int totalLevel;
    // 数据
    private List<ExtendItem> list;
    // 缓存下一级对应的 code:object,只在解析的时候用到
    private Map<String, ExtendItem> cacheMap;

    public ExtendData() {
        cacheMap = new HashMap<>();
    }

    /**
     * 获取标签数据
     */
    public List<Tag> getTags() {

        List<Tag> tagList = new ArrayList<>();
        filterItem(list,null,tagList);
        return tagList;
    }

    // 筛选出需要的tag数据
    private boolean filterItem(List<ExtendItem> list, Tag lastTag, List<Tag> tagList) {

        boolean flag = false;  // 当前层级是否有被选中的选项
        if (AssertUtil.isEmpty(list))
            return flag;

        for (int i = 0; i < list.size(); i++) {
            ExtendItem item = list.get(i);
            if (!item.isChoice()) // 没有被选择，忽略掉
                continue;

            flag = true;
            // 构建当前层级、当前选项的tag
            Tag currentLevelTag = null;
            if (lastTag == null) { // 上一个tag为null，即第一级遍历
                currentLevelTag = new Tag();
            } else {
                currentLevelTag = new Tag(lastTag);
            }
            lastTag.addItem(item);

            // 被选中的情况下 1：有子集合遍历子集合  2：没有子集合直接添加到子集合
            if (AssertUtil.isEmpty(item.getChild())) {
                Tag tag = new Tag(currentLevelTag);
                tagList.add(tag);
            } else {
                boolean filterItem = filterItem(item.getChild(), lastTag, tagList);
                if (!filterItem){  // 下一级没有被选中的条目，添加此条目到tagList
                    Tag tag = new Tag(currentLevelTag);
                    tagList.add(tag);
                }
            }
        }
        return flag;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public Map<String, ExtendItem> getCacheMap() {
        return cacheMap;
    }

    public void setCacheMap(Map<String, ExtendItem> cacheMap) {
        this.cacheMap = cacheMap;
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

        public Tag(Tag tag) {
            this.tagList = new ArrayList<>();
            if (tag != null && !AssertUtil.isEmpty(tagList)) {
                List<ExtendItem> lastTagList = tag.getTagList();
                for (ExtendItem item : lastTagList) {
                    addItem(item);
                }
            }
        }

        // 添加item，用于标签展示
        public void addItem(ExtendItem item) {
            tagList.add(item);
        }

        // 获取所有添加的标签
        public List<ExtendItem> getTagList() {
            return tagList;
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
