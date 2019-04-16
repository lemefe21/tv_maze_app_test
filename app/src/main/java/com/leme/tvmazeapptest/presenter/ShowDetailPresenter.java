package com.leme.tvmazeapptest.presenter;

import android.content.Intent;

import com.leme.tvmazeapptest.contract.ShowDetailContract;
import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.service.ShowDetailService;
import com.leme.tvmazeapptest.view.ShowDetailActivity;

public class ShowDetailPresenter implements ShowDetailContract.Presenter {

    private ShowDetailContract.Service service;
    private ShowDetailContract.View view;
    private Show show;

    public ShowDetailPresenter(ShowDetailContract.View view) {
        this.view = view;
        service = new ShowDetailService();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void getIntentExtras(Intent intent) {
        if(intent.hasExtra(MainPresenter.EXTRA_SHOW_KEY)) {
            show = intent.getExtras().getParcelable(MainPresenter.EXTRA_SHOW_KEY);
            view.setShowDetailData(show);
        }
    }

    @Override
    public boolean favoriteShow(ShowDetailActivity showDetailActivity) {
        if(show.isFavorite()) {
            show.setFavorite(service.deleteFavorite(show, showDetailActivity));
        } else {
            show.setFavorite(service.addFavorite(show, showDetailActivity));
        }
        return show.isFavorite();
    }

}
