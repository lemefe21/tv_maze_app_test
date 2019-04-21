package com.leme.tvmazeapptest.view;

import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.leme.tvmazeapptest.R;
import com.leme.tvmazeapptest.adapter.ShowItemAdapter;
import com.leme.tvmazeapptest.contract.MainContract;
import com.leme.tvmazeapptest.model.entity.Show;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.SearchManager.QUERY;
import static android.content.Intent.ACTION_SEARCH;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_LONG;
import static com.leme.tvmazeapptest.utils.AppValues.DEFAULT_URL_QUERY;
import static com.leme.tvmazeapptest.utils.AppValues.EXTRA_RESULT_DETAIL_KEY;
import static com.leme.tvmazeapptest.utils.ShowUtils.calculateBestSpanCount;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        ShowItemAdapter.ShowItemAdapterOnClickHandle, SwipeRefreshLayout.OnRefreshListener {

    private MainPresenter mPresenter;
    private ShowItemAdapter mAdapter;
    private String queryShowTvCategory = DEFAULT_URL_QUERY;

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

        getAllFavoritesShowAndRequestData();
        setShowsLayoutManager();
        handleIntent(getIntent());
    }

    private void setShowsLayoutManager() {
        Display display = getWindowManager().getDefaultDisplay();
        int numberOfColumns = calculateBestSpanCount(display);

        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);
        mRecyclerViewShows.setLayoutManager(layoutManager);
        mAdapter = new ShowItemAdapter(this,this);
        mRecyclerViewShows.setAdapter(mAdapter);
    }

    @Override
    public void showProgress() {
        mRecyclerViewShows.setVisibility(GONE);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mRecyclerViewShows.setVisibility(VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setDataToRecyclerView(List<ShowParcelable> showList, List<Show> favoriteShows) {
        mAdapter.setListData(showList, favoriteShows);
    }

    @Override
    public void setUpdateDataToRecyclerView(List<ShowParcelable> shows) {
        mAdapter.setUpdateListData(shows);
    }

    @Override
    public void showError(int error) {
        Toast.makeText(this, getString(error), LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onClick(ShowParcelable showClicked) {
        mPresenter.goToDetailsActivity(this, showClicked);
    }

    @Override
    public void onRefresh() {
        getAllFavoritesShowAndRequestData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
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
        if (!ACTION_SEARCH.equals(intent.getAction()))
            return;

        queryShowTvCategory = intent.getStringExtra(QUERY);
        mPresenter.requestDataFromServer(queryShowTvCategory);
    }

    private void getAllFavoritesShowAndRequestData() {
        class ShowTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showProgress();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                mPresenter.loadFavoriteShows(MainActivity.this);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mPresenter.requestDataFromServer(queryShowTvCategory);
            }
        }

        ShowTask showTask = new ShowTask();
        showTask.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mPresenter.getRequestCode() && resultCode == RESULT_OK) {
            ShowParcelable show = (ShowParcelable) data.getExtras().get(EXTRA_RESULT_DETAIL_KEY);
            mPresenter.updateShowList(show);
        }
    }
}
