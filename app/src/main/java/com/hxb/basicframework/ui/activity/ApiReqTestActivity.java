
package com.hxb.basicframework.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hxb.basic_framework.baselib.http.ApiRespFunction;
import com.hxb.basic_framework.baselib.http.LoadingDialogApiRespObserver;
import com.hxb.basicframework.R;
import com.hxb.basicframework.api.TestApi;
import com.hxb.basicframework.entity.resp.CommonResp;
import com.hxb.basicframework.entity.resp.DataA;
import com.hxb.basicframework.entity.resp.DataB;
import com.hxb.basicframework.http.ApiCreator;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiReqTestActivity extends AppCompatActivity {

    @BindView(R.id.fl)
    View loadingViewParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_req_test);
        ButterKnife.bind(this);

        initReq();
    }


    private void initReq() {
        ApiCreator.createApi(TestApi.class)

                .getDataA(new TestParams("aaa", "bbb", "ccc"))

                .flatMap(new ApiRespFunction<CommonResp<DataA>, Observable<CommonResp<DataB>>>() {
                    @Override
                    public Observable<CommonResp<DataB>> onSuccess(CommonResp<DataA> resp) {

                        return ApiCreator.createApi(TestApi.class).getDataB();
                    }
                })

                .subscribe(new LoadingDialogApiRespObserver<CommonResp<DataB>>(this) {
                    @Override
                    protected void onSuccess(CommonResp<DataB> resp) {

                    }
                });
    }


    public void onCommitClick(View v) {
        commitReq();
    }


    private void commitReq() {
        ApiCreator.createApi(TestApi.class)
                .getDataA(new TestParams("aaa", "bbb", "ccc"))

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new LoadingDialogApiRespObserver<CommonResp<DataA>>(this) {
                    @Override
                    protected void onSuccess(CommonResp<DataA> resp) {

                    }
                });

    }


    public static class TestParams {
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
