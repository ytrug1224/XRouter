package com.xin.github.lib.interfaces;


import com.xin.github.lib.helper.PermissionHelper;

import java.util.Collection;

/**
 * Created by zxj on 2018/5/7.
 */
public interface OnPermissionGrantListener {
    void onPermissionGranted(Collection<PermissionHelper.Permission> permissions);

    void onPermissionsDenied(Collection<PermissionHelper.Permission> permissions);

    abstract class OnPermissionGrantListenerAdapter implements OnPermissionGrantListener {
        @Override
        public void onPermissionGranted(Collection<PermissionHelper.Permission> permissions) {

        }

        @Override
        public void onPermissionsDenied(Collection<PermissionHelper.Permission> permissions) {

        }
    }
}
