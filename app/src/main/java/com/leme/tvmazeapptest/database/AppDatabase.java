package com.leme.tvmazeapptest.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.leme.tvmazeapptest.model.entity.Show;

@Database(entities = {Show.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShowDao showDao();
}

