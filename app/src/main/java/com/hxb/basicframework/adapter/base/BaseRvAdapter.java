package com.hxb.basicframework.adapter.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * adapter抽象基类
 *
 * @param <DT>数据类型
 * @param <VH>
 * @author hxb
 */
public abstract class BaseRvAdapter<DT, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context context;
    protected List<DT> data;

    public BaseRvAdapter(Context context) {
        this.context = context;
    }

    protected abstract int getItemViewResId(int viewType);

    protected abstract VH getViewHolder(View itemView);

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int resId = getItemViewResId(viewType);
        View itemView = LayoutInflater.from(context).inflate(resId, parent,false);
        return getViewHolder(itemView);
    }


    public void setData(List<DT> data){
        this.data=data;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return data==null ? 0:data.size();
    }

}
