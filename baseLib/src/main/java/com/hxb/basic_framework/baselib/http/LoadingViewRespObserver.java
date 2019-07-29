package com.hxb.basic_framework.baselib.http;

import android.content.Context;
import android.view.View;

import com.dyhdyh.widget.loadingbar2.LoadingBar;
import com.hxb.basic_framework.baselib.loadingbar.AbsLoadingViewHolder;
import com.hxb.basic_framework.baselib.loadingbar.CommonLoadingViewHolder;

import io.reactivex.disposables.Disposable;

/**
 *
 * @param <T>
 */
public abstract class LoadingViewRespObserver<T> extends RespObserver<T> {

    private AbsLoadingViewHolder loadingViewHolder;
    private View loadingViewParent;

    public LoadingViewRespObserver(Context context,View loadingViewParent,View.OnClickListener retryClickListener) {
        this.loadingViewParent = loadingViewParent;
        loadingViewHolder = new CommonLoadingViewHolder(context);
        loadingViewHolder.setRetryOnClickListener(retryClickListener);
    }


    @Override
    public void onSubscribe(Disposable d) {
        loadingViewHolder.showLoadingTipView();
        LoadingBar.view(loadingViewParent)
                .setFactoryFromView(loadingViewHolder.getRootView())
                .show();
    }

    @Override
    protected void onSuccess(CommonResp<T> resp) {
        LoadingBar.view(loadingViewParent)
                .cancel();
    }

    @Override
    protected void onFail(RespFailSpec failSpec) {
        loadingViewHolder.showLoadFailView();
    }


    @Override
    protected void onFinish(boolean isSuccess) {
    }
}
