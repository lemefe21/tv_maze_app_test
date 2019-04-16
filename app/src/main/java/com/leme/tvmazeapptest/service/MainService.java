package com.leme.tvmazeapptest.service;

import com.leme.tvmazeapptest.api.ApiClient;
import com.leme.tvmazeapptest.api.ApiInterface;
import com.leme.tvmazeapptest.contract.MainContract;
import com.leme.tvmazeapptest.database.DatabaseClient;
import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.model.UserResponse;
import com.leme.tvmazeapptest.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainService implements MainContract.Service {

    @Override
    public void getShowsByQuery(final RequestListener request, String newQuery) {

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);

        Call<List<UserResponse>> callRequest = api.getShowListByQuery(newQuery);

        callRequest.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                List<UserResponse> userResponse = response.body();
                request.success(userResponse);
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable throwable) {
                request.error(throwable);
            }
        });

    }

    @Override
    public List<Show> getFavoritedShowsDb(MainActivity mainActivity) {
        List<Show> allShows = DatabaseClient.getInstance(mainActivity).getAppDatabase().showDao().getAllShows();
        return allShows;
    }

}
