package com.hxb.baselib.loadingbar;

import android.content.Context;

import com.hxb.baselib.R;

public class CommonLoadingViewHolder extends AbsLoadingViewHolder {

    public CommonLoadingViewHolder(Context context) {
        super(context);
    }

    @Override
    protected int getRootViewLayoutId() {
        return R.layout.common_loading_view;
    }

    @Override
    protected int getLoadingTipViewId() {
        return R.id.pb_tip;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.ll_load_fail;
    }

    @Override
    protected int getRetryViewId() {
        return R.id.tv_retry;
    }
}
