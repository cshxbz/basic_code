package com.hxb.basic_framework.baselib.loadingbar;

import android.content.Context;

public class CommonLoadingViewHolder extends AbsLoadingViewHolder {

    public CommonLoadingViewHolder(Context context) {
        super(context);
    }

    @Override
    protected int getRootViewLayoutId() {
        return 0;
    }

    @Override
    protected int getLoadingTipViewId() {
        return 0;
    }

    @Override
    protected int getLoadFailViewId() {
        return 0;
    }

    @Override
    protected int getRetryViewId() {
        return 0;
    }
}
