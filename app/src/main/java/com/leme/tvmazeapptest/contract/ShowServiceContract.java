package com.leme.tvmazeapptest.contract;

import com.leme.tvmazeapptest.model.entity.Show;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.view.MainActivity;
import com.leme.tvmazeapptest.view.ShowDetailActivity;

import java.util.List;

public interface ShowServiceContract {
    interface RequestListener {
        void success(List<ShowParcelable> showParcelables);
        void error(Throwable throwable);
    }

    void loadShowsByQuery(ShowServiceContract.RequestListener request, String newQuery);

    List<Show> getFavoriteListShowsDb(MainActivity mainActivity);

    boolean deleteFavorite(ShowParcelable show, ShowDetailActivity showDetailActivity);
    boolean addFavorite(ShowParcelable show, ShowDetailActivity showDetailActivity);
}
