package com.example.harwaqt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class launcherActivity extends AppCompatActivity {
    private static final long SPLASH_DELAY = 3000; // 3 seconds
    FirebaseAuth mAuth;
    FirebaseUser user;
    private boolean splashSkipped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Button skipButton = findViewById(R.id.skipButton);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splashSkipped = true;
                startNextActivity();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!splashSkipped) {
                    startNextActivity();
                }
            }
        }, SPLASH_DELAY);
    }
    private void startNextActivity() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // User is already logged in, go to HomeActivity
            startActivity(new Intent(launcherActivity.this, HomeActivity.class));
        } else {
            // No user logged in, go to LoginActivity
            startActivity(new Intent(launcherActivity.this, MainActivity.class));
        }
        finish();
    }
}