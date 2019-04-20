package com.leme.tvmazeapptest.service;

import com.leme.tvmazeapptest.api.ApiInterface;
import com.leme.tvmazeapptest.contract.ShowServiceContract;
import com.leme.tvmazeapptest.model.entity.Show;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.model.response.ApiResponse;
import com.leme.tvmazeapptest.view.MainActivity;
import com.leme.tvmazeapptest.view.ShowDetailActivity;
import com.leme.tvmazeapptest.view.fragment.ListShowFragment;
import com.leme.tvmazeapptest.view.fragment.ShowDetailFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.leme.tvmazeapptest.api.ApiClientSingleton.API_CLIENT_INSTANCE;
import static com.leme.tvmazeapptest.database.DatabaseClientSingleton.DATABASE_INSTANCE;
import static com.leme.tvmazeapptest.utils.MapperUtils.showParcelableToEntity;
import static com.leme.tvmazeapptest.utils.MapperUtils.showResponsesToParcelables;

public class ShowService implements ShowServiceContract {

    @Override
    public void loadShowsByQuery(final RequestListener request, String newQuery) {
        ApiInterface api = API_CLIENT_INSTANCE.getClient().create(ApiInterface.class);

        Call<List<ApiResponse>> callRequest = api.getShowListByQuery(newQuery);
        callRequest.enqueue(new Callback<List<ApiResponse>>() {
            @Override
            public void onResponse(Call<List<ApiResponse>> call, Response<List<ApiResponse>> response) {
                List<ApiResponse> responses = response.body();
                request.success(showResponsesToParcelables(responses));
            }

            @Override
            public void onFailure(Call<List<ApiResponse>> call, Throwable throwable) {
                request.error(throwable);
            }
        });
    }

    @Override
    public List<Show> getFavoriteListShowsDb(ListShowFragment mainActivity) {
        return DATABASE_INSTANCE.getInstance(mainActivity.getContext())
                .getAppDatabase()
                .showDao()
                .getFavoriteShows();
    }

    @Override
    public boolean deleteFavorite(ShowParcelable show, ShowDetailActivity showDetailActivity) {
        long deleteId = DATABASE_INSTANCE.getInstance(showDetailActivity)
                .getAppDatabase()
                .showDao()
                .delete(showParcelableToEntity(show));

        return deleteId < 1;
    }

    @Override
    public boolean addFavorite(ShowParcelable show, ShowDetailActivity showDetailActivity) {
        long insertId = DATABASE_INSTANCE.getInstance(showDetailActivity)
                .getAppDatabase()
                .showDao()
                .insert(showParcelableToEntity(show));

        return insertId == show.getId();
    }

    @Override
    public boolean deleteFavorite(ShowParcelable show, ShowDetailFragment showDetail) {
        long deleteId = DATABASE_INSTANCE.getInstance(showDetail.getContext())
                .getAppDatabase()
                .showDao()
                .delete(showParcelableToEntity(show));

        return deleteId < 1;
    }

    @Override
    public boolean addFavorite(ShowParcelable show, ShowDetailFragment showDetail) {
        long insertId = DATABASE_INSTANCE.getInstance(showDetail.getContext())
                .getAppDatabase()
                .showDao()
                .insert(showParcelableToEntity(show));

        return insertId == show.getId();
    }

}
