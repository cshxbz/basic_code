
package com.hxb.basicframework.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hxb.basic_framework.baselib.http.RespObserver;
import com.hxb.basic_framework.baselib.http.RetrofitCreateHelper;
import com.hxb.basic_framework.baselib.utils.L;
import com.hxb.basicframework.R;
import com.hxb.basicframework.api.TestApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "https://www.easy-mock.com/mock/5c273d1cd746c431566468a0/android_mock_data/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReq();
    }


    private void initReq() {
        RetrofitCreateHelper.createApi(TestApi.class, BASE_URL)
                .justTest(new TestParams("aaa","bbb","ccc"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RespObserver(this) {
                    @Override
                    public void onSuccess(String data) {
                        L.i("data >> "+data);

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
