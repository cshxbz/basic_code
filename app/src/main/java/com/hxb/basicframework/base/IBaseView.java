package com.hxb.basicframework.base;


import android.support.annotation.NonNull;

/**
 * MVP BaseView
 *  @author hxb
 */
public interface IBaseView {

    /**
     * 初始化presenter
     *
     * @return
     */
    @NonNull
    BasePresenter initPresenter();


    /**
     * 使用toast显示信息
     *
     * @param msg
     */
    void showToast(String msg);


    /**
     * 显示等待提示Dialog
     *
     * @param name
     */
    void showWaitTipDialog(String name);

    /**
     * 关闭等待提示Dialog
     */
    void dismissWaitTipDialog();


    /**
     * 界面状态枚举
     */
    public static enum UiStatus {
        LOADING,//初始化加载数据中
        LOADING_FAIL,//加载失败
        LOADING_SUCC,;//加载成功
    }


    /**
     * 切换界面状态
     * @param status
     */
    void switchUiStatus(UiStatus status);


    /**
     * 隐藏键盘
     */
    void hideKeybord();


    /**
     * 回退
     */
    void back();

}

