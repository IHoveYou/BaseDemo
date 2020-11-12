package com.example.basedemo.base;

import android.app.Activity;

/**
 * 作者 lxy on Time 2020-08-1315:56.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 */
public abstract class BaseVM<A extends Activity, V extends BaseView> {
    A mContext;
    V mView;


    public BaseVM(A mContext, V mView) {
        this.mView = mView;
        this.mContext = mContext;
    }


}
