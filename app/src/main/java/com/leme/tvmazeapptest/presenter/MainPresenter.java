package com.leme.tvmazeapptest.presenter;

import android.content.Intent;

import com.leme.tvmazeapptest.contract.MainContract;
import com.leme.tvmazeapptest.handler.ExceptionHandler;
import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.model.UserResponse;
import com.leme.tvmazeapptest.service.MainService;
import com.leme.tvmazeapptest.utils.ShowUtils;
import com.leme.tvmazeapptest.view.MainActivity;
import com.leme.tvmazeapptest.view.ShowDetailActivity;

import java.util.List;

public class MainPresenter implements MainContract.Presenter, MainContract.Service.RequestListener {

    public static final String EXTRA_SHOW_KEY = "extra_show_key";
    public static final int REQUEST_CODE = 10;
    private MainContract.View view;
    private MainContract.Service service;
    private List<Show> favoritedShows;
    private List<UserResponse> responseShowList;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        service = new MainService();
    }

    @Override
    public void success(List<UserResponse> response) {
        responseShowList = response;
        view.setDataToRecyclerView(responseShowList, favoritedShows);
        view.hideProgress();
    }

    @Override
    public void error(Throwable throwable) {
        view.showError(ExceptionHandler.FormatErrorUi(throwable));
        view.hideProgress();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void requestDataFromServer(String query) {
        try {
            service.getShowsByQuery(this, query);
        } catch (Exception e) {
            this.view.showError(ExceptionHandler.FormatErrorUi(e));
        }
    }

    @Override
    public void goToDetailsActivity(MainActivity mainActivity, Show show) {
        Intent intent = new Intent(mainActivity, ShowDetailActivity.class);
        intent.putExtra(EXTRA_SHOW_KEY, show);
        mainActivity.startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void getFavoritedShows(MainActivity mainActivity) {
        favoritedShows = service.getFavoritedShowsDb(mainActivity);
    }

    public void updateShowList(Show show) {
        favoritedShows = ShowUtils.addShowInFavoritedList(show, favoritedShows);
        view.setDataToRecyclerView(responseShowList, favoritedShows);
    }

    public int getRequestCode() {
        return REQUEST_CODE;
    }

}
