package com.example.basedemo.utils;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.basedemo.R;
import com.example.basedemo.base.BaseDialog;
import com.example.basedemo.base.BaseVM;
import com.example.basedemo.databinding.BaseDialogBinding;
import com.example.basedemo.minterface.BaseDialogCallBack;
import com.example.basedemo.minterface.SingleClickListener;

/**
 * 作者 lxy on Time 2020-11-1315:11.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 */
public class DialogUtils extends BaseDialog<BaseDialogBinding, BaseVM> {
    BaseDialogCallBack callBack;
    SingleClickListener singleClickListener;
    String title;
    String leftText;
    String rightText;
    String buttonText;


    @Override
    public int getLayout() {
        return R.layout.base_dialog;
    }

    @Override
    public BaseVM createVM() {
        return null;
    }

    @Override
    public void initView() {
        title = getString("title", "");
        leftText = getString("leftText", "");
        rightText = getString("rightText", "");
        buttonText = getString("buttonText", "");
    }

    @Override
    public void initData() {
        mBinding.tvTitle.setText(title);
        if (!StringUtil.isEmpty(buttonText)) {
            mBinding.tvLeftText.setVisibility(View.GONE);
            mBinding.v.setVisibility(View.GONE);
            mBinding.tvRightText.setText(buttonText);
            mBinding.tvRightText.setOnClickListener(view -> {
                dismiss();
                if (singleClickListener == null) {
                    singleClickListener.onClick(view);
                }
            });
        } else {
            mBinding.tvLeftText.setText(leftText);
            mBinding.tvRightText.setText(rightText);
            mBinding.tvLeftText.setOnClickListener(view -> {
                dismiss();
                if (callBack != null) {
                    callBack.onLeftClick(view);
                }
            });
            mBinding.tvRightText.setOnClickListener(view -> {
                dismiss();
                if (callBack != null) {
                    callBack.onRightText(view);
                }
            });
        }


    }


    public static DialogUtils newInstance(String title, String leftText, String rightText, String button) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("leftText", leftText);
        args.putString("rightText", rightText);
        args.putString("buttonText", button);

        DialogUtils fragment = new DialogUtils();
        fragment.setArguments(args);
        return fragment;
    }

    public static void show(@NonNull FragmentManager manager, String title, String leftText, BaseDialogCallBack callBack) {
        DialogUtils utils = newInstance(title, leftText, "取消", "");
        utils.setCallBack(callBack);
        utils.show(manager, title);
    }

    public static void show(@NonNull FragmentManager manager, String title, String leftText, String rightTetx, BaseDialogCallBack callBack) {
        DialogUtils utils = newInstance(title, leftText, rightTetx, "");
        utils.setCallBack(callBack);
        utils.show(manager, title);
    }


    public static void showSingle(@NonNull FragmentManager manager, String title, String button, SingleClickListener singleClickListener) {
        DialogUtils utils = newInstance(title, "", "", button);
        utils.setSingleClickListener(singleClickListener);
        utils.show(manager, title);
    }

    public void setSingleClickListener(SingleClickListener singleClickListener) {
        this.singleClickListener = singleClickListener;
    }

    public void setCallBack(BaseDialogCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
    }
}
