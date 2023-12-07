package com.example.harwaqt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.hamzaahmedkhan.counteranimationtextview.CountAnimationTextView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.shuhart.stepview.StepView;

public class Application_Progress_Fragment extends Fragment {
    CountAnimationTextView counter,approved,rejected,pending;
    StepView stepView;
    Button re_apply,summary;
    InterstitialAd mInterstitialAd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_progress, container, false);
        approved = view.findViewById(R.id.loanapproved);
        counter = view.findViewById(R.id.moneyPositive);
        rejected=view.findViewById(R.id.rejected);
        pending=view.findViewById(R.id.pending);
        stepView = view.findViewById(R.id.step_view);
        re_apply=view.findViewById(R.id.re_apply);
        summary=view.findViewById(R.id.summary);

        re_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdClicked() {
                            // Called when a click is recorded for an ad.
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when ad is dismissed.
                            // Set the ad reference to null so you don't show the ad a second time.
                            Intent i = new Intent(getActivity(), ApplyNowActivity.class);
                            startActivity(i);
                            loadint();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            // Called when ad fails to show.
                            mInterstitialAd = null;
                        }

                        @Override
                        public void onAdImpression() {
                            // Called when an impression is recorded for an ad.
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            // Called when ad is shown.
                        }
                    });
                } else {
                    Intent i = new Intent(getActivity(), ApplyNowActivity.class);
                    startActivity(i);
                }


            }
        });
        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(getActivity());

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdClicked() {
                            // Called when a click is recorded for an ad.
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when ad is dismissed.
                            // Set the ad reference to null so you don't show the ad a second time.
                            Intent i = new Intent(getActivity(), OrderInfoActivity.class);
                            startActivity(i);
                            loadint();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            // Called when ad fails to show.
                            mInterstitialAd = null;
                        }

                        @Override
                        public void onAdImpression() {
                            // Called when an impression is recorded for an ad.
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            // Called when ad is shown.
                        }
                    });
                } else {
                    Intent i = new Intent(getActivity(), OrderInfoActivity.class);
                    startActivity(i);
                }
            }
        });
        counter.setAnimationDuration(5000).countAnimation(0, 49598);
        approved.setAnimationDuration(5000).countAnimation(0, 40451);
        rejected.setAnimationDuration(5000).countAnimation(0, 7499);
        pending.setAnimationDuration(5000).countAnimation(0, 1648);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("StepPrefs", Context.MODE_PRIVATE);
        int currentStep = sharedPreferences.getInt("currentStep", 1);

// Update the StepView based on the current step
        stepView.go(currentStep - 1, true);
        return view;
    }
    public  void loadint(){
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {}
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(getActivity(),"ca-app-pub-9204826362738432/3519511403", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }

}
