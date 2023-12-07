package com.example.harwaqt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class LoanCalculatorFragment extends Fragment {
    private EditText editTextLoanAmount;
    private EditText editTextNumberOfDays;
    private TextView textViewResult;
    InterstitialAd mInterstitialAd;
    public LoanCalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_loan_calculator, container, false);

        editTextLoanAmount = view.findViewById(R.id.amount);
        editTextNumberOfDays = view.findViewById(R.id.days);
        textViewResult = view.findViewById(R.id.textview);

        Button btnCalculate = view.findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
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
                            calculateLoan();
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
                    calculateLoan();
                }
            }
        });
        return view;
    }

    @SuppressLint("SetTextI18n")
    private void calculateLoan() {
        try {
            double loanAmount = Double.parseDouble(editTextLoanAmount.getText().toString());
            int numberOfDays = Integer.parseInt(editTextNumberOfDays.getText().toString());

            double dailyRate = 0.06 / 100; // 0.6%

            double markup = loanAmount * dailyRate * numberOfDays;

            double totalPayableAmount = loanAmount + markup;

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, numberOfDays);
            Date dueDate = calendar.getTime();
            if (loanAmount < 500 || loanAmount > 25000 || numberOfDays < 1 || numberOfDays > 30) {
                textViewResult.setText("Loan amount must be between Rs. 500-25000 and days between 1-30 Days");
                return;
            }

            textViewResult.setText(String.format(Locale.getDefault(),
                    "Markup: %.2f\nDue Date: %s\nTotal Payable Amount: %.2f",
                    markup, sdf.format(dueDate), totalPayableAmount));

        } catch (NumberFormatException e) {
            textViewResult.setText("Please enter valid numbers");
        }
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