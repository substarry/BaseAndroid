package com.substarry.ledshoes.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.substarry.ledshoes.R;

import butterknife.Bind;


public class DemoDrawerLayoutActivity extends BaseDrawerLayoutActivity {

    @Bind(R.id.fab)
    FloatingActionButton fab;


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_drawer;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
    }


    @Override
    protected void initData() {


    }

    @Override
    protected void initListeners() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public void onSwipeRefresh() {

        mMultiSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh(false);
            }
        }, 2000);

    }

    @Override
    protected NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //防止重复点击
                return menuItemChecked(item.getItemId());
            }
        };
    }

    @Override
    protected int[] getMenuItemIds() {
        return new int[] { R.id.nav_camera, R.id.nav_gallery,
                R.id.nav_slideshow, R.id.nav_manage, R.id.nav_share, R.id.nav_send };
    }

    @Override
    protected void onMenuItemOnClick(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
    }

    private void getNextTracks(String next_href){
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
