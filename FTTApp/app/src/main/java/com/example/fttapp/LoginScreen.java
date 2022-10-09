package com.example.fttapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {

    private EditText EditLoginEmail, EditLoginPassword;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        EditLoginEmail = findViewById(R.id.Username);
        EditLoginPassword = findViewById(R.id.Password);

        mAuth = FirebaseAuth.getInstance();
        changeActivity();
    }

    private void changeActivity(){
        Button LogInButton = (Button) findViewById(R.id.LogInButton);
        Button ToRegisterButton = (Button) findViewById(R.id.ToRegisterButton);
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
                //startActivity(new Intent(LoginScreen.this, MainActivity.class));
            }
        });
        ToRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this, RegisterScreen.class));
            }
        });
    }

    private void loginUser(){
        String email = EditLoginEmail.getText().toString().trim();
        String password = EditLoginPassword.getText().toString().trim();

        if(email.isEmpty()){
            EditLoginEmail.setError("Email is required");
            EditLoginPassword.requestFocus();
        }else if(password.isEmpty()){
            EditLoginPassword.setError("Password is required");
            EditLoginPassword.requestFocus();
        }else if(password.length() < 6){
            EditLoginPassword.setError("Password should be at least 6 characters");
            EditLoginPassword.requestFocus();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EditLoginEmail.setError("Enter a valid email");
            EditLoginEmail.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginScreen.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginScreen.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginScreen.this, "Log in error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}