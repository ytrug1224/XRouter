package com.xin.github.common.pullrefresh.interfaces;

import com.xin.github.common.pullrefresh.PullRefreshLayout;

/**
 * Created by zxj on 2019/8/4.
 */
public interface OnPullLayoutStateListener {

    void onStart(PullRefreshLayout.LayoutState state);

    void onPull(PullRefreshLayout.LayoutState state);

    void onRelease(PullRefreshLayout.LayoutState state);

    void onReset(PullRefreshLayout.LayoutState state);

}
