package com.hxb.basicframework.entity.resp;

import java.util.List;

public class DataB {

    private String b;
    private List<String> a;

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public List<String> getA() {
        return a;
    }

    public void setA(List<String> a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "DataB{" +
                "b='" + b + '\'' +
                ", a=" + a +
                '}';
    }
}
