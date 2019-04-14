package com.leme.tvmazeapptest.contract;

import android.content.Intent;

import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.view.ShowDetailActivity;

public interface ShowDetailContract {

    interface Service {
        boolean updateFavoritedShowStatus(long id);
    }

    interface View {
        void setShowDetailData(Show show);
        void updateFavoriteIconState(int resourceIcon);
    }

    interface Presenter {
        void onDestroy();
        void getIntentExtras(Intent intent);
        void favoriteShow(ShowDetailActivity showDetailActivity);
    }

}
