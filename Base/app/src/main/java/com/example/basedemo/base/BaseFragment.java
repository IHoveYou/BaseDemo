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

import com.example.basedemo.R;
import com.example.basedemo.utils.ToastUtils;

/**
 * 作者 lxy on Time 2020-08-1314:22.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 */
public abstract class BaseFragment<B extends ViewDataBinding, VM extends BaseVM> extends Fragment implements BaseView<VM> {
    public View contentView;
    public B mBinding;
    public VM mVM;
    public ViewGroup flContext;
    public ViewGroup flErr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mVM = createVM();
        super.onCreate(savedInstanceState);
        contentView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.base_fragment, null, false);

        flContext = container.findViewById(R.id.fl_context);
        flErr = container.findViewById(R.id.fl_err);

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), getLayout(), null, false);
        flContext.addView(mBinding.getRoot());
        init();
        return contentView;
    }

    private void init() {
        try {
            initView();
            initData();
        } catch (Exception e) {
            ToastUtils.showMessage("程序出现错误,请刷新重试");
            getActivity().finish();
        }

    }

    public void setErrLayout(View errLayout) {
        flErr.setVisibility(View.VISIBLE);
        flErr.removeAllViews();
        flErr.addView(errLayout);
    }

    public void hiddenErrLayout() {
        flErr.setVisibility(View.GONE);
    }


    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            refresh();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            refresh();
        }
    }

    //刷新
    public void refresh() {

    }

}
