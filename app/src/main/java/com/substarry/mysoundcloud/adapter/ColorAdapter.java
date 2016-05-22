package com.substarry.mysoundcloud.adapter;

import android.content.Context;
import android.view.View;

import com.substarry.mysoundcloud.R;
import com.substarry.mysoundcloud.bean.ColorBean;
import com.substarry.mysoundcloud.widget.CircleImageView;

import butterknife.Bind;

/**
 * Created by 何凌 on 2016/5/20.
 */
public class ColorAdapter extends BaseRecyclerViewAdapter {

    private Context context;
    private int enabledPosition = -1;

    public ColorAdapter(Context context) {
        super();
        this.context = context;
    }


    public class ChartViewHolder extends BaseRecyclerViewHolder {

        @Bind(R.id.color_img)
        CircleImageView colorImg;

        public ChartViewHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public int[] getItemLayouts() {
        return new int[] { R.layout.item_color };
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
        ColorBean colorBean = getItem(position);
        holder.colorImg.setFillColor(colorBean.getRgbColor());
        holder.colorImg.setBorderColorResource(position == enabledPosition ? R.color.dack_enabled : android.R.color.transparent);

    }

    @Override
    public int getRecycleViewItemType(int position) {
        return 0;
    }

    public int getEnabledPosition() {
        return enabledPosition;
    }

    public void setEnabledPosition(int enabledPosition) {
        if(enabledPosition == this.enabledPosition){
            this.enabledPosition = -1;
        }
        else {
            this.enabledPosition = enabledPosition;
        }
    }
}
