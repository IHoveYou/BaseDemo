package com.example.basedemo.utils;

import android.os.Handler;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;

public class TimeUtils {

    private static TimeUtils timeUtils;
    ArrayList<WeakReference<OnTimeCallListent>> listents = new ArrayList<>();


    private TimeUtils() {
        mHandler.postDelayed(r, 1000);//延时100毫秒
    }

    public static synchronized TimeUtils newInstance() {
        if (timeUtils == null) {
            timeUtils = new TimeUtils();
        }
        return timeUtils;
    }


    public void setCallBack(OnTimeCallListent onTimeCallListent) {
        WeakReference<OnTimeCallListent> weakReference = new WeakReference<>(onTimeCallListent);
        listents.add(weakReference);
    }

    public interface OnTimeCallListent {
        public void onTime(long time);
    }


    final Handler mHandler = new Handler();
    Runnable r = new Runnable() {

        @Override
        public void run() {
            ArrayList<WeakReference<OnTimeCallListent>> mList = new ArrayList<>();
            for (int i = 0; i < listents.size(); i++) {
                OnTimeCallListent listent = listents.get(i).get();
                if (listent != null) {
                    mList.add(listents.get(i));
                    listent.onTime(getLongTime() / 1000);
                }
            }
            listents.clear();
            listents = mList;
            mHandler.postDelayed(this, 1000);
        }
    };

    public static Date getDate() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    public static long getLongTime() {
        long dateTime = getDate().getTime();
        return dateTime;
    }
}
