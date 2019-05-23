package com.hxb.basicframework.test;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hxb.basic_framework.baselib.permission.PermissionResultObserver;
import com.hxb.basicframework.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class PermissionTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_permission_test);
    }


    public void onViewClick(View v){
        requestPermission();
    }


    private void requestPermission(){
        new RxPermissions(this)
                .requestEachCombined(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)

                .subscribe(new PermissionResultObserver(this) {
                    @Override
                    protected void onGranted() {
                        Toast.makeText(PermissionTestActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}
