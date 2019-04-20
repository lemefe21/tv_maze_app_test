package com.leme.tvmazeapptest.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leme.tvmazeapptest.R;
import com.leme.tvmazeapptest.adapter.ShowItemAdapter;
import com.leme.tvmazeapptest.contract.MainContract;
import com.leme.tvmazeapptest.model.entity.Show;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.presenter.MainPresenter;
import com.leme.tvmazeapptest.view.MainFragmentActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_LONG;
import static com.leme.tvmazeapptest.utils.AppValues.DEFAULT_URL_QUERY;

public class ListShowFragment extends Fragment implements MainContract.View,
        ShowItemAdapter.ShowItemAdapterOnClickHandle, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_main_shows)
    RecyclerView mRecyclerViewShows;

    @BindView(R.id.sr_main_list_show)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String queryShowTvCategory = DEFAULT_URL_QUERY;

    private MainPresenter mPresenter;
    private ShowItemAdapter mAdapter;

    public ListShowFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_show, container, false);

        ButterKnife.bind(this, view);

        mPresenter = new MainPresenter(this);

        setShowsLayoutManager();
        getAllFavoritesShowAndRequestData();

        return view;
    }

    private void setShowsLayoutManager() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerViewShows.setLayoutManager(layoutManager);
        mAdapter = new ShowItemAdapter(getContext(),this);
        mRecyclerViewShows.setAdapter(mAdapter);
    }

    @Override
    public void onClick(ShowParcelable showClicked) {
        MainFragmentActivity activity = (MainFragmentActivity) getActivity();
        activity.getShowClicked(showClicked);
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
                mPresenter.loadFavoriteShows(ListShowFragment.this);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mPresenter.requestDataFromServer(queryShowTvCategory);
                hideProgress();
            }
        }

        ShowTask showTask = new ShowTask();
        showTask.execute();
    }

    @Override
    public void onRefresh() {
        getAllFavoritesShowAndRequestData();
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
        Toast.makeText(getContext(), getString(error), LENGTH_LONG).show();
    }
}
