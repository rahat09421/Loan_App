package com.example.harwaqt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.Objects;

public class ApplyNowActivity extends AppCompatActivity {
    Button bt1;
    EditText name1, fname1, dob1, contactEmail, contactPhone, address, accountHolderName, accountNumber, confirmAccountNumber, loanAmount, loanDuration;
    Spinner city, spinnerEducation, spinnerMartial, spinnerIncome, spinnerOccupation, spinnerMethod, spinnerLoanType;

    private DatabaseReference databaseReference;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_now);
        dob1 = findViewById(R.id.dob);
        bt1 = findViewById(R.id.next);
        name1 = findViewById(R.id.name);
        fname1 = findViewById(R.id.fname);
        spinnerIncome = findViewById(R.id.spinnerincome);
        spinnerEducation = findViewById(R.id.spinnerEducation);
        spinnerMartial = findViewById(R.id.spinnerMarital);
        spinnerOccupation = findViewById(R.id.spinnerOccupation);
        contactEmail = findViewById(R.id.contactemail);
        contactPhone = findViewById(R.id.contactphone);
        address = findViewById(R.id.address);
        city = findViewById(R.id.spinnerCity);
        accountHolderName = findViewById(R.id.aHN);
        accountNumber = findViewById(R.id.aNumber);
        confirmAccountNumber = findViewById(R.id.confirmANumber);
        spinnerMethod = findViewById(R.id.spinnerMethod);
        spinnerLoanType = findViewById(R.id.spinnerLoanType);
        loanAmount = findViewById(R.id.loanAmount);
        loanDuration = findViewById(R.id.loanDuration);


        databaseReference = FirebaseDatabase.getInstance().getReference("Loan_Application");


        setupSpinner(spinnerEducation, R.array.education_levels);
        setupSpinner(spinnerMartial, R.array.marital_status);
        setupSpinner(spinnerOccupation, R.array.occupation);
        setupSpinner(spinnerIncome, R.array.monthly_income);
        setupSpinner(city, R.array.resident_city);
        setupSpinner(spinnerMethod, R.array.mobile_wallet);
        setupSpinner(spinnerLoanType, R.array.loan_type);

        SharedPreferences sharedPreferences = getSharedPreferences("StepPrefs", Context.MODE_PRIVATE);
        int currentStep = sharedPreferences.getInt("currentStep", 1);



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView1 = findViewById(R.id.adViewapplynow1);
        AdView mAdView2 = findViewById(R.id.adViewapplynow2);
        AdView mAdView3 = findViewById(R.id.adViewapplynow3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest);
        mAdView2.loadAd(adRequest);
        mAdView3.loadAd(adRequest);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    if (isConnectedToInternet()) {
                        // Proceed with your activity logic
                        SweetAlertDialog pDialog = new SweetAlertDialog(ApplyNowActivity.this, com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog.NORMAL_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("Submit");
                        pDialog.setContentText("Are Your Sure To Submit Application?");
                        pDialog.setConfirmText("Yes");
                        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sendDataToFirebase();
                                // Update the step to the next one
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("currentStep", currentStep + 1);
                                editor.apply();
                                {
                                    if (mInterstitialAd != null) {
                                        mInterstitialAd.show(ApplyNowActivity.this);

                                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                            @Override
                                            public void onAdClicked() {
                                                // Called when a click is recorded for an ad.
                                            }
                                            @Override
                                            public void onAdDismissedFullScreenContent() {
                                                // Called when ad is dismissed.
                                                // Set the ad reference to null so you don't show the ad a second time.
                                                Intent i = new Intent(ApplyNowActivity.this, LoanEstimationActivity.class);
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
                                        Intent i = new Intent(ApplyNowActivity.this, LoanEstimationActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            }
                        });
                        pDialog.setCancelText("No");
                        pDialog.setCancelable(true);
                        pDialog.show();
                    } else {
                        SweetAlertDialog pDialog = new SweetAlertDialog(ApplyNowActivity.this, com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog.NORMAL_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("No Internet Connection");
                        pDialog.setContentText("Please connect to the internet and try again.");
                        pDialog.setConfirmText("Ok");
                        pDialog.setCancelable(true);
                        pDialog.show();
                    }
                }
            }
        });

    }


    private void setupSpinner(Spinner spinner, int arrayResource) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                arrayResource,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private boolean validateForm() {
        boolean isValid = true;

        // Validate EditText fields
        if (name1.getText().toString().isEmpty()) {
            name1.setError("Name is required");
            isValid = false;
        } else if (fname1.getText().toString().isEmpty()) {
            fname1.setError("Father Name is required");
            isValid = false;
        } else if (dob1.getText().toString().isEmpty()) {
            dob1.setError("DOB is required");
            isValid = false;
        }

        // Validate Spinner selections
        else if (spinnerEducation.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select Education", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (spinnerMartial.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select Marital Status", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (spinnerOccupation.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select Occupation", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (spinnerIncome.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select Monthly Income", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (contactEmail.getText().toString().isEmpty()) {
            contactEmail.setError("Email is required");
            isValid = false;
        } else if (contactPhone.getText().toString().isEmpty()) {
            contactPhone.setError("Phone No. is required");
            isValid = false;
        }
        // Validate Spinner selections
        else if (city.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select Residential City", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (address.getText().toString().isEmpty()) {
            address.setError("Complete Address is required");
            isValid = false;
        } else if (spinnerMethod.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select Method", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (accountHolderName.getText().toString().isEmpty()) {
            accountHolderName.setError("Account Holder Name is required");
            isValid = false;
        } else if (accountNumber.getText().toString().isEmpty()) {
            accountNumber.setError("Account No. is required");
            isValid = false;
        } else if (confirmAccountNumber.getText().toString().isEmpty()) {
            confirmAccountNumber.setError("Confirm Account No. is required");
            isValid = false;
        } else if (spinnerLoanType.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select Loan Type", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (loanAmount.getText().toString().isEmpty()) {
            loanAmount.setError("Loan Amount is required");
            isValid = false;
        } else if (loanDuration.getText().toString().isEmpty()) {
            loanDuration.setError("Loan Duration is required");
            isValid = false;
        }
        return isValid;
    }

    private void sendDataToFirebase() {
        String currentUserUid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        // Create a reference to the specific user's node
        DatabaseReference userReference = databaseReference.child(currentUserUid);
        // Get data from UI elements
        String name = name1.getText().toString();
        String fname = fname1.getText().toString();
        String dob = dob1.getText().toString();
        String education = spinnerEducation.getSelectedItem().toString();
        String maritalStatus = spinnerMartial.getSelectedItem().toString();
        String occupation = spinnerOccupation.getSelectedItem().toString();
        String monthlyIncome = spinnerIncome.getSelectedItem().toString();

        String email = contactEmail.getText().toString();
        String phone = contactPhone.getText().toString();
        String city1 = city.getSelectedItem().toString();
        String address1 = address.getText().toString();

        String method = spinnerMethod.getSelectedItem().toString();
        String holderName = accountHolderName.getText().toString();
        String accountNumber1 = accountNumber.getText().toString();
        String confirmAccountNum = confirmAccountNumber.getText().toString();

        String loanType = spinnerLoanType.getSelectedItem().toString();
        String loanAmount1 = loanAmount.getText().toString();
        String loanDuration1 = loanDuration.getText().toString();

        // Create a DataModel object
        DataModel data = new DataModel(name, fname, dob, education, maritalStatus, occupation, monthlyIncome, email, phone, city1, address1, method, holderName, accountNumber1, confirmAccountNum, loanType, loanAmount1, loanDuration1);

        // Push data to Firebase
        userReference.setValue(data);

        // You can also use addOnCompleteListener to handle success or failure
    }

    private boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
    public  void loadint(){
        MobileAds.initialize(ApplyNowActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {}
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(ApplyNowActivity.this,"ca-app-pub-9204826362738432/3519511403", adRequest,
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

