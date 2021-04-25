package com.xin.github.uilib.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xin.github.lib.api.AppContext;

import androidx.annotation.LayoutRes;

/**
 * Created by zxj on 2019/8/2.
 */
public class ViewUtil {

    public static View inflate(@LayoutRes int layout, ViewGroup parent, boolean attachToParent) {
        return LayoutInflater.from(AppContext.getAppContext()).inflate(layout, parent, attachToParent);
    }
}
