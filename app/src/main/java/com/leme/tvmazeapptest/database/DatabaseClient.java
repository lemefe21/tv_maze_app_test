package com.leme.tvmazeapptest.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import static com.leme.tvmazeapptest.utils.AppValues.DATABASE_NAME;

public class DatabaseClient {

    private AppDatabase appDatabase;

    protected DatabaseClient(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
