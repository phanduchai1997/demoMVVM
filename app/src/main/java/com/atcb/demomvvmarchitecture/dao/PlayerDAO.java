package com.atcb.demomvvmarchitecture.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.atcb.demomvvmarchitecture.database.Player;

import java.util.List;

@Dao
public interface PlayerDAO {
    @Insert
    void insert(Player player);

    @Update
    void update(Player player);

    @Delete
    void delete(Player player);

    @Query("Delete from player_table")
    void deleteAllPlayer();

    @Query("Select * from player_table")
    LiveData<List<Player>> getAllPlayer();
//    List<Player> getAllPlayer();
}
