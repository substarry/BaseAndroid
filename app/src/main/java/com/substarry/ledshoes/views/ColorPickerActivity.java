package com.substarry.ledshoes.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.substarry.colorpicker.ColorPicker;
import com.substarry.ledshoes.adapter.ColorAdapter;
import com.substarry.ledshoes.R;
import com.substarry.ledshoes.adapter.BaseRecyclerViewHolder;
import com.substarry.ledshoes.bean.ColorBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class ColorPickerActivity extends BaseToolbarActivity {

    private LinearLayoutManager mLayoutManager;
    private ColorAdapter mAdapter;
    private boolean isLoadMore =false;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.color_picker)
    ColorPicker colorPicker;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_color_picker;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        colorPicker.setShowOldCenterColor(false);
        colorPicker.setTouchAnywhereOnColorWheelEnabled(true);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
    }


    @Override
    protected void initData() {

        this.mAdapter = new ColorAdapter(this);
        List<ColorBean> colorBeanList = new ArrayList<>();

        colorBeanList.add(new ColorBean(Color.RED));
        colorBeanList.add(new ColorBean(0xFFFF7F00));
        colorBeanList.add(new ColorBean(Color.YELLOW));
        colorBeanList.add(new ColorBean(Color.GREEN));
        colorBeanList.add(new ColorBean(Color.CYAN));
        colorBeanList.add(new ColorBean(Color.BLUE));
        colorBeanList.add(new ColorBean(0xFFFF00FF));

        mAdapter.setList(colorBeanList);
        this.mAdapter.setOnItemClickListener(new BaseRecyclerViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View convertView, int position) {

                ColorBean colorBean = mAdapter.getItem(position);
                mAdapter.setEnabledPosition(position);
                mAdapter.notifyDataSetChanged();
                colorPicker.setColor(colorBean.getRgbColor());
            }
        });
        this.recyclerView.setAdapter(this.mAdapter);

    }

    @Override
    protected void initListeners() {
        colorPicker.setOnColorChangedListener(new ColorPicker.OnColorChangedListener() {
            @Override
            public void onColorChanged(int color) {
            }
        });

        colorPicker.setOnColorSelectedListener(new ColorPicker.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                Log.d("HELING_DEBUG","onColorSelected = " +color);
                int current = mAdapter.getEnabledPosition();
                if(current >= 0 && current < mAdapter.getItemCount()){
                    ((ColorBean)mAdapter.getItem(current)).setRgbColor(color);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
