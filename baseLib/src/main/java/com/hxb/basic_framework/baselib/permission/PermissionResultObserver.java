package com.hxb.basic_framework.baselib.permission;

import com.tbruyelle.rxpermissions2.Permission;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 使用rxPermission库申请权限时，用这个observer
 * 这里面集中处理权限被拒绝后弹出提示框的逻辑
 * @author hxb
 */
public abstract class PermissionResultObserver implements Observer<Permission> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Permission p) {
        if(p.granted){
            onGranted();
        }else{
            if (p.shouldShowRequestPermissionRationale) {
                //当用户选择的禁止,并且没有勾选 "禁止后不再询问"
                //TODO 此处应弹出dialog,提示必须授予权限才能使用

            } else {
                //当选用选择了禁止，并且勾选了 "禁止后不再询问"
                //TODO 此处应弹出dialog,提示权限异常，必须去系统的权限设置界面打开权限

            }

        }
    }

    /**
     * 子类实现此方法来处理权限申请成功的情况
     */
    protected abstract void onGranted();

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
