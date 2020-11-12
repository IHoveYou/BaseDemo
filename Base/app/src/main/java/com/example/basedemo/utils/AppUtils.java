package com.example.basedemo.utils;

import android.content.Context;

/**
 * 作者 lxy on Time 2020-08-1314:48.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 */
public class AppUtils {

    public static int dp2px(Context context, int value) {
        float v = context.getResources().getDisplayMetrics().density;
        return (int) (v * value + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, int pxValue) {
        return (int) (pxValue / context.getResources().getDisplayMetrics().density);
    }
}
