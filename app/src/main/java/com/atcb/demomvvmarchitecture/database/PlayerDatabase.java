package com.atcb.demomvvmarchitecture.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.atcb.demomvvmarchitecture.dao.PlayerDAO;

@Database(entities = { Player.class }, version = 1)
public abstract class PlayerDatabase extends RoomDatabase {
    private static PlayerDatabase instance;
    public abstract PlayerDAO playerDAO();
    public static final PlayerDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, PlayerDatabase.class, "player_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
