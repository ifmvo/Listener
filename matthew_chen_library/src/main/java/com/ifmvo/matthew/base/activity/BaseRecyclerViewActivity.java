package com.ifmvo.matthew.base.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ifmvo.matthew.R;

import space.sye.z.library.RefreshRecyclerView;
import space.sye.z.library.adapter.RefreshRecyclerViewAdapter;
import space.sye.z.library.listener.OnBothRefreshListener;
import space.sye.z.library.listener.OnLoadMoreListener;
import space.sye.z.library.listener.OnPullDownListener;
import space.sye.z.library.manager.RecyclerMode;
import space.sye.z.library.manager.RefreshRecyclerAdapterManager;


/**
 * 封装了 RecyclerView 的 Activity
 *
 * 适用于一个界面只有一个列表的 Activity 继承
 *
 * Created by Matthew_Chen on 16/10/12.
 */

public abstract class BaseRecyclerViewActivity extends ExBaseTopBarActivity {

    protected RefreshRecyclerView recyclerView;
    RefreshRecyclerAdapterManager refreshRecyclerAdapterManager;

    protected View listTopLay, listBottomLay;
    protected RelativeLayout layParent;
    View errorLay;
    Button btnErrorRefresh;

    boolean canLoadMore = true;
    int mIndexPage = 1;
    boolean isLoading = false;
    int pageSize = 20;

    @Override
    public void onCreatedContentView() {
        setMainContentView(R.layout.lay_base_recylerview);
    }

    @Override
    public void onFindView() {
        btnErrorRefresh = (Button) findViewById(R.id.btnErrorRefresh);
        listTopLay = findViewById(R.id.listTopLay);
        listBottomLay = findViewById(R.id.listBottomLay);
        layParent = (RelativeLayout) findViewById(R.id.layParent);
        errorLay = findViewById(R.id.errorLay);
        recyclerView = (RefreshRecyclerView) findViewById(R.id.pull_refresh_list);

        refreshRecyclerAdapterManager = new RefreshRecyclerAdapterManager(getAdapter(), getLayoutManager());
        refreshRecyclerAdapterManager.setMode(getRecyclerMode())
                .addHeaderView(getHeadView())
                .addHeaderView(getSecondHeadView())
                .addHeaderView(getThirdHeadView())
                .addFooterView(getFootView())
                .addFooterView(getSecondFootView())
                .addFooterView(getThirdFootView())
                .setOnBothRefreshListener(new OnBothRefreshListener() {
                    @Override
                    public void onPullDown() {
                        refresh();
                    }

                    @Override
                    public void onLoadMore() {
                        loadMore();
                    }
                })
                .setOnPullDownListener(new OnPullDownListener() {
                    @Override
                    public void onPullDown() {
                        refresh();
                    }
                })
                .setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        loadMore();
                    }
                })
                .setOnItemClickListener(new RefreshRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                        onListItemClick(holder, position);
                    }
                })
                .into(recyclerView, getContext());

        btnErrorRefresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onErrorRefreshBtnClick()) {
                    onErrorClick();
                }
            }
        });
    }

    @Override
    public void onPostLoad() {
        initedView();
        getData(mIndexPage, pageSize);
    }

    protected boolean onErrorRefreshBtnClick() {
        return false;
    }

    private void onErrorClick() {
        errorLay.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        refresh();
    }

    /**
     * 加载到底的时候调用
     */
    protected void setLoadEnd() {
        setCanLoadMore(false);
        refreshRecyclerAdapterManager.onLoadEnd();
    }

    /**
     * 加载失败的时候调用
     *
     * @param errorStr
     */
//    protected void setLoadError(final String errorStr) {
//        errorLay.setVisibility(View.VISIBLE);
//        btnErrorRefresh.setText(errorStr);
////        listView.setVisibility(View.GONE);
//    }

    protected void setLoadError(int drawableRes, String errorStr, View.OnClickListener onClickListener) {
        isLoading = false;
        btnErrorRefresh.setCompoundDrawablesWithIntrinsicBounds(0, drawableRes, 0, 0);
        if (onClickListener != null) {
            btnErrorRefresh.setOnClickListener(onClickListener);
        } else {
            btnErrorRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onErrorRefreshBtnClick()) {
                        onErrorClick();
                    }
                }
            });
        }
        setLoadError(errorStr);
    }

    public void setCanRefresh(boolean canRefresh) {
        recyclerView.setMode(canRefresh ? RecyclerMode.BOTH : RecyclerMode.NONE);
    }

    public void setCanLoadMore(boolean canLoadMore) {
        recyclerView.setMode(canLoadMore ? RecyclerMode.BOTH : RecyclerMode.TOP);
        this.canLoadMore = canLoadMore;
        refreshRecyclerAdapterManager.onRefreshCompleted();
    }


    protected void refresh() {
        setCanLoadMore(true);
        mIndexPage = 1;
        isLoading = true;
        getData(mIndexPage, pageSize);
    }

    protected void loadMore() {
        if (canLoadMore) {
            mIndexPage++;
            isLoading = true;
            getData(mIndexPage, pageSize);
        }
    }

    /**
     * 需要设置的时候就重写
     *
     * @return
     */
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    protected RecyclerMode getRecyclerMode() {
        return RecyclerMode.BOTH;
    }

    protected View getHeadView() {
        return null;
    }

    protected View getSecondHeadView() {
        return null;
    }

    protected View getThirdHeadView() {
        return null;
    }

    protected View getFootView() {
        return null;
    }

    protected View getSecondFootView() {
        return null;
    }

    protected View getThirdFootView() {
        return null;
    }

    public abstract void initedView();

    public abstract void getData(int indexPage, int pageSize);

    public abstract RecyclerView.Adapter getAdapter();

    public abstract void onListItemClick(RecyclerView.ViewHolder holder, int position);
}
