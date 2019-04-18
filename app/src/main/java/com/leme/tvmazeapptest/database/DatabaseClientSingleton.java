package com.leme.tvmazeapptest.database;

import android.content.Context;

public enum DatabaseClientSingleton {
    DATABASE_INSTANCE;

    private DatabaseClient mInstance;

    public DatabaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(context);
        }

        return mInstance;
    }
}
