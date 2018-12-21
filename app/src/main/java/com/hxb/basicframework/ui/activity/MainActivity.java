
package com.hxb.basicframework.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hxb.basic_framework.baselib.http.RespFunction;
import com.hxb.basic_framework.baselib.http.RespObserver;
import com.hxb.basicframework.R;
import com.hxb.basicframework.test.SimulateReqCreator;
import com.hxb.basicframework.utils.L;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReq();
    }


    private void initReq(){
        SimulateReqCreator.getReqA()

                .flatMap(new RespFunction() {
                    @Override
                    public Observable<String> onSuccess(String data) {
                        L.i(data);

                        return SimulateReqCreator.getReqB();
                    }
                })

                .flatMap(new RespFunction() {
                    @Override
                    public Observable<String> onSuccess(String data) {

                        return SimulateReqCreator.getReqC();
                    }
                })

                .flatMap(new RespFunction() {
                    @Override
                    public Observable<String> onSuccess(String data) {

                        return null;

                    }
                })

                .subscribe(new RespObserver(this) {
                    @Override
                    public void onSuccess(String data) {
                        L.i(data);
                    }
                });
    }


    private Observable<?> getOtherObserver(){
        Observable<Integer> ob = Observable.create(e -> {
            //此处进行本地的异步任务
        });

        return ob;
    }
}
