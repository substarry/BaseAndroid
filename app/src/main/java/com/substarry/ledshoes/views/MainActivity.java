package com.substarry.ledshoes.views;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.substarry.ledshoes.adapter.BaseRecyclerViewHolder;
import com.substarry.ledshoes.R;
import com.substarry.ledshoes.adapter.ChartAdapter;
import com.substarry.ledshoes.widget.BorderDividerItemDecoration;

import butterknife.Bind;


public class MainActivity extends BaseSwipeRefreshLayoutActivity {

    private MediaPlayer mMediaPlayer;
    private LinearLayoutManager mLayoutManager;
    private ChartAdapter mAdapter;
    private boolean isLoadMore =false;

    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        autoRefresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // dy>0 表示向下滑动
                if(isLoadMore || dy <= 0 ){
                    return;
                }
                int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = mLayoutManager.getItemCount();

                if (lastVisibleItem >= totalItemCount - 2 ) {
                    getNextTracks();
                }
            }
        });
        recyclerView.addItemDecoration(
                new BorderDividerItemDecoration(this.getResources().getDimensionPixelOffset(R.dimen.border_divider_padding_vertical),
                        this.getResources().getDimensionPixelOffset(R.dimen.border_divider_padding_horizontal))
        );
    }


    @Override
    protected void initData() {

        this.mAdapter = new ChartAdapter(this);
        this.mAdapter.setOnItemClickListener(new BaseRecyclerViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View convertView, int position) {

            }
        });
        this.recyclerView.setAdapter(this.mAdapter);


    }

    @Override
    protected void initListeners() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });

    }

    @Override
    public void onSwipeRefresh() {

        isLoadMore =true;
        mMultiSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh(false);
            }
        },2000);
    }

    private void getNextTracks() {
        isLoadMore = true;
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
