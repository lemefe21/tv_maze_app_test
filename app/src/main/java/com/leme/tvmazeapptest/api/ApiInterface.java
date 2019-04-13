package com.leme.tvmazeapptest.api;

import com.leme.tvmazeapptest.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/search/shows")
    Call<List<UserResponse>> getShowListByQuery(@Query("q") String newQuery);

}
