package com.leme.tvmazeapptest.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {

    private Context mContext;
    private static DatabaseClient mInstance;
    private AppDatabase appDatabase;
    private final static String DATABASE_NAME = "show";

    private DatabaseClient(Context context) {
        this.mContext = context;
        appDatabase = Room.databaseBuilder(mContext, AppDatabase.class, DATABASE_NAME).build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

}
