package com.leme.tvmazeapptest.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.leme.tvmazeapptest.R;
import com.leme.tvmazeapptest.adapter.ShowItemAdapter;
import com.leme.tvmazeapptest.contract.MainContract;
import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.model.UserResponse;
import com.leme.tvmazeapptest.presenter.MainPresenter;
import com.leme.tvmazeapptest.utils.ShowUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View, ShowItemAdapter.ShowItemAdapterOnClickHandle, SwipeRefreshLayout.OnRefreshListener {

    private MainPresenter mPresenter;
    private ShowItemAdapter mAdapter;
    private String queryShowTvCategory = "cartoon";

    @BindView(R.id.iv_main_image_no_internet)
    ImageView mImageViewNoInternet;

    @BindView(R.id.rv_main_shows)
    RecyclerView mRecyclerViewShows;

    @BindView(R.id.sr_main_list_show)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

    }

    private void initUi() {

        ButterKnife.bind(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                R.color.colorAccentDark,
                R.color.colorPrimary,
                R.color.colorPrimaryDark);

        mPresenter = new MainPresenter(this);
        mPresenter.requestDataFromServer(queryShowTvCategory);

        setShowsLayoutManager();

        handleIntent(getIntent());

    }

    private void setShowsLayoutManager() {

        Display display = getWindowManager().getDefaultDisplay();
        int numberOfColumns = ShowUtils.calculateBestSpanCount(display);

        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);
        mRecyclerViewShows.setLayoutManager(layoutManager);
        mAdapter = new ShowItemAdapter(this,this);
        mRecyclerViewShows.setAdapter(mAdapter);

    }

    @Override
    public void showProgress() {
        mRecyclerViewShows.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mRecyclerViewShows.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setDataToRecyclerView(List<UserResponse> response) {
        mAdapter.setListData(response);
    }

    @Override
    public void showError(int error) {
        Toast.makeText(this, getString(error), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onClick(Show showClicked) {

        mPresenter.goToDetailsActivity(this, showClicked);

    }

    @Override
    public void onRefresh() {
        mPresenter.requestDataFromServer(queryShowTvCategory);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            queryShowTvCategory = intent.getStringExtra(SearchManager.QUERY);
            mPresenter.requestDataFromServer(queryShowTvCategory);
        }
    }
}
