package com.leme.tvmazeapptest.contract;

import android.content.Intent;

import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.view.ShowDetailActivity;
import com.leme.tvmazeapptest.view.fragment.ShowDetailFragment;

public interface ShowDetailContract {

    interface View {
        void setShowDetailData(ShowParcelable show);
        void favoriteShowIcon();
        void disfavorShowIcon();
    }

    interface Presenter {
        void onDestroy();
        void getIntentExtras(Intent intent);
        boolean favoriteShow(ShowDetailActivity showDetailActivity);
        boolean favoriteShowFragment(ShowDetailFragment showDetail);
        void getFragmentBundle(ShowParcelable showParcelable);
    }
}
