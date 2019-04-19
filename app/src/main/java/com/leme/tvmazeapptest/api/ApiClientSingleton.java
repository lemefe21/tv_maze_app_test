package com.leme.tvmazeapptest.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.leme.tvmazeapptest.utils.AppValues.BASE_URL;

public enum ApiClientSingleton {
    API_CLIENT_INSTANCE;

    private Retrofit retrofit;

    public Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
