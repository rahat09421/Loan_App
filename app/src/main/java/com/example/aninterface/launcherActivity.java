package com.example.aninterface;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class launcherActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        mAuth = FirebaseAuth.getInstance();
        user =mAuth.getCurrentUser();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user!=null){
                Intent i = new Intent(launcherActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
                } else{
                    Intent i = new Intent(launcherActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        },2000);
    }
}