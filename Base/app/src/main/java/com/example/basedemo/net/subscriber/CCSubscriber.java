package com.example.basedemo.net.subscriber;


import com.example.basedemo.net.BaseEntity;
import com.example.basedemo.net.exception.CCHttpException;
import com.example.basedemo.net.exception.ExceptionHandler;
import com.example.basedemo.utils.LogUtil;
import com.google.gson.Gson;

import de.greenrobot.event.EventBus;


public abstract class CCSubscriber<T> extends BaseSubscriber<BaseEntity<T>> {

    public CCSubscriber() {
    }

    public CCSubscriber(boolean showErrorToast) {
        super(showErrorToast);
    }

    @Override
    protected final void onSuccess(BaseEntity<T> data) {
        if ("10002".equals(data.getRespCode())) {
            if (data != null)
                LogUtil.e("http", new Gson().toJson(data));
            onCCSuccess(data.data);
        } else {
            if (data != null)
                LogUtil.e("http", new Gson().toJson(data));
            handleBiz(data);
        }
    }

    @Override
    public final void onError(Throwable e) {
        CCHttpException exception = ExceptionHandler.handleHttpException(e);
        if (!onHttpOrDataRevertError(exception.mCode, exception.mMessage)) {
            onFailed(exception.mCode, exception.mMessage);
        }
        onFinish();
    }

    /**
     * @param baseEntity
     */
    private void handleBiz(BaseEntity baseEntity) {
        CCHttpException bizException = ExceptionHandler.handleBizException(baseEntity);
        if (!onBizError(baseEntity)) {
            if ("10107".equals(bizException.mCode)) {
                EventBus.getDefault().post("notlogin");
            } else {
                onFailed(bizException.mCode, bizException.mMessage);
            }
        }
    }


    /**
     * http请求成功，返回 数据协议里面的data字段
     *
     * @param data
     */
    protected abstract void onCCSuccess(T data);

    /**
     * 数据协议 code 值不为0 的回调
     *
     * @param baseEntity
     * @return 返回true则不再调用onFailed()方法，false则继续调用onFailed()
     */
    protected boolean onBizError(BaseEntity baseEntity) {
        return false;
    }

    /**
     * 底层 http层请求失败 或 数据转换失败 回调
     *
     * @param code
     * @param message
     * @return 返回true则不再调用onFailed()方法，false则继续调用onFailed()
     */
    protected boolean onHttpOrDataRevertError(String code, String message) {
        return false;
    }

    @Override
    protected void onFinish() {

    }

    /**
     * 数据协议层  code 是否 为0
     *
     * @param code
     * @return
     */
    private boolean isSuccessCode(int code) {
        return code == 0;
    }
}
