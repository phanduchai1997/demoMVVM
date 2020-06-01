package com.atcb.demomvvmarchitecture;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.atcb.demomvvmarchitecture.databinding.LayoutPlayerContentBinding;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class PlayerContentActivity extends AppCompatActivity implements View.OnClickListener {
    private LayoutPlayerContentBinding contentBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentBinding = DataBindingUtil.setContentView(PlayerContentActivity.this, R.layout.layout_player_content);
        contentBinding.btnBack.setOnClickListener(this);
        contentBinding.btnCancel.setOnClickListener(this);
        contentBinding.btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                super.finish();
            case R.id.btn_cancel:
                super.finish();
                break;
            case R.id.btn_save:
                save();
                break;
            case R.id.img_file:
                break;
        }
    }

    private void save() {

        Intent data = new Intent();
        data.putExtra("name",contentBinding.edtPlayerContentName.getText().toString());
        data.putExtra("club",contentBinding.edtPlayerContentClub.getText().toString());
        int pos = -1;
//        Toast.makeText(PlayerContentActivity.this,""+getIntent().getExtras().get("pos"),Toast.LENGTH_SHORT).show();
        if(getIntent().getExtras()!=null){
            pos = getIntent().getExtras().getInt("pos");
            data.putExtra("pos",pos);
        }
        setResult(RESULT_OK, data);
        finish();
    }
}
