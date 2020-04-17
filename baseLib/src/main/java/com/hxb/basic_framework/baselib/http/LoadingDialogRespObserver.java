package com.hxb.basic_framework.baselib.http;

import android.content.Context;

import com.dyhdyh.widget.loadingbar2.LoadingBar;

import io.reactivex.disposables.Disposable;

/**
 *
 * @param <T>
 */
public abstract class LoadingDialogRespObserver<T> extends RespObserver<T>  {

    private Context context;

    public LoadingDialogRespObserver(Context context) {
        this.context = context;
    }


    @Override
    public void onSubscribe(Disposable d) {
        LoadingBar.dialog(context)
                .show();
    }


    @Override
    protected void onFinish(boolean isSuccess) {
        LoadingBar.dialog(context)
                .cancel();
    }

    @Override
    protected void onFail(RespFailSpec failSpec) {

    }

}
