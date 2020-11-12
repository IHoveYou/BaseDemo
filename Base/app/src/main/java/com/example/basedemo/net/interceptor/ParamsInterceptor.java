package com.example.basedemo.net.interceptor;



import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public abstract class ParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {//拦截器 统一加头部
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
//        builder.addHeader("identity", BaseApplication.getInstance().getIdentity() );
//        builder.addHeader("vid",  BaseApplication.getInstance().getVid());
//        builder.addHeader("token", BaseApplication.getInstance().getToken());
        return chain.proceed(builder.build());
    }


}
