package com.module.sayem.foodculture.storage.roomDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user_en")
    List<User_En> getAllUsers();

    @Query("SELECT * FROM user_en WHERE uid IN (:userIds)")
    List<User_En> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user_en WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    User_En findByName(String first, String last);

    @Insert
    void insertAll(User_En users);

    @Delete
    void delete(User_En user);

    @Query("DELETE FROM user_en")
    void deleteAll();
}
