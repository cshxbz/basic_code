package com.hxb.basicframework.livedata;

import androidx.lifecycle.LiveData;

public class TestLiveData<T> extends LiveData<T> {

    @Override
    protected void postValue(T value) {
        super.postValue(value);
    }

    @Override
    protected void setValue(T value) {
        super.setValue(value);
    }
}
