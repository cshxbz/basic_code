package com.hxb.basicframework.entity.plain;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.hxb.basicframework.BR;

public class User extends BaseObservable {

    private String name;
    private int age;


    public void onBtnClick(View v){
        setName("rose");
        setAge(25);
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        //通知属性变更
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
}
