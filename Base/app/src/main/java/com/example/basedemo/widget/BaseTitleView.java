package com.example.basedemo.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.basedemo.R;
import com.example.basedemo.databinding.BaseTitleBinding;

/**
 * 作者 lxy on Time 2020-08-1314:25.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 */
public class BaseTitleView extends FrameLayout {
    BaseTitleBinding mBinding;

    public BaseTitleView(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseTitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    protected void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.base_title, this, false);
        mBinding = DataBindingUtil.bind(view);
        addView(view);

        mBinding.llLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext() instanceof Activity) {
                    Activity activity = (Activity) getContext();
                    activity.finish();
                }
            }
        });
    }


    public void initTitle(int leftImage, String leftText, String title, String rightText, int rightImage) {
        mBinding.ivLeft.setImageResource(leftImage);
        mBinding.tvLeftText.setText(leftText);
        mBinding.tvTitle.setText(title);
        mBinding.tvRightText.setText(rightText);
        mBinding.ivRight.setImageResource(rightImage);
    }


    public void setTitleText(String text){
        mBinding.tvTitle.setText(text);
    }

    public void setLeftTextColor(@ColorRes int color) {
        mBinding.tvLeftText.setTextColor(getResources().getColor(color));
    }

    public void setTitleTextColor(@ColorRes int color) {
        mBinding.tvTitle.setTextColor(getResources().getColor(color));
    }

    public void setRightTextColor(@ColorRes int color) {
        mBinding.tvRightText.setTextColor(getResources().getColor(color));
    }


    public void setLeftImage(int res) {
        mBinding.ivLeft.setImageResource(res);
    }

    public void setRightImg(int res) {
        mBinding.ivLeft.setImageResource(res);
    }


}
