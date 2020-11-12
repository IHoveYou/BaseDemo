package com.example.basedemo.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.basedemo.R;
import com.example.basedemo.widget.BaseTitleView;

/**
 * 作者 lxy on Time 2020-08-1314:22.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 */
public abstract class BaseActivity<B extends ViewDataBinding, VM extends BaseVM> extends AppCompatActivity implements BaseView<VM> {
    public View contentView;
    public B mBinding;
    public VM mVM;

    public ViewGroup rootView;
    public ViewGroup flContext;

    BaseTitleView titleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.base_activity);
        rootView = findViewById(R.id.rootView);
        flContext = findViewById(R.id.fl_context);
        mVM = createVM();
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(this), getLayout(), null, false);
        contentView = mBinding.getRoot();
        flContext.addView(contentView);
        init();

    }

    private void init() {
        initView();
        initData();
    }


    public void setTitle(String text) {
        if (titleView == null) {
            titleView = new BaseTitleView(this);
            rootView.addView(titleView, 0);
        }
        titleView.setTitleText(text);
    }

    public void hidden() {

    }

    //刷新
    public void refresh() {

    }

    public static int dp2px(Context context, int value) {
        float v = context.getResources().getDisplayMetrics().density;
        return (int) (v * value + 0.5f);
    }
    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }
}
