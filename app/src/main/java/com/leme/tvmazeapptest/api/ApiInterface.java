package com.leme.tvmazeapptest.api;

import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/search/shows?q=cartoon")
    Call<List<UserResponse>> getShowList();

}
