package com.xin.github.module.main.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xin.github.lib.utils.RandomUtil;
import com.xin.github.lib.utils.UIHelper;
import com.zxj.github.module.main.R2;
import com.xin.github.common.base.BaseAppFragment;
import com.zxj.github.module.main.R;

import com.xin.github.common.base.adapter.BaseMultiRecyclerViewHolder;
import com.xin.github.common.base.adapter.MultiRecyclerViewAdapter;
import com.xin.github.common.base.adapter.SimpleMultiType;
import com.xin.github.common.base.adapter.interfaces.OnItemClickListener;
import com.xin.github.common.base.adapter.interfaces.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by zxj on 2019/8/3.
 * <p>
 * 朋友圈时间线fragment
 */
public class TimeLineFragment extends BaseAppFragment {
    @BindView(R2.id.rv_content)
    RecyclerView mRvContent;

    @Override
    public int layoutId() {
        return R.layout.fragment_time_line;
    }

    @Override
    protected void onInitView(View rootView) {
        List<SimpleMultiType> multiTypes = new ArrayList<>();
        int[] types = {1, 2, 3};
        for (int i = 0; i < RandomUtil.randomInt(100, 500); i++) {
            multiTypes.add(new SimpleMultiType(types[RandomUtil.randomInt(0, 2)]));
        }
        MultiRecyclerViewAdapter<SimpleMultiType> mAdapter = new MultiRecyclerViewAdapter<>(getContext(), multiTypes);
        mAdapter.appendHolder(TestHolder1.class, 1)
                .appendHolder(TestHolder2.class, 2)
                .appendHolder(TestHolder3.class, 3);

        mRvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvContent.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener<SimpleMultiType>() {
            @Override
            public void onItemClick(View v, int position, SimpleMultiType data) {
                UIHelper.toast("" + position);
            }
        });
        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener<SimpleMultiType>() {
            @Override
            public void onItemLongClick(View v, int position, SimpleMultiType data) {
                UIHelper.toast("long " + position);
            }
        });
    }

    private static class TestHolder1 extends BaseMultiRecyclerViewHolder<SimpleMultiType> {
        TextView tv;

        public TestHolder1(@NonNull View itemView) {
            super(itemView);
            tv = findViewById(R.id.tv_test);
        }

        @Override
        public int layoutId() {
            return R.layout.test_layout1;
        }

        @Override
        public void onBindData(SimpleMultiType data, int position) {
            tv.setText("cur pos = " + position);
        }
    }

    private static class TestHolder2 extends BaseMultiRecyclerViewHolder<SimpleMultiType> {
        ImageView iv;
        TextView lable;

        public TestHolder2(@NonNull View itemView) {
            super(itemView);
            iv = findViewById(R.id.iv_test);
            lable = findViewById(R.id.iv_lable);
        }

        @Override
        public int layoutId() {
            return R.layout.test_layout2;
        }

        @Override
        public void onBindData(SimpleMultiType data, int position) {
            lable.setText(position + "aa");
        }
    }

    private static class TestHolder3 extends BaseMultiRecyclerViewHolder<SimpleMultiType> {
        TextView lable;

        public TestHolder3(@NonNull View itemView) {
            super(itemView);
            lable = findViewById(R.id.iv_lable3);
        }

        @Override
        public int layoutId() {
            return R.layout.test_layout3;
        }

        @Override
        public void onBindData(SimpleMultiType data, int position) {
            lable.setText(position + "aa1");
        }
    }
}
