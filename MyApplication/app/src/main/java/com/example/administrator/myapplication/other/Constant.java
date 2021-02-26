package com.example.administrator.myapplication.other;

import okhttp3.MediaType;

/**
 * Created by Administrator on 2020/3/29.
 */

public class Constant {
    public static final String MEDIA_TYPE_FORMAT = "text/x-markdown; charset=utf-8";
    public static final MediaType JSON = MediaType.parse(MEDIA_TYPE_FORMAT);
    public static final String URL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    public static final String FILE_NAME = "okhttp-utils-test.mp4";
    public static final String VIDEO_URL = "http://vfx.mtime.cn/Video/2019/06/29/mp4/190629004821240734.mp4";
    public static final int WIFI_ACTIVITY_ID = 1;
    public static final int RECEIVE_STICKY_EVENT_ID = 2;
    public static final int THREAD_BLOCK_ACTIVITY_RECEIVE_ID = 3;
    public static final int MAIN_ACTIVITY_RECEIVE_ID = 4;
    public static final int EVENT_BUS_RECEIVE_ID_5 = 5;
    public static final int THREAD_LOCAL_ACTIVITY_RECEIVE_ID = 6;
    public static final int THREAD_LOCAL_ACTIVITY_RECEIVE_ID_2 = 7;
    public static final boolean ANTI_ALIAS = true;
    public static final String URI = "content://com.zyb.provider";

    public static final int DEFAULT_SIZE = 150;
    public static final int DEFAULT_START_ANGLE = 270;
    public static final int DEFAULT_SWEEP_ANGLE = 360;

    public static final int DEFAULT_ANIM_TIME = 1000;

    public static final int DEFAULT_MAX_VALUE = 100;
    public static final int DEFAULT_VALUE = 50;

    public static final int DEFAULT_HINT_SIZE = 15;
    public static final int DEFAULT_UNIT_SIZE = 30;
    public static final int DEFAULT_VALUE_SIZE = 15;

    public static final int DEFAULT_ARC_WIDTH = 15;

    public static final int DEFAULT_WAVE_HEIGHT = 40;
    public static final int MSG_FORM_CLIENT = 1;
    public static final int MSG_FROM_SERVICE = 2;
}
