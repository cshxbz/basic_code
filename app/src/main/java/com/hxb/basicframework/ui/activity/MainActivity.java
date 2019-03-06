
package com.hxb.basicframework.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hxb.basic_framework.baselib.http.CommonResp;
import com.hxb.basic_framework.baselib.http.RespFunction;
import com.hxb.basic_framework.baselib.http.RespObserver;
import com.hxb.basic_framework.baselib.utils.L;
import com.hxb.basicframework.R;
import com.hxb.basicframework.api.TestApi;
import com.hxb.basicframework.entity.resp.DataA;
import com.hxb.basicframework.entity.resp.DataB;
import com.hxb.basicframework.http.ApiCreator;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReq();
    }


    private void initReq() {
        ApiCreator.createApi(TestApi.class)

                .justTest(new TestParams("aaa","bbb","ccc"))


                .flatMap(new RespFunction<DataA, DataB>() {
                    @Override
                    public Observable<CommonResp<DataB>> onSuccess(CommonResp<DataA> resp) {

                        L.i(resp.toString());

                        return ApiCreator.createApi(TestApi.class).justTest2();
                    }
                })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new RespObserver<DataB>() {
                    @Override
                    public void onSuccess(CommonResp<DataB> resp) {
                        L.i(resp.toString());
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
