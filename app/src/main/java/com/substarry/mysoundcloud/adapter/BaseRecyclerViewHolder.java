package com.substarry.mysoundcloud.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by 何凌 on 2016/5/20.
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {


    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    /**
     * the click listeners callback
     * 点击事件回调
     */
    public interface OnItemClickListener {
        /**
         * on item click call back
         *
         * @param convertView convertView
         * @param position position
         */
        void onItemClick(View convertView, int position);
    }

    /**
     * the long click listeners callback
     * 长点击事件回调
     */
    public interface OnItemLongClickListener {
        /**
         * on item long click call back
         *
         * @param convertView convertView
         * @param position position
         * @return true false
         */
        boolean onItemLongClick(View convertView, int position);
    }

    /**
     * set the on item click listener
     * 设置Item的点击事件
     *
     * @param listener listener
     * @param position position
     */
    public void setOnItemClickListener(final BaseRecyclerViewHolder.OnItemClickListener listener, final int position) {
        if (listener == null) {
            this.itemView.setOnClickListener(null);
        } else {
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(v, position);
                }
            });
        }
    }


    /**
     * set the on item long click listener
     * 设置Item的长点击事件
     *
     * @param listener listener
     * @param position position
     */
    public void setOnItemLongClickListener(final BaseRecyclerViewHolder.OnItemLongClickListener listener, final int position) {
        if (listener == null) {
            this.itemView.setOnLongClickListener(null);
        } else {
            this.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override public boolean onLongClick(View v) {
                    return listener.onItemLongClick(v, position);
                }
            });
        }
    }
}
