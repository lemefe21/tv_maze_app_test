package com.leme.tvmazeapptest.service;

import com.leme.tvmazeapptest.contract.ShowDetailContract;
import com.leme.tvmazeapptest.database.DatabaseClient;
import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.view.ShowDetailActivity;

public class ShowDetailService implements ShowDetailContract.Service {

    @Override
    public boolean deleteFavorite(Show show, ShowDetailActivity showDetailActivity) {
        long deleteId = DatabaseClient.getInstance(showDetailActivity).getAppDatabase().showDao().delete(show);
        return !(deleteId > 1);

    }

    @Override
    public boolean addFavorite(Show show, ShowDetailActivity showDetailActivity) {
        long insertId = DatabaseClient.getInstance(showDetailActivity).getAppDatabase().showDao().insert(show);
        return insertId == show.getId();
    }

}
