package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterScreen extends AppCompatActivity {

    private EditText EditPersonalEmail, EditPassword, EditPasswordCheck;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        EditPersonalEmail = findViewById(R.id.PersonalEmail);
        EditPassword = findViewById(R.id.CreatePassword);
        EditPasswordCheck = findViewById(R.id.PasswordCheck);

        mAuth = FirebaseAuth.getInstance();
        changeActivity();
    }

    private void changeActivity(){
        Button RegisterButton = (Button) findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    private void createUser(){
        String email = EditPersonalEmail.getText().toString().trim();
        String password = EditPassword.getText().toString().trim();
        String checkPassword = EditPasswordCheck.getText().toString().trim();

        if(email.isEmpty()){
            EditPersonalEmail.setError("Email is required");
            EditPersonalEmail.requestFocus();
        }else if(password.isEmpty()){
            EditPassword.setError("Password is required");
            EditPassword.requestFocus();
        }else if(password.length() < 6){
            EditPassword.setError("Password should be at least 6 characters");
            EditPassword.requestFocus();
        }else if(checkPassword.isEmpty()){
            EditPasswordCheck.setError("Password check is empty or incorrect");
            EditPasswordCheck.requestFocus();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EditPersonalEmail.setError("Enter a valid email");
            EditPersonalEmail.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterScreen.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterScreen.this, LoginScreen.class));
                    }else{
                        Toast.makeText(RegisterScreen.this, "Registration error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}