package com.hxb.basicframework.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hxb.basicframework.databinding.ActivityStyleTestBinding;

public class StyleTestActivity extends AppCompatActivity {

    private ActivityStyleTestBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStyleTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(v -> {
            //
            new AlertDialog.Builder(this)
                    .setTitle("title")
                    .setMessage("this is message")
                    .setNegativeButton("cancel", null)
                    .setPositiveButton("confirm", null)
                    .show();
        });
    }


}
