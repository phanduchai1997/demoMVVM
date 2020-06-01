package com.atcb.demomvvmarchitecture.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.atcb.demomvvmarchitecture.database.Player;
import com.atcb.demomvvmarchitecture.respository.PlayerRespository;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    private PlayerRespository playerRespository;
    public PlayerViewModel(@NonNull Application application) {
        super(application);
        playerRespository = new PlayerRespository(application);
    }
    public void insert(Player player){
        playerRespository.insert(player);
    }

    public void update(Player player){
        playerRespository.update(player);
    }
    public void delete(Player player){
        playerRespository.delete(player);
    }
    public void deleteAll(){
        playerRespository.deleteAll();
    }

    public LiveData<List<Player>> getAllPlayers() {
        return playerRespository.getAllPlayer();
    }
}
