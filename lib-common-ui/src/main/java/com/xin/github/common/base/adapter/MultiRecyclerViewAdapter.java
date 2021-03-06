package com.xin.github.common.base.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.xin.github.lib.utils.log.KLog;
import com.xin.github.lib.utils.UnsafeUtil;
import com.xin.github.lib.utils.joor.Reflect;
import com.xin.github.lib.utils.joor.ReflectException;
import com.xin.github.common.utils.ViewUtil;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by zxj on 2019/8/3.
 */
public class MultiRecyclerViewAdapter<T extends MultiType> extends BaseRecyclerViewAdapter<T> {

    private static HashMap<String, Integer> mViewHolderIdCache = new HashMap<>();
    private SparseArray<ViewHolderInfo> mHolderInfos = new SparseArray<>();
    private static final Class<?>[] INSTANCE_TYPE = {View.class};

    private WeakReference<Object> outher;

    public MultiRecyclerViewAdapter(Context context) {
        super(context);
    }

    public MultiRecyclerViewAdapter(Context context, List<T> datas) {
        super(context, datas);
    }

    public MultiRecyclerViewAdapter appendHolder(Class<? extends BaseMultiRecyclerViewHolder> holderClass) {
        return appendHolder(holderClass, holderClass.hashCode());
    }

    public MultiRecyclerViewAdapter appendHolder(Class<? extends BaseMultiRecyclerViewHolder> holderClass, int... viewType) {
        for (int i : viewType) {
            if (mHolderInfos.get(i) == null) {
                mHolderInfos.put(i, new ViewHolderInfo(holderClass, i));
            }
        }
        return this;
    }

    public MultiRecyclerViewAdapter outher(Object object) {
        this.outher = new WeakReference<>(object);
        return this;
    }

    @Override
    public int onGetViewType(T data, int position) {
        return data.getItemType();
    }

    @Override
    public int holderLayout(int viewType) {
        ViewHolderInfo info = mHolderInfos.get(viewType);
        return info == null ? 0 : info.getLayoutId();
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder createViewHolder(@NonNull ViewGroup parent, @NonNull View itemView, int viewType) {
        if (mHolderInfos.size() < 0) {
            return createEmptyHolder();
        }
        ViewHolderInfo info = mHolderInfos.get(viewType);
        if (info == null) {
            KLog.e(TAG, "???????????????viewType???holder??????", viewType);
            return createEmptyHolder();
        }
        final int id = info.getLayoutId();

        if (id == 0) {
            KLog.e(TAG, "id???0", info.getHolderClass());
            return createEmptyHolder();
        }

        if (itemView == null) {
            itemView = ViewUtil.inflate(id, parent, false);
        }

        try {
            return Reflect.on(info.getHolderClass()).create(INSTANCE_TYPE, itemView).get();
        } catch (ReflectException e) {
            //?????????????????????????????????????????????viewholder???????????????????????????????????????
            if (outher != null && outher.get() != null) {
                //????????????????????????????????????viewholder??????????????????outher
                Class<?>[] types = {outher.get().getClass(), View.class};
                return Reflect.on(info.getHolderClass()).create(types, outher.get(), itemView).get();
            }
            //??????????????????Context????????????
            Class<?>[] types = {getContext().getClass(), View.class};
            return Reflect.on(info.getHolderClass()).create(types, getContext(), itemView).get();
        }
    }

    private static class ViewHolderInfo {
        final Class<? extends BaseMultiRecyclerViewHolder> mHolderClass;
        final int viewType;

        int layoutId;

        public ViewHolderInfo(Class<? extends BaseMultiRecyclerViewHolder> holderClass, int viewType) {
            mHolderClass = holderClass;
            this.viewType = viewType;
        }

        public int getLayoutId() {
            if (layoutId == 0) {
                searchLayout();
            }
            return layoutId;
        }

        public Class<? extends BaseMultiRecyclerViewHolder> getHolderClass() {
            return mHolderClass;
        }

        public int getViewType() {
            return viewType;
        }

        static String generateKey(Class clazz, int viewType) {
            return clazz.getName() + "$Type:" + viewType;
        }

        void searchLayout() {
            if (layoutId != 0) return;
            if (mHolderClass != null) {
                final String key = generateKey(mHolderClass, viewType);
                if (mViewHolderIdCache.containsKey(key)) {
                    layoutId = mViewHolderIdCache.get(key);
                    if (layoutId != 0) {
                        KLog.i("ViewHolderInfo", "id from cache : " + layoutId);
                        return;
                    }
                }
                //??????unsafe??????????????????????????????????????????????????????layoutid
                BaseMultiRecyclerViewHolder holder = UnsafeUtil.unsafeInstance(mHolderClass);
                if (holder == null) return;
                layoutId = holder.layoutId();
                if (layoutId != 0) {
                    mViewHolderIdCache.put(key, layoutId);
                }
                holder = null;
            }
        }
    }
}
