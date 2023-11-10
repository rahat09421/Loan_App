package com.example.harwaqt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {
    TextView forget, signup;
    EditText mail, password;
    Button login;
    View google;
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forget = findViewById(R.id.forget);
        signup = findViewById(R.id.signup);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.pw);
        login = findViewById(R.id.login);
//        fb=findViewById(R.id.fb);
        google = findViewById(R.id.google);
        mAuth = FirebaseAuth.getInstance();

        //Login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString().trim();
                String password1 = password.getText().toString().trim();
                if (email.isEmpty() && password1.isEmpty()) {
                    SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Missing Email and Password");
                    pDialog.setCancelable(true);
                    pDialog.show();
                } else if (email.isEmpty()) {

                    SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Email is required");
                    pDialog.setCancelable(true);
                    pDialog.show();
                }
                if (password1.isEmpty()) {
                    password.setError("Password is required");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    mail.setError("Invalid Email");
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("Incorrect Email or Password");
                                    pDialog.setCancelable(true);
                                    pDialog.show();
                                }
                            }
                        });
            }
        });
        //Google Id Login
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.NORMAL_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Coming Soon");
                pDialog.setCancelable(true);
                pDialog.show();
            }
        });

        //Signup Screen
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SignupScreen.class);
                startActivity(i);
                finish();
            }
        });
        //Forget Password
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Forget_Password.class);
                startActivity(i);
            }
        });
    }
}
