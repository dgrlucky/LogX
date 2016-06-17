package com.dgrlucky.demo.bean;

/**
 * @author dgrlucky
 * @date 2016/3/25 15:00
 * @company dgrlucky
 * @desc
 */
public class Child<T> {
    private T parent;
    private String name;

    public Child(String name) {
        this.name = name;
    }

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}