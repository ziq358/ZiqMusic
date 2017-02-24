package com.xogrp.john.ziqtoollibrary.model;

import com.google.gson.annotations.Expose;

/**
 * Created by john on 08/02/2017.
 */

public class JohTtest {

    /**
     * name : 怪盗kidou
     * age : 24
     * emailAddress : ikidou@example.com
     */
    @Expose
    private String name;
    @Expose
    private int age;
    @Expose
    private String emailAddress;

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
