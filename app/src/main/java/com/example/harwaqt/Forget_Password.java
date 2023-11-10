package com.example.harwaqt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;


public class Forget_Password extends AppCompatActivity {
    private EditText emailEditText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        emailEditText = findViewById(R.id.emailEditText);
        Button resetPasswordButton = findViewById(R.id.resetPasswordButton);
        firebaseAuth = FirebaseAuth.getInstance();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewforgetpass);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();

                if (email.isEmpty()) {
                    SweetAlertDialog pDialog = new SweetAlertDialog(Forget_Password.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Oops...")
                            .setContentText("Enter your Email");
                    pDialog.setConfirmText("Ok");
                    pDialog.setCancelable(true);
                    pDialog.show();
                } else {
                    // Send a password reset email
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                SweetAlertDialog pDialog = new SweetAlertDialog(Forget_Password.this, SweetAlertDialog.SUCCESS_TYPE);
                                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                pDialog.setTitleText("Congratulations")
                                        .setContentText("Password reset email sent. Check your email inbox.");
                                pDialog.setConfirmText("Ok");
                                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        Intent i = new Intent(Forget_Password.this, MainActivity.class);
                                        startActivity(i);
                                    }
                                });
                                pDialog.setCancelable(true);
                                pDialog.show();
                            } else {
                                // Check if the email is not registered
                                if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                                    SweetAlertDialog pDialog = new SweetAlertDialog(Forget_Password.this, SweetAlertDialog.ERROR_TYPE);
                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("Oops...")
                                            .setContentText("Email not registered");
                                    pDialog.setConfirmText("Ok");
                                    pDialog.setCancelable(true);
                                    pDialog.show();
                                } else {
                                    SweetAlertDialog pDialog = new SweetAlertDialog(Forget_Password.this, SweetAlertDialog.ERROR_TYPE);
                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("Oops...")
                                            .setContentText("Failed to send password reset email.");
                                    pDialog.setConfirmText("Try Again");
                                    pDialog.show();
                                }
                            }
                        }
                    });

                }
            }
        });
    }
}