package com.atcb.demomvvmarchitecture;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.atcb.demomvvmarchitecture.adapter.PlayerAdapter;
import com.atcb.demomvvmarchitecture.database.Player;
import com.atcb.demomvvmarchitecture.databinding.ActivityMainBinding;
import com.atcb.demomvvmarchitecture.listener.OnClickItem;
import com.atcb.demomvvmarchitecture.listener.SwipeHelper;
import com.atcb.demomvvmarchitecture.view_model.PlayerViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickItem {
    private PlayerViewModel playerViewModel;
    private PlayerAdapter adapter;
    private ActivityMainBinding mainBinding;
    private final int ACTION_UPDATE = 101;
    private final int ACTION_INSERT = 100;
    private List<Player> mPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        playerViewModel = ViewModelProviders.of(MainActivity.this).get(PlayerViewModel.class);
        mPlayers = new ArrayList<>();
        mainBinding.rvMain.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlayerAdapter(mPlayers, this);
        mainBinding.rvMain.setAdapter(adapter);
        registerLivaDataListener();
        mainBinding.btnMainAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayerContentActivity.class);
                startActivityForResult(intent, ACTION_INSERT);
            }
        });
        mainBinding.btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerViewModel.deleteAll();
                Toast.makeText(MainActivity.this, "Đã xóa sạch sẽ !", Toast.LENGTH_SHORT).show();
            }
        });

        swipItem();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == ACTION_INSERT) {
                String name = data.getExtras().getString("name");
                String club = data.getExtras().getString("club");
                Player player = new Player(name, club);
                playerViewModel.insert(player);
                Toast.makeText(MainActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
            } else if (requestCode == ACTION_UPDATE) {
                String name = data.getExtras().getString("name");
                String club = data.getExtras().getString("club");
                int pos = data.getExtras().getInt("pos");
                Player player = mPlayers.get(pos);
                player.setName(name);
                player.setClub(club);
                playerViewModel.update(player);
                Toast.makeText(MainActivity.this,"Upate thành công",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void registerLivaDataListener() {
        playerViewModel.getAllPlayers().observe(MainActivity.this, new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                if (players != null) {
                    adapter.setAdapter(players);
                    mPlayers = players;
                }
            }
        });
    }

    private void swipItem() {
        SwipeHelper swipeHelper = new SwipeHelper(this, mainBinding.rvMain) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Delete",
                        0,
                        Color.parseColor("#FF3C30"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Player player = mPlayers.get(pos);
                                playerViewModel.delete(player);
                                Toast.makeText(MainActivity.this,"Đã xóa "+pos,Toast.LENGTH_SHORT).show();
                            }
                        }
                ));

                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Update",
                        0,
                        Color.parseColor("#FF9502"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Intent intent = new Intent(MainActivity.this, PlayerContentActivity.class);
                                intent.putExtra("pos",pos);
                                startActivityForResult(intent, ACTION_UPDATE);
                            }
                        }
                ));
            }
        };
    }

    @Override
    public void onClick(int posision) {

    }
}
