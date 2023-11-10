package com.example.aninterface;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {
    TextView forget,signup;
    EditText mail,password;
    Button login;
    ImageView google,fb;
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forget=findViewById(R.id.forget);
        signup=findViewById(R.id.signup);
        mail=findViewById(R.id.mail);
        password=findViewById(R.id.pw);
        login=findViewById(R.id.login);
        fb=findViewById(R.id.fb);
        google=findViewById(R.id.google);
        mAuth = FirebaseAuth.getInstance();

        //Login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =mail.getText().toString().trim();
                String password1 =password.getText().toString().trim();

                if (email.isEmpty()) {
                    mail.setError("Email is required");
                    return;
                }
                if (password1.isEmpty()) {
                    password.setError("Password is required");
                    return ;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    mail.setError("Invalid Email");
                    return;
               }
                mAuth.signInWithEmailAndPassword(email,password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent i = new  Intent(MainActivity.this, HomeActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, "Email or Password is Incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        });
            }
        });
        //Google Id Login
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.mainscreen), "Working Proper", Snackbar.LENGTH_SHORT).show();
            }
        });
        //Facebook Id Login
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
               pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Still in Development");
               pDialog.setCancelable(true);
                pDialog.show();
            }
        });
        //Signup Screen
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,SignupScreen.class);
                startActivity(i);
            }
        });
        //Forget Password
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Still in Development");
                pDialog.setCancelable(true);
                pDialog.show();
            }
        });
    }
}
