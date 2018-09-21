package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.bibjavalibrary.BibJavaLibraryActivity;


public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, new BibJavaLibraryActivity().getTestJoke());
    }

//    public void tellJoke(View view) {
//        Toast.makeText(this, "Free Version", Toast.LENGTH_SHORT).show();
//        new EndpointsAsyncTask().execute(this);
//    }
}
