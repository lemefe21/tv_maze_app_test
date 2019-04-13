package com.leme.tvmazeapptest.presenter;

import com.leme.tvmazeapptest.contract.MainContract;
import com.leme.tvmazeapptest.handler.ExceptionHandler;
import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.model.UserResponse;
import com.leme.tvmazeapptest.service.MainService;
import com.leme.tvmazeapptest.view.MainActivity;

import java.util.List;

public class MainPresenter implements MainContract.Presenter, MainContract.Service.RequestListener {

    public static final String SHOW_EXTRA = "main_list_show_extra";
    private MainContract.View view;
    private MainContract.Service service;

    public MainPresenter(MainContract.View view) {

        this.view = view;
        service = new MainService();

    }

    @Override
    public void success(List<UserResponse> response) {

        view.setDataToRecyclerView(response);
        view.hideProgress();

    }

    @Override
    public void error(Throwable throwable) {

        view.showError(ExceptionHandler.FormatErrorUi(throwable));
        view.hideProgress();
        view.hideProgress();

    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void requestDataFromServer() {

        view.showProgress();
        try {
            service.getShows(this);
        } catch (Exception e) {
            this.view.showError(ExceptionHandler.FormatErrorUi(e));
        }

    }

    @Override
    public void goToDetailsActivity(MainActivity mainActivity, Show Show) {

    }
}
