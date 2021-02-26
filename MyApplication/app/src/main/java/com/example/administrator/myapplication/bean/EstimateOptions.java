package com.example.administrator.myapplication.bean;

/**
 * Created by Administrator on 2020/3/28.
 */

public class EstimateOptions {
    private ManualOptionsBean manual;
    private ManualOptionsBean auto;

    public ManualOptionsBean getManual() {
        return manual;
    }

    public void setManual(ManualOptionsBean manual) {
        this.manual = manual;
    }

    public ManualOptionsBean getAuto() {
        return auto;
    }

    public void setAuto(ManualOptionsBean auto) {
        this.auto = auto;
    }

    @Override
    public String toString() {
        return "EstimateOptions{" +
                "manual=" + manual +
                ", auto=" + auto +
                '}';
    }
}
