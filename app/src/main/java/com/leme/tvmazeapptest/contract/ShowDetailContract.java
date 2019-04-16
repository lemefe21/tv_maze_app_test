package com.leme.tvmazeapptest.contract;

import android.content.Intent;

import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.view.ShowDetailActivity;

public interface ShowDetailContract {

    interface Service {
        void deleteFavorite(Show show, ShowDetailActivity showDetailActivity);
        void addFavorite(Show show, ShowDetailActivity showDetailActivity);
    }

    interface View {
        void setShowDetailData(Show show);
        void favoriteShowIcon();
        void disfavorShowIcon();
    }

    interface Presenter {
        void onDestroy();
        void getIntentExtras(Intent intent);
        void favoriteShow(ShowDetailActivity showDetailActivity);
    }

}
