package com.gz.camming.mvp.bean;

/**
 * Created by camming on 2019/3/22.
 * code is data ,data is code
 */

public class AibBean {
    private String name;
    private int age;
    private String address;
    private String aib;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAib() {
        return aib;
    }

    public void setAib(String aib) {
        this.aib = aib;
    }

    @Override
    public String toString() {
        return "AibBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", aib='" + aib + '\'' +
                '}';
    }
}
