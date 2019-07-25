package com.hxb.basicframework.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.dyhdyh.widget.loadingbar2.LoadingBar;
import com.hxb.basicframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoadingBarTestActivity extends AppCompatActivity {

    @BindView(R.id.fl_content)
    FrameLayout flContent;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);


    }


    @OnClick({R.id.btn_show_loading_view})
    public void showLoadingView(){
        LoadingBar.view(flContent).show();
        handler.postDelayed(() -> {
            LoadingBar.view(flContent).cancel();
        }, 3000);
    }


    @OnClick({R.id.btn_show_loading_dialog})
    public void showLoadingDialog(){

    }

}
