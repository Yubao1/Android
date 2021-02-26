package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.androidart.activites.AndroidArtActivity;
import com.example.administrator.androidart.activites.TestActivity;
import com.example.administrator.myapplication.activitys.AndserverActivity;
import com.example.administrator.myapplication.activitys.BluetoothActivity;
import com.example.administrator.myapplication.activitys.CameraActivity;
import com.example.administrator.myapplication.activitys.ClassDynamicActivity;
import com.example.administrator.myapplication.activitys.ContextStartActivity;
import com.example.administrator.myapplication.activitys.CustomScanViewActivity;
import com.example.administrator.myapplication.activitys.DebugActivity;
import com.example.administrator.myapplication.activitys.DemoActivity;
import com.example.administrator.myapplication.activitys.DynamicRegistActivity;
import com.example.administrator.myapplication.activitys.FragmentActivity;
import com.example.administrator.myapplication.activitys.FrameLayoutActivity;
import com.example.administrator.myapplication.activitys.GlideActivity;
import com.example.administrator.myapplication.activitys.HiddenKeyboardActivity;
import com.example.administrator.myapplication.activitys.JNIActivity;
import com.example.administrator.myapplication.activitys.JavaBaseActivity;
import com.example.administrator.myapplication.activitys.JsonActivity;
import com.example.administrator.myapplication.activitys.LayoutInflaterActivity;
import com.example.administrator.myapplication.activitys.MemoryActivity;
import com.example.administrator.myapplication.activitys.MoreActivityStartServiceActivity;
import com.example.administrator.myapplication.activitys.MyTest2Activity;
import com.example.administrator.myapplication.activitys.NActivityStartServiceActivity;
import com.example.administrator.myapplication.activitys.NetDialogActivity;
import com.example.administrator.myapplication.activitys.OkHttpActivity;
import com.example.administrator.myapplication.activitys.OtherKnowledgeActivity;
import com.example.administrator.myapplication.activitys.ProgressBar2Activity;
import com.example.administrator.myapplication.activitys.ProgressBarActivity;
import com.example.administrator.myapplication.activitys.RecordActivity;
import com.example.administrator.myapplication.activitys.RecyclerViewAdapterActivity;
import com.example.administrator.myapplication.activitys.RecylerViewActivity;
import com.example.administrator.myapplication.activitys.ReplaceQQHeadPortraitActivity;
import com.example.administrator.myapplication.activitys.RollActivity;
import com.example.administrator.myapplication.activitys.SharedPreferencesActivity;
import com.example.administrator.myapplication.activitys.StartCommandActivity;
import com.example.administrator.myapplication.activitys.StickyEventActivity;
import com.example.administrator.myapplication.activitys.TestListActivity;
import com.example.administrator.myapplication.activitys.ThreadPoolActivity;
import com.example.administrator.myapplication.activitys.WifiActivity;
import com.example.administrator.myapplication.other.Constant;
import com.example.administrator.myapplication.other.EventBusCall;
import com.example.administrator.myapplication.tool.BindEventBus;
import com.example.administrator.myapplication.tool.LayoutManagerTool;
import com.example.administrator.myapplication.tool.MyLogcat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;

/**
 * Android开发艺术探索 开发页412
 *
 * 如果APP没有达到预期的效果，看看APP是否打开相应权限
 * 简书：https://www.jianshu.com/c/ef7836bf3e22
 *
 * 1、百度网盘和QQ群有资料
 * 2、仿butterknife注解分析：https://blog.csdn.net/henkun/article/details/97525907
 * 3、butterknife注解分析：https://www.cnblogs.com/tony-yang-flutter/p/12483127.html
 * 4、JVM原理：https://www.cnblogs.com/hujinshui/p/10398958.html
 * 5、View.performClick() 说明View的点击事件被调用
 * 6、public <T extends View> T findViewById(@IdRes int id) 返回到具体的T实例对象
 * 7、setContentView(layoutResId)方法就是 初始化布局的所有视图
 * 8、用命令窗口执行java文件:https://www.cnblogs.com/wangxiaoha/p/6293340.html
 * 9、父类的公共静态方法，子类的对象和类名都可以调用父类的该方法
 * 10、server的2种状态可以共存即启动和绑定，如 NActivityStartServiceActivity案例
 * 11、声明接口的方法含抛出异常时，用如下表示 void method（）throws Exception;
 * 12、activty的startService(intent)方法，不管intent是否相同，启动的是同一个服务对象
 * 13、readResolve的用法 https://www.iteye.com/blog/lotuo-2294143
 * 14、子类可以用对象调用接口的属性，也可以用本身类名调用或者接口名调用
 * 15、Java反射—Array类的使用: https://www.jianshu.com/p/754ed930adce
 */
