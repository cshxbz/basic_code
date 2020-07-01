package com.hxb.basicframework.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hxb.basic_framework.baselib.utils.Logger;
import com.hxb.basicframework.databinding.ActivityViewModelTestBinding;
import com.hxb.basicframework.viewmodel.TestViewModel;

/**
 *
 */
public class ViewModelTestActivity extends AppCompatActivity {

    private ActivityViewModelTestBinding binding;
    private TestViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewModelTestBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                .get(TestViewModel.class);

        viewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Logger.i(">>>: "+s);
            }
        });


        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getName().setValue("erika");
            }
        });

    }

}
