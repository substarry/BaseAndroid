package com.substarry.ledshoes.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.substarry.ledshoes.R;

import butterknife.Bind;

/**
 * Created by 何凌 on 2016/5/20.
 */
public class ChartAdapter extends BaseRecyclerViewAdapter {

    private Context context;

    public ChartAdapter(Context context) {
        super();
        this.context = context;
    }


    public class ChartViewHolder extends BaseRecyclerViewHolder {

        @Bind(R.id.chart_img)
        ImageView chartImg;

        @Bind(R.id.chart_title_tv)
        TextView chartTitleTv;

        @Bind(R.id.chart_date_tv)
        TextView chartDataTv;

        public ChartViewHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public int[] getItemLayouts() {
        return new int[] { R.layout.item_chart };
    }

    @Override
    public BaseRecyclerViewHolder onCreateRecycleViewHolder(View view, int viewType) {
        switch (viewType) {
            case 0:
            default:
                return new ChartViewHolder(view);
        }
    }

    @Override
    public void onBindRecycleViewHolder(BaseRecyclerViewHolder viewHolder, int position) {

        ChartViewHolder holder = (ChartViewHolder) viewHolder;

    }

    @Override
    public int getRecycleViewItemType(int position) {
        return 0;
    }



}
