package com.leme.tvmazeapptest.service;

import com.leme.tvmazeapptest.contract.ShowDetailContract;
import com.leme.tvmazeapptest.database.DatabaseClient;
import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.view.ShowDetailActivity;

public class ShowDetailService implements ShowDetailContract.Service {

    @Override
    public void deleteFavorite(Show show, ShowDetailActivity showDetailActivity) {
        DatabaseClient.getInstance(showDetailActivity).getAppDatabase().showDao().delete(show);
    }

    @Override
    public void addFavorite(Show show, ShowDetailActivity showDetailActivity) {
        DatabaseClient.getInstance(showDetailActivity).getAppDatabase().showDao().insert(show);
    }

}
