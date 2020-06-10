package com.hxb.basic_framework.baselib.http;

import android.content.Context;
import android.view.View;

import com.dyhdyh.widget.loadingbar2.LoadingBar;
import com.hxb.basic_framework.baselib.http.resp.IResp;
import com.hxb.basic_framework.baselib.loadingbar.AbsLoadingViewHolder;
import com.hxb.basic_framework.baselib.loadingbar.CommonLoadingViewHolder;

import io.reactivex.disposables.Disposable;

/**
 *
 * @param <T>
 */
public abstract class LoadingViewApiRespObserver<T extends IResp> extends ApiRespObserver<T> {

    private AbsLoadingViewHolder loadingViewHolder;
    private View loadingViewParent;

    private View.OnClickListener retryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LoadingBar.view(loadingViewParent)
                    .cancel();
            onRetryClick();
        }
    };

    public LoadingViewApiRespObserver(Context context, View loadingViewParent) {
        this.loadingViewParent = loadingViewParent;
        loadingViewHolder = getLoadingViewHolder(context);
        loadingViewHolder.setRetryOnClickListener(retryClickListener);
    }

    @Override
    public void onSubscribe(Disposable d) {
        loadingViewHolder.showLoadingTipView();
        LoadingBar.view(loadingViewParent)
                .setFactoryFromView(loadingViewHolder.getRootView())
                .show();
    }

    /**
     * 可重新此方法，在上层代码决定具体使用哪种类型的AbsLoadingViewHolder
     * @param context
     * @return
     */
    protected AbsLoadingViewHolder getLoadingViewHolder(Context context){
        return new CommonLoadingViewHolder(context);
    }

    /**
     * 点击重试按钮时，此方法将会被调用
     */
    protected abstract void onRetryClick();

    @Override
    protected void onFail(RespFailSpec failSpec) {}

    @Override
    protected void onFinish(boolean isSuccess) {
        if (isSuccess) {
            LoadingBar.view(loadingViewParent)
                    .cancel();
        }else{
            loadingViewHolder.showLoadFailView();
        }
    }
}
