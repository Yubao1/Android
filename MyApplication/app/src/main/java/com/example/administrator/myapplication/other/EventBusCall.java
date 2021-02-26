package com.example.administrator.myapplication.other;

/**
 * Created by Administrator on 2020/4/4.
 */

public class EventBusCall {
    private int id = 0;
    private Object[] message;
    public EventBusCall(int id,Object... message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public Object[] getMessage() {
        return message;
    }
}
