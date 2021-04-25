package com.xin.github.lib.interfaces;

/**
 * Created by zxj on 2018/6/13.
 * 升级版simpleCallback
 */
public abstract class ExtSimpleCallback<T> implements SimpleCallback<T> {

    public void onStart() {
    }

    public void onError(int code,String errorMessage) {
    }

    public void onFinish() {
    }
}
