package com.leme.tvmazeapptest.contract;

import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.view.MainActivity;

import java.util.List;

public interface MainContract {

    interface Service {

        interface RequestListener {
            void success(List<Show> shows);
            void error(Throwable throwable);
        }

        void getShows(RequestListener request);

    }

    interface View {

        void showProgress();
        void hideProgress();
        void setDataToRecyclerView(List<Show> shows);
        void showError(int error);

    }

    interface Presenter {

        void onDestroy();
        void requestDataFromServer();
        void goToDetailsActivity(MainActivity mainActivity, Show Show);

    }

}
