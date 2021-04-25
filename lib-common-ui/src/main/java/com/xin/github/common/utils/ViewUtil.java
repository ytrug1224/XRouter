package com.xin.github.common.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

import com.xin.github.lib.api.AppContext;

/**
 * Created by zxj on 2019/8/2.
 */
public class ViewUtil {

    public static View inflate(@LayoutRes int layout, ViewGroup parent, boolean attachToParent) {
        return LayoutInflater.from(AppContext.getAppContext()).inflate(layout, parent, attachToParent);
    }
}
