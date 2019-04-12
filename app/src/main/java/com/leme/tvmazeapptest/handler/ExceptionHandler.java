package com.leme.tvmazeapptest.handler;

import com.leme.tvmazeapptest.R;

import java.io.IOException;

import retrofit2.HttpException;

public class ExceptionHandler {

    public static int FormatErrorUi(Throwable throwable) {
        if(throwable instanceof HttpException) {
            return R.string.error_server;
        } else if(throwable instanceof IOException) {
            return R.string.error_network;
        } else {
            return R.string.error_unknown;
        }

    }

}
