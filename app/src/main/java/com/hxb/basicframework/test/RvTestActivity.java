package com.hxb.basicframework.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hxb.basic_framework.baselib.adapter.BaseRvAdapter;
import com.hxb.basic_framework.baselib.utils.Logger;
import com.hxb.basicframework.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RvTestActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_rl_test);
        ButterKnife.bind(this);

        initViews();
    }


    private void initViews() {
        rv.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration decor=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        decor.setDrawable(getResources().getDrawable(R.drawable.divider_common));

        rv.addItemDecoration(decor);

        TestRvAdapter testRvAdapter=new TestRvAdapter(this);


        testRvAdapter.setChildClickListener((adapter, v, position) -> {

            String item = (String) adapter.getItem(position);

            switch (v.getId()) {
                case R.id.btn_1:
                    Logger.i("position: " + position + "--item: " + item + "--btn_1");
                    break;
                case R.id.btn_2:
                    Logger.i("position: " + position + "--item: " + item + "--btn_2");
                    break;
                case R.id.item_total:
                    Logger.i("position: " + position + "--item: " + item + "--item_total");
                    break;
            }
        });

        rv.setAdapter(testRvAdapter);
        testRvAdapter.setData(getSimulateData());
    }



    private List<String> getSimulateData() {
        List<String> data = new ArrayList<>();

        data.add("aa");
        data.add("bb");
        data.add("dd");
        data.add("cc");
        data.add("ee");
        data.add("ff");
        data.add("gg");
        data.add("hh");
        data.add("ii");
        data.add("jj");
        data.add("kk");
        data.add("ll");
        data.add("mm");
        data.add("nn");
        data.add("oo");
        data.add("pp");
        data.add("qq");
        data.add("rr");
        data.add("ss");
        data.add("tt");

        return data;
    }



    public static class TestRvAdapter extends BaseRvAdapter<String,ViewHolder>{

        public TestRvAdapter(Context mContext) {
            super(mContext);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflate(parent, R.layout.list_item_simple);
            return new ViewHolder(itemView);
        }

        @Override
        protected void onBindViewHolder(ViewHolder holder, int position, String item) {
            holder.tv.setText(item);

            //绑定点击监听
            bindOnClickListener(holder,
                    R.id.btn_1,R.id.btn_2,R.id.item_total);
        }

    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv)
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
