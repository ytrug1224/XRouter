package com.xin.github.common.base.adapter;

import android.view.View;

import androidx.annotation.NonNull;

/**
 * Created by zxj on 2019/8/3.
 */
public abstract class BaseMultiRecyclerViewHolder<T extends MultiType> extends BaseRecyclerViewHolder<T> {
    public BaseMultiRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract int layoutId();
}
