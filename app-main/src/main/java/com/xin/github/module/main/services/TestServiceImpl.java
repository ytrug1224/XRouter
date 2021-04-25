package com.xin.github.module.main.services;

import com.xin.github.lib.utils.UIHelper;
import com.xin.github.router.TestService;
import com.xin.lib.annotations.ServiceImpl;

/**
 * Created by zxj on 2019/8/14.
 */
@ServiceImpl
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        UIHelper.toast("Test");
    }
}
