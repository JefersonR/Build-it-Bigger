package com.udacity.gradle.builditbigger.backend;

import com.udacity.gradle.jokes.Joker;

/** The object model for the data we are sending through endpoints */
public class MyBean {
    private Joker myData;

     MyBean() {
        myData = new Joker();
    }
    public String getData() {
        return myData.getJoke();
    }
}