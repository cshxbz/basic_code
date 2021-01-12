package com.hxb.basicframework.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hxb.basicframework.databinding.ActivityDatabindingTestBinding;
import com.hxb.basicframework.entity.plain.User;

/**
 *
 */
public class DataBindingTestActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ActivityDatabindingTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_test);
        ActivityDatabindingTestBinding binding = ActivityDatabindingTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        User user = new User("erika", 18);
        binding.setUser(user);


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                user.setName("linus");
                user.setAge(50);
            }
        }, 3000);

    }
}
