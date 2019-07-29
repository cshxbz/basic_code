package com.hxb.basic_framework.baselib.loadingbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class AbsLoadingViewHolder {

    protected View rootView;
    protected View loadingTipView;//正在加载中的时候显示的view
    protected View loadFailView;//加载失败时显示的view
    protected View retryView;//重试点击按钮

    protected abstract int getRootViewLayoutId();

    protected abstract int getLoadingTipViewId();

    protected abstract int getLoadFailViewId();

    protected abstract int getRetryViewId();

    public AbsLoadingViewHolder(Context context) {
        LayoutInflater lif = LayoutInflater.from(context);
        rootView = lif.inflate(getRootViewLayoutId(), null);
        initViews();
    }

    public void showLoadingTipView() {
        loadingTipView.setVisibility(View.VISIBLE);
        loadFailView.setVisibility(View.GONE);
    }

    public void showLoadFailView(){
        loadFailView.setVisibility(View.VISIBLE);
        loadingTipView.setVisibility(View.GONE);
    }

    private void initViews() {
        loadingTipView = rootView.findViewById(getLoadingTipViewId());
        loadFailView = rootView.findViewById(getLoadFailViewId());
        retryView = rootView.findViewById(getRetryViewId());
    }

    public void setRetryOnClickListener(View.OnClickListener listener){
        retryView.setOnClickListener(listener);
    }

}
