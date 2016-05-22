/*
 * {EasyGank}  Copyright (C) {2015}  {CaMnter}
 *
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * The hypothetical commands `show w' and `show c' should show the appropriate
 * parts of the General Public License.  Of course, your program's commands
 * might be different; for a GUI interface, you would use an "about box".
 *
 * You should also get your employer (if you work as a programmer) or school,
 * if any, to sign a "copyright disclaimer" for the program, if necessary.
 * For more information on this, and how to apply and follow the GNU GPL, see
 * <http://www.gnu.org/licenses/>.
 *
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs.  If your program is a subroutine library, you
 * may consider it more useful to permit linking proprietary applications with
 * the library.  If this is what you want to do, use the GNU Lesser General
 * Public License instead of this License.  But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 */

package com.substarry.ledshoes.views;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.substarry.ledshoes.R;
import com.substarry.ledshoes.widget.MultiSwipeRefreshLayout;

import butterknife.Bind;

/**
 * Description：BaseSwipeRefreshLayoutActivity
 * Created by Ling.He on 2016/5/20.
 */
public abstract class BaseSwipeRefreshLayoutActivity extends BaseToolbarActivity {

    @Bind(R.id.multi_swipe_refresh_layout) protected MultiSwipeRefreshLayout
            mMultiSwipeRefreshLayout;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.initMultiSwipeRefreshLayout();
    }


    /**
     * 初始化MultiSwipeRefreshLayout
     */
    private void initMultiSwipeRefreshLayout() {
        // 下拉刷新的颜色
        if (this.mMultiSwipeRefreshLayout != null) {
            this.mMultiSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        }

        // 在刷新时，关闭刷新开关
        if (this.mMultiSwipeRefreshLayout != null) {
            this.mMultiSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    onSwipeRefresh();
                }
            });
        }
    }


    /**
     * 刷新的时候
     */
    protected abstract void onSwipeRefresh();


    /**
     * 获取当前刷新状态
     *
     * @return boolean
     */
    public boolean isRefreshStatus() {
        return mMultiSwipeRefreshLayout.isRefreshing();
    }


    /**
     * 刷新 true false
     *
     * @param refresh refresh
     */
    public void refresh(final boolean refresh) {
        if (this.mMultiSwipeRefreshLayout == null) return;
        if (!refresh && isRefreshStatus()) {
            // 延迟1s消失
            this.mMultiSwipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    BaseSwipeRefreshLayoutActivity.this.mMultiSwipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        } else if (!this.isRefreshStatus()) {
            /*
             * 到这了，refresh==true，refreshStatus==false
             * 排除了refreshStatus==true的情况
             */
            this.mMultiSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    BaseSwipeRefreshLayoutActivity.this.mMultiSwipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    /**
     * 第一次刷新时，没有调用onSwipeRefresh， 主动调用
     *
     */
    public void autoRefresh(){
        this.mMultiSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                BaseSwipeRefreshLayoutActivity.this.mMultiSwipeRefreshLayout.setRefreshing(true);
                onSwipeRefresh();
            }
        });
    }

}
