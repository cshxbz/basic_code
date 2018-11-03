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
 *
 * @param <T>  数据类型
 * @param <VH> ViewHolder类型
 * @author hxb
 */
public abstract class BaseRvAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context mContext;
    private List<T> mData;

    /**
     * 外部设置进来的条目子View监听器
     */
    private OnChildClickListener mChildClickListener;

    public interface OnChildClickListener{
        void onClick(BaseRvAdapter adapter, View view, int position);
    }


    public BaseRvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    protected View inflate(ViewGroup parent, int layoutResId) {
        return LayoutInflater.from(mContext).inflate(layoutResId, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        onBindViewHolder(holder, position, getItem(position));
    }

    /**
     * 子类实现此方法绑定数据到子View上
     */
    protected abstract void onBindViewHolder(VH holder, int position, T item);

    /**
     * 调用此方法来为子View绑定点击监听，绑定了监听的子View
     *
     * @param childIds 要绑定监听的子View的id
     */
    protected final void bindOnClickListener(VH holder,int... childIds) {
        for (int id : childIds) {
            View childView = holder.itemView.findViewById(id);
            if (childView != null) {
                childView.setOnClickListener(new InnerOnClickListener<>(this,holder));
            }
        }
    }

    /**
     * 外部调用此方法，设置条目子View点击监听器
     */
    public void setChildClickListener(OnChildClickListener childClickListener) {
        this.mChildClickListener = childClickListener;
    }

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    /**
     * 获取一个条目的数据对象
     *
     * @param position 想要获得数据对象的位置
     * @return
     */
    public T getItem(int position) {
        T itemData = null;
        if (mData != null) {
            try {
                itemData = mData.get(position);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return itemData;
    }


    /**
     * 将一个数据列表添加到当前adapter数据列表的末尾
     */
    public void addItemAll(Collection<? extends T> collection) {
        int notifyStartPosition = mData.size();
        mData.addAll(collection);
        notifyItemRangeInserted(notifyStartPosition, collection.size());
    }



    private static class InnerOnClickListener<VH extends RecyclerView.ViewHolder> implements View.OnClickListener {
        private BaseRvAdapter mAdapter;
        private VH mHolder;

        public InnerOnClickListener(BaseRvAdapter mAdapter,VH mHolder) {
            this.mAdapter = mAdapter;
            this.mHolder = mHolder;
        }

        @Override
        public void onClick(View view) {
            if(mAdapter.mChildClickListener!=null){
                int position = mHolder.getLayoutPosition();
                mAdapter.mChildClickListener.onClick(mAdapter,view,position);
            }
        }
    }


}
