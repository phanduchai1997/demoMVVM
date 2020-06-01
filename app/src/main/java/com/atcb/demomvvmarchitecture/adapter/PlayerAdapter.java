package com.atcb.demomvvmarchitecture.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.atcb.demomvvmarchitecture.R;
import com.atcb.demomvvmarchitecture.database.Player;
import com.atcb.demomvvmarchitecture.databinding.PlayerLayoutBinding;
import com.atcb.demomvvmarchitecture.listener.OnClickItem;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private List<Player> mPlayers;
    private OnClickItem onClickItem;

    public PlayerAdapter(List<Player> mPlayers, OnClickItem onClickItem) {
        this.mPlayers = mPlayers;
        this.onClickItem = onClickItem;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlayerLayoutBinding playerLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.player_layout,parent,false);
        return new ViewHolder(playerLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player player = mPlayers.get(position);
        holder.playerLayoutBinding.setPlayer(player);
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public void setAdapter(List<Player> players){
        mPlayers.clear();
        mPlayers = players;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private PlayerLayoutBinding playerLayoutBinding;
        public ViewHolder(@NonNull PlayerLayoutBinding itemView) {
            super(itemView.getRoot());
            playerLayoutBinding = itemView;
        }
    }
}
