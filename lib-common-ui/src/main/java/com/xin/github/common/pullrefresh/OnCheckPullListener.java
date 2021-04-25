package com.xin.github.common.pullrefresh;

import android.view.View;

/**
 * Created by zxj on 2019/8/4.
 */
public interface OnCheckPullListener {
    boolean onCheckPullable(View v, int pointerId);
}
