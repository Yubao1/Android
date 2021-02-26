package com.example.administrator.androidart.other;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 86188 on 2020/9/27.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler mCrachHandler;
    private static final String TAG = "CrashHandler";
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/CrashTest/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;
    private CrashHandler() {

    }
    public static CrashHandler getInstance() {
        if (mCrachHandler == null) {
            mCrachHandler = new CrashHandler();
        }
        return mCrachHandler;
    }
    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            dumpExceptionToSDCard(e);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        e.printStackTrace();
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(t,e);
        } else {
            Process.killProcess(Process.myPid());
        }
    }
    private void dumpExceptionToSDCard(Throwable ex) throws IOException {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d(TAG,"SD卡异常");
            return;
        }
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        pw.println(time);
        try {
            dumpPhoneInfo(pw);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        pw.println();
        ex.printStackTrace(pw);
        pw.close();
    }
    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),PackageManager.GET_ACTIVITIES);
        pw.print("App Version:");
        pw.print(pi.versionName);
        pw.print("_");
        pw.print(pi.versionCode);

        pw.print("OS version:");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.print(Build.VERSION.SDK_INT);
    }
}
