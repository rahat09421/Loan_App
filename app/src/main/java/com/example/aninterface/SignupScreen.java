package com.example.aninterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupScreen extends AppCompatActivity {
    TextView login,term,policy;
    EditText email,phone,password;
    Button signup;
    private FirebaseAuth mAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        login = findViewById(R.id.login1);
        term = findViewById(R.id.term);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        policy = findViewById(R.id.policy);
        email = findViewById(R.id.email);
        signup = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 =email.getText().toString().trim();
                String password1 =password.getText().toString().trim();


                boolean isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked){

                    mAuth.createUserWithEmailAndPassword(email1, password1)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent i= new Intent(SignupScreen.this,MainActivity.class);
                                        startActivity(i);
                                        Toast.makeText(SignupScreen.this, "Account Created Successfully.",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(SignupScreen.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignupScreen.this,MainActivity.class);
                startActivity(i);
            }
        });
        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignupScreen.this, "Working Proper", Toast.LENGTH_SHORT).show();
            }
        });
        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignupScreen.this, "Working Proper", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean CheckAllFields() {
        if (email.length() == 0) {
            email.setError("Email is Required");
            return false;
        }
        if (phone.length() == 0) {
           phone.setError("Mobile No. is Required");
            return false;
        }
        if (password.length() == 0) {
               password.setError("Password is required");
                 return false;
             } else if (password.length() < 8) {
                password.setError("Password must be minimum 8 characters");
                  return false;
    }
        return true;
    }

}