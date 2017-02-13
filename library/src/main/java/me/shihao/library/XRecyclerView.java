package me.shihao.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.TintTypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by shihao <1406504841@qq.com> on 2017/2/13.12:00
 */

public class XRecyclerView extends SwipeRefreshLayout {
    protected final String TAG = getClass().getSimpleName();

    private NestedScrollView scrollView;
    private RecyclerView recyclerView;
    private TextView textView;
    private MaterialProgressBar progressBar;
    private LinearLayout llLoadMoreView;
    private LinearLayout llHeader, llFooter;

    public XRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public XRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_xrecyclerview, this);
        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (MaterialProgressBar) findViewById(R.id.progressBar);
        llLoadMoreView = (LinearLayout) findViewById(R.id.ll_loadmore);
        llHeader = (LinearLayout) findViewById(R.id.ll_header);
        llFooter = (LinearLayout) findViewById(R.id.ll_footer);
        initRecyclerView();
        initSwipeRefreshLayout();
        loadMore(false);
    }

    public void addHeaderView(View view) {
        if (llHeader != null && view != null)
            llHeader.addView(view);
    }

    public void removeHeaderView(View view) {
        if (llHeader != null && view != null)
            llHeader.removeView(view);
    }

    public void removeHeaderView(int index) {
        if (llHeader != null && index >= 0 && index < llHeader.getChildCount())
            llHeader.removeViewAt(index);
    }

    public void removeAllHeaderView() {
        if (llHeader != null)
            llHeader.removeAllViews();
    }

    public void addFooterView(View view) {
        if (llFooter != null && view != null)
            llFooter.addView(view);
    }

    public void removeFooterView(View view) {
        if (llFooter != null && view != null)
            llFooter.removeView(view);
    }

    public void removeFooterView(int index) {
        if (llFooter != null && index >= 0 && index < llFooter.getChildCount())
            llFooter.removeViewAt(index);
    }

    public void removeAllFooterView() {
        if (llFooter != null)
            llFooter.removeAllViews();
    }

    public void refreshComlete() {
        if (isRefreshing())
            setRefreshing(false);
        else
            loadMore(false);
    }

    private void loadMore(boolean loading) {
        if (loading) {
            llLoadMoreView.setVisibility(VISIBLE);
            progressBar.setVisibility(VISIBLE);
            textView.setVisibility(VISIBLE);
            textView.setText("加载中...");
            isCanLoadMore = true;
            isLoadMore = true;
        } else {
            llLoadMoreView.setVisibility(INVISIBLE);
//            progressBar.setVisibility(GONE);
//            textView.setVisibility(VISIBLE);
//            textView.setText("加载完成");
            isLoadMore = false;
        }
    }

    public void loadMoreNoData() {
        loadMoreNoData("");
    }

    public void loadMoreNoData(String text) {
        llLoadMoreView.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        textView.setText(TextUtils.isEmpty(text) ? "没有更多了~" : text);
        isCanLoadMore = false;
    }


    private void initSwipeRefreshLayout() {
        super.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (refreshListener != null)
                    refreshListener.onRefresh();
            }
        });
        setColorSchemeColors(getColorAccent());
    }

    private int getColorAccent() {
        int accentColor = 0xFF9800;
        int[] attrsArray = {R.attr.colorAccent};
        TypedArray typedArray = getContext().obtainStyledAttributes(attrsArray);
        try {
            accentColor = typedArray.getColor(0, accentColor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        typedArray.recycle();
        return accentColor;
    }


    private int scrollDistance;
    private boolean isLoadMore = false;
    private boolean isCanLoadMore = true;

    private void initRecyclerView() {
        recyclerView.setNestedScrollingEnabled(false);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d(TAG, "scrollY = " + scrollY + ",oldScrollY = " + oldScrollY);
                scrollDistance = scrollY;
                int maxScrollAmount = v.getChildAt(0).getMeasuredHeight() - +v.getHeight();
                Log.d(TAG, "scrollDistance = " + scrollDistance + ",maxScrollAmount = " + maxScrollAmount);
                if (scrollDistance >= maxScrollAmount - 80) {
                    if (!isRefreshing() && !isLoadMore && isCanLoadMore) {
                        Log.d(TAG, "加载更多...");
                        //加载更多
                        if (refreshListener != null) {
                            loadMore(true);
                            refreshListener.onLoadMore();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        super.setRefreshing(refreshing);
        isCanLoadMore = true;
        if (refreshListener != null && refreshing) {
            refreshListener.onRefresh();
        }
    }

    public void autoRefresh() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                setRefreshing(true);
            }
        });
    }

    private OnRefreshListener refreshListener;

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.refreshListener = listener;
    }

    public interface OnRefreshListener {
        void onRefresh();

        void onLoadMore();
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public RecyclerView verticalLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        getRecyclerView().setLayoutManager(manager);
        return getRecyclerView();
    }

    public RecyclerView horizontalLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        getRecyclerView().setLayoutManager(manager);
        return getRecyclerView();
    }

    public RecyclerView gridLayoutManager(int spanCount) {
        GridLayoutManager manager = new GridLayoutManager(getContext(), spanCount);
        getRecyclerView().setLayoutManager(manager);
        return getRecyclerView();
    }

    public RecyclerView verticalStaggeredLayoutManager(int spanCount) {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager
                .VERTICAL);
        getRecyclerView().setLayoutManager(manager);
        return getRecyclerView();
    }

    public RecyclerView horizontalStaggeredLayoutManager(int spanCount) {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager
                .HORIZONTAL);
        getRecyclerView().setLayoutManager(manager);
        return getRecyclerView();
    }

    public RecyclerView setAdapter(RecyclerView.Adapter adapter) {
        getRecyclerView().setAdapter(adapter);
        return getRecyclerView();
    }


    public NestedScrollView getScrollView() {
        return scrollView;
    }
}
