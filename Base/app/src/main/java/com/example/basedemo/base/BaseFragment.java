package com.example.basedemo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * 作者 lxy on Time 2020-08-1314:22.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 */
public abstract class BaseFragment<B extends ViewDataBinding, VM extends BaseVM> extends Fragment implements BaseView<VM> {
    public View contentView;
    public B mBinding;
    public VM mVM;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mVM = createVM();
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), getLayout(), null, false);
        contentView = mBinding.getRoot();
        init();

        return contentView;
    }

    private void init() {
        initView();
        initData();
    }


    //刷新
    public void refresh() {

    }

}
