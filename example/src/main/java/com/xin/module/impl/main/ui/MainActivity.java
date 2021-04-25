package com.xin.module.impl.main.ui;

import android.view.View;

import com.xin.github.common.base.BaseLibActivity;
import com.xin.github.module.main.ui.TimeLineFragment;

import com.zxj.module.impl.main.R;

public class MainActivity extends BaseLibActivity {

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView(View rootView) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, new TimeLineFragment(), "timeline")
                .commitAllowingStateLoss();
    }

}
