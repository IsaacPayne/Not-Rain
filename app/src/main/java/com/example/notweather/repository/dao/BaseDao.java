package com.example.notweather.repository.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;
import java.util.ArrayList;
import java.util.List;

/** Upserting: taken from https://tech.bakkenbaeck.com/post/room-insert-update */
@Dao
abstract class BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insert(T obj);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract List<Long> insert(List<T> objList);

    @Update
    public abstract void update(T obj);

    @Update
    public abstract void update(List<T> objList);

    @Transaction
    public void insertOrUpdate(T obj) {
        long id = insert(obj);
        if (id == -1L) {
            update(obj);
        }
    }

    @Transaction
    public void insertOrUpdate(List<T> objList) {
        List<Long> insertResult = insert(objList);
        List<T> updateList = new ArrayList<>();

        for (int i = 0; i < insertResult.size(); i++) {
            if (insertResult.get(i) == -1L) {
                updateList.add(objList.get(i));
            }
        }

        if (!updateList.isEmpty()) {
            update(updateList);
        }
    }
}
