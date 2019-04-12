package com.leme.tvmazeapptest.service;

import com.leme.tvmazeapptest.api.ApiClient;
import com.leme.tvmazeapptest.api.ApiInterface;
import com.leme.tvmazeapptest.contract.MainContract;
import com.leme.tvmazeapptest.model.Show;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainService implements MainContract.Service {

    @Override
    public void getShows(final RequestListener request) {

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Show>> callRequest = api.getShowList();

        callRequest.enqueue(new Callback<List<Show>>() {
            @Override
            public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
                List<Show> showList = response.body();
                request.success(showList);
            }

            @Override
            public void onFailure(Call<List<Show>> call, Throwable throwable) {
                request.error(throwable);
            }
        });



    }

}
