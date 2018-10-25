package com.hxb.basicframework.base;

import android.support.annotation.NonNull;

/**
 *
 * MVP BasePresenter
 * @param <M> 子类决定具体的 IModel类型
 * @param <V> 子类决定具体的 IView 类型
 *
 * @author  hxb
 */
public abstract class BasePresenter<M,V> {

    private M mIModel;
    private V mIView;

    /**
     * 子类实现此方法，返回具体需要使用的IModel对象
     * @return
     */
    protected abstract M getModel();


    /**
     * 绑定IView 和  IModel
     */
    public void attachMV(@NonNull V v){
        this.mIModel = getModel();
        this.mIView = v;
    }


    /**
     * 解绑IView 和 IModel
     */
    public void detachMV(){
        this.mIModel = null;
        this.mIView = null;
    }





}
