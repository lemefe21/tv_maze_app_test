package com.leme.tvmazeapptest.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class MainActivity extends AppCompatActivity implements MainContract.View, ShowItemAdapter.ShowItemAdapterOnClickHandle {

    private MainPresenter mPresenter;
    private ShowItemAdapter mAdapter;

    @BindView(R.id.iv_main_image_no_internet)
    ImageView mImageViewNoInternet;

    @BindView(R.id.pb_main_loading_indicator)
    ProgressBar mLoading;

    @BindView(R.id.rv_main_shows)
    RecyclerView mRecyclerViewShows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {

        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);
        mPresenter.requestDataFromServer();

        setShowsLayoutManager();

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
        mLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mRecyclerViewShows.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.GONE);
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
        Toast.makeText(this, "Show id: " + showClicked.getId(), Toast.LENGTH_LONG).show();
    }
}
