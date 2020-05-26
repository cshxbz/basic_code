package com.hxb.basicframework.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dyhdyh.widget.loadingbar2.LoadingBar;
import com.hxb.basicframework.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoadingBarTestActivity extends AppCompatActivity {

    @BindView(R.id.fl_content)
    ViewGroup vgContent;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_bar_test);
        ButterKnife.bind(this);


    }


    @OnClick({R.id.btn_show_loading_view})
    public void showLoadingView(){
        showCustomLoadingView();
    }


    private ProgressBar pbLoading;
    private TextView tvLoadingFail;

    private void showCustomLoadingView(){
        View view = LayoutInflater.from(this).inflate(R.layout.loading_view, null);

        pbLoading = view.findViewById(R.id.pb_loading);
        tvLoadingFail = view.findViewById(R.id.tv_fail);

        pbLoading.setVisibility(View.VISIBLE);
        tvLoadingFail.setVisibility(View.GONE);

        tvLoadingFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbLoading.setVisibility(View.VISIBLE);
                tvLoadingFail.setVisibility(View.GONE);
                simulateIo();
            }
        });

        LoadingBar.view(vgContent)
                .setFactoryFromView(view)
                .show();

        simulateIo();
    }


    private void simulateIo(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Random r = new Random();
                int i = r.nextInt(2);

                if(i==1){
                    LoadingBar.view(vgContent).cancel();
                }else{
                    pbLoading.setVisibility(View.GONE);
                    tvLoadingFail.setVisibility(View.VISIBLE);
                }
            }
        }, 3000);
    }


    @OnClick({R.id.btn_show_loading_dialog})
    public void showLoadingDialog(){
        LoadingBar.dialog(this).show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadingBar.dialog(LoadingBarTestActivity.this).cancel();
            }
        }, 3000);
    }

}
