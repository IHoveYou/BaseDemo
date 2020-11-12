package com.example.basedemo.base;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.basedemo.widget.textview.OneTextView;
import com.example.basedemo.widget.textview.ThreeTextView;
import com.example.basedemo.widget.textview.TwoTextView;

/**
 * 作者 lxy on Time 2020-08-1314:53.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 */
public  class BaseApplication extends Application {
    static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    public static void setInstance(BaseApplication instance) {
        BaseApplication.instance = instance;
    }

    @Override
    public void onCreate() {
        instance = this;
//        initTextView();
        super.onCreate();
    }


    public void initTextView(int[] size, int[] color) {
        OneTextView.setSize(size[0]);
        TwoTextView.setSize(size[1]);
        ThreeTextView.setSize(size[2]);

        OneTextView.setColor(color[0]);
        TwoTextView.setColor(color[1]);
        ThreeTextView.setColor(color[2]);


    }

    /**
     * 返回当前程序版本号
     */
    public double getAppVersionCode() {
        double versioncode = 0;
        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            // versionName = pi.versionName;
            versioncode = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versioncode;
    }

    /**
     * 返回当前程序版本名
     */
    public  String getAppVersionName() {
        String versionName = null;
        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

}
