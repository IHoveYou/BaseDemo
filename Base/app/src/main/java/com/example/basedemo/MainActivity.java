package com.example.basedemo;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.View;

import com.example.basedemo.base.BaseActivity;
import com.example.basedemo.base.BaseVM;
import com.example.basedemo.databinding.ActivityMainBinding;

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
        setTitle("节日快乐");

    }

    @Override
    public void initData() {

//        for (int i = 0; i < 10; i++) {
//            final int finalI = i;
//            SQLUtils.newInstance().set("MainActivity1", finalI + "你好啊");
//            String s = SQLUtils.newInstance().get("MainActivity");
//            LogUtil.e("查询到的数据是:" + s);
//        }

        mHandler.postDelayed(r, 500);
    }

    int i = -1;

    final Handler mHandler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            switch (i) {
                case -1:
                    startObjectAnimatorAnims(mBinding.iv);
                    break;
                case 0:
                    startObjectAnimatorAnim(mBinding.tv1);
                    break;
                case 1:
                    startObjectAnimatorAnim(mBinding.tv2);
                    break;
                case 2:
                    startObjectAnimatorAnim(mBinding.tv3);
                    break;
                case 3:
                    startObjectAnimatorAnim(mBinding.tv4);
                    break;
                case 4:
                    startObjectAnimatorAnim(mBinding.tv5);
                    break;
                case 5:
                    mBinding.redRain.init();
                    mBinding.redRain.post(new Runnable() {
                        @Override
                        public void run() {
                            mBinding.redRain.start();
                        }
                    });
                    break;
            }
            i++;
            mHandler.postDelayed(this, 1500);
        }
    };

    /**
     * ObjectAnimator基本使用继承子ValueAnimator
     * 对对象v的alpha参数进行操作，alpha的值从1.0变到0.3
     *
     * @param v
     */
    public void startObjectAnimatorAnim(View v) {
        v.setVisibility(View.VISIBLE);
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(v, "alpha", 0.0f, 1f);
        //执行事件
        alphaAnim.setDuration(2000);
        //延迟
        alphaAnim.setStartDelay(0);
        alphaAnim.start();
    }

    public void startObjectAnimatorAnims(View v) {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(v, "alpha", 1.0f, 0f);
        //执行事件
        alphaAnim.setDuration(2000);
        //延迟
        alphaAnim.setStartDelay(0);
        alphaAnim.start();
    }

}
