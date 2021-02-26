package com.example.administrator.myapplication.bean;

/**
 * Created by Administrator on 2020/3/28.
 */

public class OptionBean {

    private String subTitle;
    private int subId;
    private String url;

    @Override
    public String toString() {
        return "OptionBean{" +
                "subTitle='" + subTitle + '\'' +
                ", subId=" + subId +
                ", url='" + url + '\'' +
                ", subTitleInt=" + subTitleInt +
                '}';
    }

    private int subTitleInt;// 在 rom ram 匹配时辅助比较

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSubTitleInt() {
        return subTitleInt;
    }

    public void setSubTitleInt(int subTitleInt) {
        this.subTitleInt = subTitleInt;
    }
}

