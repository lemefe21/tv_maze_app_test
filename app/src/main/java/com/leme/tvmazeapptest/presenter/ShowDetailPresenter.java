package com.leme.tvmazeapptest.presenter;

import android.content.Intent;

import com.leme.tvmazeapptest.contract.ShowDetailContract;
import com.leme.tvmazeapptest.contract.ShowServiceContract;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.service.ShowService;
import com.leme.tvmazeapptest.view.ShowDetailActivity;

import static com.leme.tvmazeapptest.utils.AppValues.EXTRA_SHOW_KEY;

public class ShowDetailPresenter implements ShowDetailContract.Presenter {

    private ShowServiceContract service;
    private ShowDetailContract.View view;
    private ShowParcelable show;

    public ShowDetailPresenter(ShowDetailContract.View view) {
        this.view = view;
        service = new ShowService();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void getIntentExtras(Intent intent) {
        if(intent.hasExtra(EXTRA_SHOW_KEY)) {
            show = intent.getExtras().getParcelable(EXTRA_SHOW_KEY);
            view.setShowDetailData(show);
        }
    }

    @Override
    public boolean favoriteShow(ShowDetailActivity showDetailActivity) {
        boolean favorite = show.isFavorite() ?
                service.deleteFavorite(show, showDetailActivity) :
                service.addFavorite(show, showDetailActivity);

        show.setFavorite(favorite);
        return favorite;
    }

    public ShowParcelable getShowUpdate() {
        return show;
    }

}
