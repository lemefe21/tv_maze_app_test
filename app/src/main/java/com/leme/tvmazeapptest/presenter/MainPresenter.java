package com.leme.tvmazeapptest.presenter;

import android.content.Intent;

import com.leme.tvmazeapptest.contract.MainContract;
import com.leme.tvmazeapptest.contract.ShowServiceContract;
import com.leme.tvmazeapptest.model.entity.Show;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.service.ShowService;
import com.leme.tvmazeapptest.utils.ShowUtils;
import com.leme.tvmazeapptest.view.MainActivity;
import com.leme.tvmazeapptest.view.ShowDetailActivity;
import com.leme.tvmazeapptest.view.fragment.ListShowFragment;

import java.util.ArrayList;
import java.util.List;

import static com.leme.tvmazeapptest.handler.ExceptionHandler.FormatErrorUi;
import static com.leme.tvmazeapptest.utils.AppValues.EXTRA_SHOW_KEY;
import static com.leme.tvmazeapptest.utils.AppValues.REQUEST_CODE;
import static com.leme.tvmazeapptest.utils.ShowUtils.updateListWithFavoriteShow;

public class MainPresenter implements MainContract.Presenter, ShowServiceContract.RequestListener {

    private MainContract.View view;
    private ShowServiceContract service;
    private List<Show> favoriteListShows;
    private List<ShowParcelable> showParcelables;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        service = new ShowService();
    }

    @Override
    public void success(List<ShowParcelable> showList) {
        showParcelables = showList;
        view.setDataToRecyclerView(showParcelables, favoriteListShows);
    }

    @Override
    public void error(Throwable throwable) {
        view.showError(FormatErrorUi(throwable));
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void requestDataFromServer(String query) {
        try {
            service.loadShowsByQuery(this, query);
        } catch (Exception e) {
            this.view.showError(FormatErrorUi(e));
        }
    }

    @Override
    public void goToDetailsActivity(MainActivity mainActivity, ShowParcelable show) {
        Intent intent = new Intent(mainActivity, ShowDetailActivity.class);
        intent.putExtra(EXTRA_SHOW_KEY, show);
        mainActivity.startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void loadFavoriteShows(MainActivity mainActivity) {
        //favoriteListShows = service.getFavoriteListShowsDb(mainActivity);
    }

    @Override
    public void loadFavoriteShows(ListShowFragment mainActivity) {
        favoriteListShows = service.getFavoriteListShowsDb(mainActivity);
    }

    public void updateShowList(ShowParcelable showParcelable) {
        showParcelables = updateListWithFavoriteShow(showParcelable, new ArrayList<>(showParcelables));
        view.setUpdateDataToRecyclerView(showParcelables);
    }

    public int getRequestCode() {
        return REQUEST_CODE;
    }

}
