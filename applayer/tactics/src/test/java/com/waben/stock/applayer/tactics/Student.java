package com.waben.stock.applayer.tactics;

/**
 * @author: zengzhiwei
 * @date: 2018/8/7 20:33
 * @desc：
 */
public class Student {

    private int age;

    private Boolean isUsable;

    public int getAge() {
        return age;
    }

    public Boolean isUsable() {
        return isUsable;
    }

    public void setUsable(Boolean isUsable) {
        this.isUsable = isUsable;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return getAge()+"";
    }
}
