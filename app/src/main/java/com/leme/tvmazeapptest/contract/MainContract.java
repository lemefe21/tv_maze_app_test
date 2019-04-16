package com.leme.tvmazeapptest.contract;

import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.model.UserResponse;
import com.leme.tvmazeapptest.view.MainActivity;

import java.util.List;

public interface MainContract {

    interface Service {
        interface RequestListener {
            void success(List<UserResponse> response);
            void error(Throwable throwable);
        }

        void getShowsByQuery(RequestListener request, String newQuery);

        List<Show> getFavoritedShowsDb(MainActivity mainActivity);
    }

    interface View {
        void showProgress();
        void hideProgress();
        void setDataToRecyclerView(List<UserResponse> response, List<Show> favoritedShows);
        void showError(int error);
    }

    interface Presenter {
        void onDestroy();
        void requestDataFromServer(String query);
        void goToDetailsActivity(MainActivity mainActivity, Show Show);
        void getFavoritedShows(MainActivity mainActivity);
    }

}
