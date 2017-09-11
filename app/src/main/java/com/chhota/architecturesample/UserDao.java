package com.chhota.architecturesample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by chhota89 on 11/9/17.
 */

@Dao
public interface UserDao {


    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllSync();

    @Query("SELECT count(*) FROM user")
    Integer countOfUser();

    @Update
    void updateAll(User... Users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User... Users);

    @Delete
    void delete(User User);

    @Query("DELETE FROM user")
    void deleteAllUser();
}
