package com.atcb.demomvvmarchitecture.respository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.atcb.demomvvmarchitecture.dao.PlayerDAO;
import com.atcb.demomvvmarchitecture.database.Player;
import com.atcb.demomvvmarchitecture.database.PlayerDatabase;

import java.util.List;

public class PlayerRespository {
    private PlayerDAO playerDAO;

    public PlayerRespository(Application application) {
        PlayerDatabase playerDatabase = PlayerDatabase.getInstance(application.getApplicationContext());
        playerDAO = playerDatabase.playerDAO();
    }

    public void insert(Player player){
        new InsertAsystask(playerDAO).execute(player);

    }
    public void update(Player player){
        new UpdateAsystask(playerDAO).execute(player);
    }
    public void delete(Player player){
        new DeleteAsystask(playerDAO).execute(player);
    }
    public void deleteAll(){
        new DeleteAllAsystask(playerDAO).execute();
    }

    public LiveData<List<Player>> getAllPlayer() {
       return playerDAO.getAllPlayer();
    }

    public static class InsertAsystask extends AsyncTask<Player, Void, Void>{
        PlayerDAO playerDAO;

        public InsertAsystask(PlayerDAO playerDAO) {
            this.playerDAO = playerDAO;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDAO.insert(players[0]);
            return null;
        }
    }

    public static class UpdateAsystask extends AsyncTask<Player, Void, Void>{
        PlayerDAO playerDAO;

        public UpdateAsystask(PlayerDAO playerDAO) {
            this.playerDAO = playerDAO;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDAO.update(players[0]);
            return null;
        }
    }
    public static class DeleteAsystask extends AsyncTask<Player, Void, Void>{
        PlayerDAO playerDAO;

        public DeleteAsystask(PlayerDAO playerDAO) {
            this.playerDAO = playerDAO;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDAO.delete(players[0]);
            return null;
        }
    }
    public static class DeleteAllAsystask extends AsyncTask<Void, Void, Void>{
        PlayerDAO playerDAO;

        public DeleteAllAsystask(PlayerDAO playerDAO) {
            this.playerDAO = playerDAO;
        }

        @Override
        protected Void doInBackground(Void... params) {
            playerDAO.deleteAllPlayer();
            return null;
        }
    }
}
