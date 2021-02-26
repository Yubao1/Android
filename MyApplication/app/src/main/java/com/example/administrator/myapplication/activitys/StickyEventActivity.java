package com.example.administrator.myapplication.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.other.Constant;
import com.example.administrator.myapplication.other.EventBusCall;

import org.greenrobot.eventbus.EventBus;

public class StickyEventActivity extends XActivity {
    @Override
    public void init() {

    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_event:
                sendEvent();
                break;
            case R.id.btn_receive_event:
                Intent intent = new Intent(this,ReceiveStickyEventActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void sendEvent() {
        EventBusCall call = new EventBusCall(Constant.RECEIVE_STICKY_EVENT_ID);
        EventBus.getDefault().postSticky(call);
        Toast.makeText(this,"发送粘性事件车成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getId() {
        return R.layout.activity_sticky_event;
    }
}
