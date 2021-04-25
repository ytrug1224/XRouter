package com.xin.github.uilib.base.adapter.interfaces;

import android.view.View;

/**
 * Created by zxj on 2019/8/3.
 */
public interface OnItemClickListener<T> {

    void onItemClick(View v, int position, T data);
}
