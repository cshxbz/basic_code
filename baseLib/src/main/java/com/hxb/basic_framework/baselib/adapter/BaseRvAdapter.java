package com.hxb.basic_framework.baselib.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.List;

/**
 * RecyclerView Adapter 抽象基类
 * @author hxb
 * @param <T>
 * @param <VH>
 */
public abstract class BaseRvAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private Context mContext;
    private List<T> mData;

    protected View inflate(ViewGroup parent,int layoutResId){
        return LayoutInflater.from(mContext).inflate(layoutResId, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        onBindViewHolder(holder,position,getItem(position));
    }

    protected abstract void onBindViewHolder(VH holder, int position, T item);

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    /**
     *
     * @param position
     * @return
     */
    public T getItem(int position){
        T itemData=null;
        if(mData!=null){
            itemData=mData.get(position);
        }
        return itemData;
    }


    /**
     *
     * @param collection
     */
    public void addItemAll(Collection<? extends T> collection) {
        int notifyStartPosition = mData.size();
        mData.addAll(collection);
        notifyItemRangeInserted(notifyStartPosition, collection.size());
    }


}
