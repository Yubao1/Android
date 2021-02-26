package com.example.administrator.myapplication.activitys;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.kotlinclass.activate.MvvpActivity;
import com.example.administrator.myapplication.kotlinclass.activate.ResolutionActivity;
import com.example.administrator.myapplication.other.Constant;
import com.example.administrator.myapplication.other.EventBusCall;
import com.example.administrator.myapplication.tool.MyLogcat;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.ConcurrentLinkedDeque;

public class OtherKnowledgeActivity extends XActivity {
    Handler mH;
    class MyHandlerThread extends HandlerThread {
        public MyHandlerThread(String name) {
            super(name);
        }
    }
    class MyH extends Handler {
        public MyH(Looper l) {
            super(l);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            new MyLogcat().d(OtherKnowledgeActivity.this.getClass(),"ThreadName = " + Thread.currentThread().getName());
        }
    }
    @Override
    public void init() {
        MyHandlerThread mT = new MyHandlerThread("MyHandlerThread");
        mT.start();
        mH = new MyH(mT.getLooper());
        mH.sendEmptyMessageDelayed(0,400);
    }

    @Override
    public int getId() {
        return R.layout.activity_other_knowledge;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_volley:
                startActivity(VolleyActivity.class);
                break;
            case R.id.btn_scroll_and_recycler_collide:
                startActivity(ListAndRecyclerCollideActivity.class);
                break;
            case R.id.btn_location:
                startActivity(LocationActivity.class);
                break;
            case R.id.btn_event_bus:
                startActivity(EventBusActivity.class);
                startThread();
                break;
            case R.id.btn_mvvp:
                startActivity(MvvpActivity.class);
//                startActivity(Main3Activity.class);
                break;
            case R.id.btn_resolution:
                startActivity(ResolutionActivity.class);
                break;
        }
    }

    private void startThread() {
        Thread t = new Thread(){
            int i = 0;
            @Override
            public void run() {
                super.run();
                while ( i < 7) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    EventBusCall call = new EventBusCall(Constant.EVENT_BUS_RECEIVE_ID_5,i);
                    EventBus.getDefault().post(call);
                    i++;
                }
                new MyLogcat().d(OtherKnowledgeActivity.this.getClass(),"---startThread");
            }
        };
        t.start();
    }
}
