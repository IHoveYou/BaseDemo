package com.example.basedemo;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.basedemo.receiver.ConnectionChangeReceiver;
import com.example.basedemo.utils.ANRWatchDog;
import com.example.basedemo.utils.CrashHandlerUtil;
import com.example.basedemo.widget.textview.OneTextView;
import com.example.basedemo.widget.textview.ThreeTextView;
import com.example.basedemo.widget.textview.TwoTextView;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;


/**
 * Created by Tany on 2018/6/4.
 * Desc:
 */


public class BaseApplication extends Application {
    private static BaseApplication application;

    public static synchronized BaseApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

//        initTextView();

        new ANRWatchDog().start();
        /**
         *解决未捕获异常引起的崩溃
         */
        CrashHandlerUtil crashHandlerUtil = CrashHandlerUtil.getInstance();
        crashHandlerUtil.init(getApplicationContext());

        /**
         * 动态注册网络监听广播
         */
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");  //添加要收到的广播
        ConnectionChangeReceiver networkChangeReceiver = new ConnectionChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, R.color.gray1);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
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

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc(); // 垃圾回收
    }
}
