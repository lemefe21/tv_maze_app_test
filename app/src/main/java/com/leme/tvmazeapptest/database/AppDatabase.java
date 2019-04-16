package com.leme.tvmazeapptest.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.leme.tvmazeapptest.model.Show;

@Database(entities = {Show.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShowDao showDao();
}

