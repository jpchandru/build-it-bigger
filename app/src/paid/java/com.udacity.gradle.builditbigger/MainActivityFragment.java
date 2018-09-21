package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.bibandroidlibrary.BibAndroidLibraryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public String result = null;
    private static final String RANDOM_JOKES = "RANDOM_JOKES";

    @BindView(R.id.spinner_jokes)
    ProgressBar progressBar;
    @BindView(R.id.button_view)
    Button button;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                tellJoke();

            }
        });
        progressBar.setVisibility(View.GONE);
        return view;
    }

    /*
   This is from postexecute asynch call.
    */
    public void launchBibAndroidLibraryActivity() {
        Context context = getActivity();
        Intent i = new Intent(context, BibAndroidLibraryActivity.class);
        i.putExtra(RANDOM_JOKES, result);
        context.startActivity(i);
        progressBar.setVisibility(View.GONE);
    }

    public void tellJoke() {
        new EndpointsAsyncTask().execute(this);
    }

}
