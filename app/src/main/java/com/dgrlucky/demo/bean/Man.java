package com.dgrlucky.demo.bean;

import com.dgrlucky.demo.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author dgrlucky
 * @date 2016/3/25 15:00
 * @company dgrlucky
 * @desc
 */
public class Man extends Person {

    private static final int SEX = 1;
    private int a = 0;
    private List<String> list = new ArrayList<>();
    private Map<String, Person> map = Data.getObjectMap();

    public Man(int a) {
        super();
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
