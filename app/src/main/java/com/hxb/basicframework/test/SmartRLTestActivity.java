package com.hxb.basicframework.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hxb.basicframework.R;
import com.hxb.basicframework.adapter.base.BaseRvAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * @author hxb
 */
public class SmartRLTestActivity extends AppCompatActivity {

    @BindView(R.id.smartrl)
    SmartRefreshLayout smartRl;

    @BindView(R.id.rv)
    RecyclerView rv;

    private RvAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_rl_test);
        ButterKnife.bind(this);

        initSmartRl();
        initRv();


    }


    private void initSmartRl(){
        smartRl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);//传入false表示刷新失败
            }
        });
        smartRl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000);//传入false表示加载失败
            }
        });
    }

    private void initRv(){
        LinearLayoutManager llMgr = new LinearLayoutManager(this);
        llMgr.setOrientation(LinearLayoutManager.VERTICAL);

        rv.setLayoutManager(llMgr);

        adapter = new RvAdapter(this);
        adapter.setData(getSimulateData());

        rv.setAdapter(adapter);
    }


    private List<String> getSimulateData(){
        List<String> data = new ArrayList<>();

        data.add("aa");
        data.add("bb");
        data.add("cc");
        data.add("dd");
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

        return data;
    }


    public static class RvAdapter extends BaseRvAdapter<String,RvAdapter.Vh>{

        public RvAdapter(Context context) {
            super(context);
        }

        @Override
        protected int getItemViewResId(int viewType) {
            return R.layout.list_item_simple;
        }

        @Override
        protected Vh getViewHolder(View itemView) {
            return new Vh(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull Vh vh, int position) {
            vh.tv.setText(data.get(position));

            vh.tv.setOnClickListener(clickListener);
            vh.tv.setTag(data.get(position));
        }

        private View.OnClickListener clickListener=new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String tag = (String) v.getTag();
                Toast.makeText(context, tag, Toast.LENGTH_LONG).show();

            }
        };

        public class Vh extends RecyclerView.ViewHolder{
            @BindView(R.id.tv)
            TextView tv;

            public Vh(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }

    }







}
