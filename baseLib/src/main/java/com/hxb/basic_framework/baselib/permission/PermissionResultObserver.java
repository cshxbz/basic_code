package com.hxb.basic_framework.baselib.permission;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.tbruyelle.rxpermissions2.Permission;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 使用rxPermission库申请权限时，用这个observer
 * 这里面集中处理权限被拒绝后弹出提示框的逻辑
 * @author hxb
 */
public abstract class PermissionResultObserver implements Observer<Permission> {

    private Context context;

    public PermissionResultObserver(Context context) {
        this.context = context;
    }

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
                showPermissionRationaleDialog();
            } else {
                //当选用选择了禁止，并且勾选了 "禁止后不再询问"
                showGoToPermissionPageDialog();
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


    /**
     * 弹出引导用户跳转到系统权限设置界面的dialog
     */
    private void showGoToPermissionPageDialog(){
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("权限异常，请前往系统权限设置界面打开权限。")
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //去设置页
                        PermissionTool.jumpPermissionPage(context);
                    }
                })
                .create()
                .show();

    }


    /**
     *
     * 弹出提示用户授权原因的dialog
     */
    private void showPermissionRationaleDialog(){
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("必须允许所有权限后才能使用此功能。")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .create()
                .show();
    }


}
