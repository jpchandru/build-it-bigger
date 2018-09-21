package com.udacity.gradle.builditbigger.backend;

import com.android.bibjavalibrary.BibJavaLibraryActivity;

public class MyBean {

    private String myData;

    public String getData() {

        myData = new BibJavaLibraryActivity().getRandomJokes();
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}