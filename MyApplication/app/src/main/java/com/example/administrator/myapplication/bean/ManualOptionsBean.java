package com.example.administrator.myapplication.bean;

import java.util.Map;

/**
 * Created by Administrator on 2020/3/28.
 */

public class ManualOptionsBean {

    private String title;
    private int typeId;
    private Map<String, ManualTypeDataBean> typeData;

    public String getTitle() {
        return title;
    }

    public void setTitle(String t) {
        title = t;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Map<String, ManualTypeDataBean> getTypeData() {
        return typeData;
    }

    public void setTypeData(Map<String, ManualTypeDataBean> typeData) {
        this.typeData = typeData;
    }

    @Override
    public String toString() {
        return "ManualOptionsBean{" +
                "title='" + title + '\'' +
                ", typeId=" + typeId +
                ", typeData=" + typeData +
                '}';
    }
}

