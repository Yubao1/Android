package com.example.administrator.myapplication.activitys;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.EventBusCall;
import com.example.administrator.myapplication.tool.BindEventBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class ReceiveStickyEventActivity extends AppCompatActivity {
    private TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_sticky_event);
        mTv = findViewById(R.id.tv);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 只要发送的粘性事件车，App没有关闭，那么就会一直有粘性事件车的信息存在
     * @param event
     */
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(EventBusCall event) {
        mTv.setText("已经接收到了事件车");
    }

}
