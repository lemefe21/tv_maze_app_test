package com.leme.tvmazeapptest.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.leme.tvmazeapptest.model.entity.Show;

import java.util.List;

@Dao
public interface ShowDao {

    @Query("SELECT * FROM Show WHERE id IN (:ids)")
    List<Show> getFavoriteShows(List<Long> ids);

    @Insert
    long insert(Show show);

    @Delete
    int delete(Show show);

}
