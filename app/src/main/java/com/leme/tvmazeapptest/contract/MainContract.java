package com.leme.tvmazeapptest.contract;

import com.leme.tvmazeapptest.model.entity.Show;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.view.MainActivity;
import com.leme.tvmazeapptest.view.fragment.ListShowFragment;

import java.util.List;

public interface MainContract {

    interface View {
        void showProgress();
        void hideProgress();
        void setDataToRecyclerView(List<ShowParcelable> shows, List<Show> favoriteShows);
        void setUpdateDataToRecyclerView(List<ShowParcelable> shows);
        void showError(int error);
    }

    interface Presenter {
        void onDestroy();
        void requestDataFromServer(String query);
        void goToDetailsActivity(MainActivity mainActivity, ShowParcelable show);
        void loadFavoriteShows(MainActivity mainActivity);
        void loadFavoriteShows(ListShowFragment mainActivity);
    }
}
