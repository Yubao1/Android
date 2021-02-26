package com.example.administrator.myapplication.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class RollActivity extends XActivity {
    private ScrollView scrollView;
    public static Activity staticActivity;
    private int j = 5;
    LinearLayout ll;
    //初始化AutoTouch对象
    public AutoTouch autoTouch = new AutoTouch();

    @Override
    public void init() {
        staticActivity = this;
        scrollView = findViewById(R.id.scrollView);
        ll = findViewById(R.id.ll);
        /***************************使用*****************************/
        //传入所在比例
        autoTouch.autoClickRatio(staticActivity, 50, 30);
        //出入坐标
        autoTouch.autoClickPos(staticActivity, 100, 40);
        new Handler().post(ScrollRunnable);
    }
    public void onClick(View view) {
        Toast.makeText(this,"被点击了",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void setTheme() {
        super.setTheme();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public int getId() {
        return R.layout.activity_roll;
    }

    class AutoTouch {
        public int width = 0;
        public int height = 0;

        /**
         * 传入在屏幕中的比例位置，坐标左上角为基准
         *
         * @param act    传入Activity对象
         * @param ratioX 需要点击的x坐标在屏幕中的比例位置
         * @param ratioY 需要点击的y坐标在屏幕中的比例位置
         */
        public void autoClickRatio(Activity act, final double ratioX, final double ratioY) {
            width = act.getWindowManager().getDefaultDisplay().getWidth();
            height = act.getWindowManager().getDefaultDisplay().getHeight();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 线程睡眠0.3s
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 生成点击坐标
                    int x = (int) (width * ratioX);
                    int y = (int) (height * ratioY);

                    // 利用ProcessBuilder执行shell命令
                    String[] order = {"input", "tap", "" + x, "" + y};
                    try {
                        new ProcessBuilder(order).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        public void autoClickPos(Activity act, final double x, final double y) {
            width = act.getWindowManager().getDefaultDisplay().getWidth();
            height = act.getWindowManager().getDefaultDisplay().getHeight();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 线程睡眠0.3s
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 利用ProcessBuilder执行shell命令
                    String[] order = {"input", "tap", "" + x, "" + y};
                    try {
                        new ProcessBuilder(order).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    Runnable ScrollRunnable = new Runnable() {

        @SuppressLint("NewApi")
        @Override
        public void run() {
            // TODO Auto-generated method stub
            int off = ll.getMeasuredHeight() - scrollView.getHeight();// 判断高度
            if (off > 0) {
                scrollView.scrollBy(0, j);
                j += 5;
                if (scrollView.getScaleY() == off) {
                    Thread.currentThread().interrupt();
                } else {
                    new Handler().postDelayed(this, 1000);
                }
            }
        }
    };

}
