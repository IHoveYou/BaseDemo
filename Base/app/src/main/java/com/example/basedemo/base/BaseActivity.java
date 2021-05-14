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
import com.example.basedemo.utils.AppUtils;
import com.example.basedemo.utils.StatusBarUtil;
import com.example.basedemo.utils.ToastUtils;
import com.example.basedemo.widget.BaseTitleView;

/**
 * 作者 lxy on Time 2020-08-1314:22.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 */
public abstract class BaseActivity<B extends ViewDataBinding, VM extends BaseVM> extends AppCompatActivity implements BaseView<VM>, View.OnClickListener {
    public View contentView;
    public B mBinding;
    public VM mVM;

    public ViewGroup rootView;
    public ViewGroup flContext;
    public ViewGroup fltitle;
    public ViewGroup flErrLayout;

    BaseTitleView titleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.base_activity);
        setStatusBar(R.color.white);
        rootView = findViewById(R.id.rootView);
        flContext = findViewById(R.id.fl_context);
        fltitle = findViewById(R.id.fl_title);
        flErrLayout = findViewById(R.id.fl_err);
        mVM = createVM();
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(this), getLayout(), null, false);
        contentView = mBinding.getRoot();
        flContext.addView(contentView);
        init();

    }

    private void init() {
        try {
            initView();
            initData();
        } catch (Exception e) {
            ToastUtils.showMessage("程序出现错误,请刷新重试");
            finish();
        }
    }

    /**
     * 子类想改变其他颜色就重写这个方法
     */
    protected void setStatusBar(int color) {
        StatusBarUtil.setColor(this, getResources().getColor(color));
    }

    //沉浸式
    protected void hideStatusBar() {
        fltitle.setPadding(0, AppUtils.getStatusBarHeight(this),0,0);
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }

    public void setTitle(String text) {
        initTitle();
        titleView.setTitleText(text);
    }

    public void setTitleLeftImage(int image) {
        titleView.setLeftImage(image);
    }

    public void setTitleLeft(int color, String leftText) {
        initTitle();
        titleView.setLeftTextColor(color);
        titleView.setLeftText(leftText);
    }

    public void setTitleLeft(String leftText) {
        initTitle();
        titleView.setLeftText(leftText);
    }

    public void setTitleRightText(int color, String leftText) {
        initTitle();
        titleView.setRightTextColor(color);
        titleView.setRightText(leftText);
    }

    public void setTitleRightText(String leftText) {
        initTitle();
        titleView.setRightText(leftText);
    }


    public void setRightImage(int image) {
        initTitle();
        titleView.setRightImg(image);
    }


    private void initTitle() {
        if (titleView == null) {
            titleView = new BaseTitleView(this);
            fltitle.addView(titleView, 0);
            titleView.setOnClickListener(this);
            fltitle.setVisibility(View.VISIBLE);
        }
        fltitle.setVisibility(View.VISIBLE);
        rootView.setOnClickListener(this);

    }

    public void hiddenTitle() {
        fltitle.setVisibility(View.GONE);
        if (titleView != null) {
            rootView.removeView(titleView);
        }
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_leftText:
                leftTextOnClick();
                break;
            case R.id.iv_left:
                leftImgOnClick();
                break;
            case R.id.tv_rightText:
                rightTextOnClick();
                break;
            case R.id.iv_right:
                rightImgOnClick();
                break;
            case R.id.tv_title:
                titleTextOnClick();
                break;
            case R.id.ll_left:
                leftOnClick();
                break;
            case R.id.ll_right:
                rightOnClick();
                break;
        }
    }


    public void leftOnClick() {
        finish();
    }

    public void rightOnClick() {
    }

    public void leftTextOnClick() {
    }

    public void rightTextOnClick() {
    }

    public void titleTextOnClick() {
    }

    public void leftImgOnClick() {
    }

    public void rightImgOnClick() {
    }


    public void setErrLayout(View errLayout) {
        flErrLayout.setVisibility(View.VISIBLE);
        flErrLayout.removeAllViews();
        flErrLayout.addView(errLayout);
    }

    public void hiddenErrLayout() {
        flErrLayout.setVisibility(View.GONE);
    }
}
