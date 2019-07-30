
package com.hxb.basicframework.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hxb.basic_framework.baselib.http.CommonResp;
import com.hxb.basic_framework.baselib.http.LoadingViewRespObserver;
import com.hxb.basic_framework.baselib.http.RespFunction;
import com.hxb.basic_framework.baselib.utils.Logger;
import com.hxb.basicframework.R;
import com.hxb.basicframework.api.TestApi;
import com.hxb.basicframework.entity.resp.DataA;
import com.hxb.basicframework.entity.resp.DataB;
import com.hxb.basicframework.http.ApiCreator;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl)
    View loadingViewParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initReq();
    }


    private void initReq() {
        ApiCreator.createApi(TestApi.class)

                .justTest(new TestParams("aaa","bbb","ccc"))


                .flatMap(new RespFunction<DataA, DataB>() {
                    @Override
                    public Observable<CommonResp<DataB>> onSuccess(CommonResp<DataA> resp) {

                        Logger.i(resp.toString());

                        return ApiCreator.createApi(TestApi.class).justTest2();
                    }
                })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new LoadingViewRespObserver<DataB>(this, loadingViewParent) {

                    @Override
                    protected void onSuccess(CommonResp<DataB> resp) {

                    }

                    @Override
                    protected void onRetryClick() {
                        initReq();
                    }
                });

    }



    public static class TestParams{
        String p1;
        String p2;
        String p3;

        public TestParams(String p1, String p2, String p3) {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        }
    }


}
