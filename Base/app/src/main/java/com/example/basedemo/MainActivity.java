package com.example.basedemo;

import android.view.View;

import com.example.basedemo.base.BaseActivity;
import com.example.basedemo.base.BaseVM;
import com.example.basedemo.databinding.ActivityMainBinding;
import com.example.basedemo.minterface.BaseDialogCallBack;
import com.example.basedemo.minterface.SingleClickListener;
import com.example.basedemo.utils.DialogUtils;

public class MainActivity extends BaseActivity<ActivityMainBinding, BaseVM> {


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public BaseVM createVM() {
        return null;
    }

    @Override
    public void initView() {
        setTitle("节日快乐");//设置标题
        hideStatusBar();//浸入式
        hiddenTitle();//隐藏标题

        mBinding.tvOne.setOnClickListener(view -> DialogUtils.show(getSupportFragmentManager(), "测试标题", "确定", new BaseDialogCallBack() {
            @Override
            public void onLeftClick(View view) {

            }

            @Override
            public void onRightText(View view) {

            }
        }));
        mBinding.tvTwo.setOnClickListener(view -> DialogUtils.showSingle(getSupportFragmentManager(), "测试标题", "确定", new SingleClickListener() {
            @Override
            public void onClick(View view) {

            }
        }));


    }

    @Override
    public void initData() {


    }


}
