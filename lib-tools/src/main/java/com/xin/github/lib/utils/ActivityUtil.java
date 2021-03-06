package com.xin.github.lib.utils;

import android.app.Activity;

/**
 * Created by zxj on 2019/8/1.
 */
public class ActivityUtil {

    /**
     * 检查activity是否存活
     */
    public static boolean isAlive(Activity act) {
        if (act == null) return false;
        return !act.isFinishing() && !act.isDestroyed();
    }
}