@BindEventBus
public class MainActivity extends XActivity implements View.OnTouchListener{
    TextView mTvTest;
    private Button mFloatingButton;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_camera_activity:
                startActivity(CameraActivity.class);
                break;
            case R.id.btn_glide_activity:
                startActivity(GlideActivity.class);
                break;
            case R.id.btn_jni_activity:
//                startActivity(JNIActivity.class);
                break;
            case R.id.btn_recycler_view_activity:
                startActivity(RecylerViewActivity.class);
                break;
            case R.id.btn_thread_pool_activity:
                startActivity(ThreadPoolActivity.class);
                break;
            case R.id.btn_json_activity:
                startActivity(JsonActivity.class);
                break;
            case R.id.btn_class_dynamic_activity:
                startActivity(ClassDynamicActivity.class);
                break;
            case R.id.btn_okhttp_activity:
                startActivity(OkHttpActivity.class);
                break;
            case R.id.btn_bluetooth_activity:
                startActivity(BluetoothActivity.class);
                break;
            case R.id.btn_record_activity:
                startActivity(RecordActivity.class);
                break;
            case R.id.btn_wifi_activity:
                startActivity(WifiActivity.class);
                break;
            case R.id.btn_progress_bar_activity:
                startActivity(ProgressBarActivity.class);
                break;
            case R.id.btn_fragment_activity:
                startActivity(FragmentActivity.class);
                break;
            case R.id.btn_progress_bar_2_activity:
                startActivity(ProgressBar2Activity.class);
                break;
            case R.id.btn_roll_activity:
                startActivity(RollActivity.class);
                break;
            case R.id.btn_shared_preferences_activity:
                startActivity(SharedPreferencesActivity.class);
                break;
            case R.id.btn_sticky_event_activity:
                startActivity(StickyEventActivity.class);
                break;
            case R.id.btn_android_art_activity:
                startActivity(AndroidArtActivity.class);
                break;
            case R.id.btn_test_activity:
                startActivity(TestActivity.class);
                break;
            case R.id.tv_context_start_activity:
                startActivity(ContextStartActivity.class);
                break;
            case R.id.tv_n_activity_start_service:
                startActivity(NActivityStartServiceActivity.class);
                break;
            case R.id.tv_lauouy_inflater_activity:
                startActivity(LayoutInflaterActivity.class);
                break;
            case R.id.tv_replace_qq_activity:
                startActivity(ReplaceQQHeadPortraitActivity.class);
                break;
            case R.id.tv_net_dialog_activity:
                startActivity(NetDialogActivity.class);
                break;
            case R.id.tv_frame_layout_activity:
                startActivity(FrameLayoutActivity.class);
                break;
            case R.id.tv_recycler_view_adapter_activity:
                startActivity(RecyclerViewAdapterActivity.class);
                break;
            case R.id.tv_custom_scan_view_activity:
                startActivity(CustomScanViewActivity.class);
                break;
            case R.id.tv_hidden_keyboard_activity:
                startActivity(HiddenKeyboardActivity.class);
                break;
            case R.id.tv_test_list_activity:
                startActivity(TestListActivity.class);
                break;
            case R.id.tv_debug_activity:
                startActivity(DebugActivity.class);
                break;
            case R.id.tv_java_base_activity:
                startActivity(JavaBaseActivity.class);
                break;
            case R.id.tv_test_activity:
                startActivity(com.example.administrator.myapplication.activitys.TestActivity.class);
                break;
            case R.id.tv_andserver_activity:
                startActivity(AndserverActivity.class);
                break;
            case R.id.tv_memory_activity:
                startActivity(MemoryActivity.class);
                break;
            case R.id.tv_start_command_activity:
                startActivity(StartCommandActivity.class);
                break;
            case R.id.tv_more_start_command_activity:
                startActivity(MoreStartCommandActivity.class);
                break;
            case R.id.tv_demo_activity:
                startActivity(DemoActivity.class);
                break;
            case R.id.tv_dynamic_regist_activity:
                startActivity(DynamicRegistActivity.class);
                break;
            case R.id.tv_more_activity_start_service:
                startActivity(MoreActivityStartServiceActivity.class);
                break;
            case R.id.btn_other_knowledge_activity:
                startActivity(OtherKnowledgeActivity.class);
                break;
        }
    }

    @Override
    public void onEventBusCall(EventBusCall event) {
        super.onEventBusCall(event);
        if (event.getId() == Constant.MAIN_ACTIVITY_RECEIVE_ID) {
//            startActivity(OkHttpActivity.class);
            mTvTest.setText("hehehehehehehhehehheheh");
        }
    }

    @Override
    public void init() {
        String TAG = getClass().getSimpleName();
        int pid = android.os.Process.myPid();
        new MyLogcat().d(getClass(),TAG + "------------------------" + pid);
        mTvTest = findViewById(R.id.tv_test_activity);
//        ButterKnife.bind(this);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(OkHttpActivity.class);
//            }
//        },10000);
        showWindow();
    }
    private void showWindow() {
        mWindowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        mFloatingButton = new Button(this);
        mFloatingButton.setText("执行每一项任务");
        mLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0,
                PixelFormat.TRANSPARENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
//        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;

        /**
         * 8.0以上只能用这个权限
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        }
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 300;
        mFloatingButton.setOnTouchListener(this);
        mWindowManager.addView(mFloatingButton, mLayoutParams);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mWindowManager != null) {
//            mWindowManager.removeView(mFloatingButton);
//        }
        new MyLogcat().d(getClass(),"------------------------------onDestroy()");
    }

    @Override
    public int getId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int)event.getRawX();
        int rawY = (int)event.getRawY();
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            mLayoutParams.x = rawX;
            mLayoutParams.y = rawY;
            mWindowManager.updateViewLayout(mFloatingButton,mLayoutParams);
        }
        return false;
    }

}
