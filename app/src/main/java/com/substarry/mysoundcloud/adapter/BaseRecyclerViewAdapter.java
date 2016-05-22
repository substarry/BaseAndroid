package com.substarry.mysoundcloud.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 何凌 on 2016/5/20.
 */
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    private ArrayList mList;
    private BaseRecyclerViewHolder.OnItemClickListener onItemClickListener;
    private BaseRecyclerViewHolder.OnItemLongClickListener onItemLongClickListener;


    public BaseRecyclerViewAdapter() {
        this.mList = new ArrayList();
    }

    @SuppressWarnings("unchecked")
    public <T> T getItem(int position) {
        return (T) this.mList.get(position);
    }

    public <T> T getItemByPosition(int position) {
        return this.getItem(position);
    }

    @SuppressWarnings("unchecked")
    public void setList(List list) {
        this.mList.clear();
        if (list == null) return;
        this.mList.addAll(list);
    }


    public void clear() {
        this.mList.clear();
    }


    public void remove(Object o) {
        this.mList.remove(o);
    }


    public List getList() {
        return this.mList;
    }


    @SuppressWarnings("unchecked")
    public void addAll(Collection list) {
        this.mList.addAll(list);
    }


    /**
     * Please return RecyclerView loading layout Id array
     * 请返回RecyclerView加载的布局Id数组
     *
     * @return 布局Id数组
     */
    public abstract int[] getItemLayouts();


    public abstract BaseRecyclerViewHolder onCreateRecycleViewHolder(View view, int viewType);
    /**
     * butt joint the onBindViewHolder and
     * If you want to write logic in onBindViewHolder, you can write here
     * 对接了onBindViewHolder
     * onBindViewHolder里的逻辑写在这
     *
     * @param viewHolder viewHolder
     * @param position   position
     */
    public abstract void onBindRecycleViewHolder(BaseRecyclerViewHolder viewHolder, int position);

    /**
     * Please write judgment logic when more layout
     * and not write when single layout
     * 如果是多布局的话，请写判断逻辑
     * 单布局可以不写
     *
     * @param position Item position
     * @return 布局Id数组中的index
     */
    public abstract int getRecycleViewItemType(int position);


    /**
     * get the itemType by position
     * 根据position获取ItemType
     *
     * @param position Item position
     * @return 默认ItemType等于0
     */
    @Override
    public int getItemViewType(int position) {
        return this.getRecycleViewItemType(position);
    }


    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType < 0) return null;
        if (this.getItemLayouts() == null) return null;
        int[] layoutIds = this.getItemLayouts();
        if (layoutIds.length < 1) return null;

        int itemLayoutId;
        if (layoutIds.length == 1) {
            itemLayoutId = layoutIds[0];
        } else {
            itemLayoutId = layoutIds[viewType];
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
        return this.onCreateRecycleViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        try {
            this.onBindRecycleViewHolder(holder, position);
            holder.setOnItemClickListener(this.onItemClickListener, position);
            holder.setOnItemLongClickListener(this.onItemLongClickListener, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return this.mList.size();
    }


    /**
     * set the on item click listener
     * 设置点击事件
     *
     * @param onItemClickListener onItemClickListener
     */
    public void setOnItemClickListener(BaseRecyclerViewHolder.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    /**
     * set the on item long click listener
     * 设置长点击事件
     *
     * @param onItemLongClickListener onItemLongClickListener
     */
    public void setOnItemLongClickListener(BaseRecyclerViewHolder.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
