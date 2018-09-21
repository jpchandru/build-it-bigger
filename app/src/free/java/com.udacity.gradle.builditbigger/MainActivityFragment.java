package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.bibandroidlibrary.BibAndroidLibraryActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private PublisherInterstitialAd interstitialAd = null;
    public String result = null;
    private static final String RANDOM_JOKES = "RANDOM_JOKES";
    @BindView(R.id.button_view)
    Button button;
    @BindView(R.id.spinner_joke)
    ProgressBar progressBar;
    @BindView(R.id.adView)
    AdView mAdView;

    public MainActivityFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        interstitialAd = new PublisherInterstitialAd(Objects.requireNonNull(getContext()));
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                progressBar.setVisibility(View.VISIBLE);
                tellJoke();
                //fetch the next ad in prior
                requestNewInterstitial();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                Log.i(LOG_TAG, "Failing");
                //prefetch the next ad
                requestNewInterstitial();
            }

            @Override
            public void onAdLoaded() {
                Log.i(LOG_TAG, "Loading");
                super.onAdLoaded();
            }
        });

        requestNewInterstitial();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    tellJoke();
                }
            }
        });
        progressBar.setVisibility(View.GONE);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
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

    private void requestNewInterstitial() {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest);
    }

    public void tellJoke() {
        new EndpointsAsyncTask().execute(this);
    }
}
