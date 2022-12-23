package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterScreen extends AppCompatActivity {

    private EditText EditFullname, EditPersonalEmail, EditPassword, EditPasswordCheck;

    DatabaseReference clientReference;
    FirebaseAuth mAuth;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        EditFullname = findViewById(R.id.fullName);
        EditPersonalEmail = findViewById(R.id.PersonalEmail);
        EditPassword = findViewById(R.id.CreatePassword);
        EditPasswordCheck = findViewById(R.id.PasswordCheck);

        clientReference = FirebaseDatabase.getInstance().getReference("clients");
        mAuth = FirebaseAuth.getInstance();

        client = new Client();
        changeActivity();
    }

    private void changeActivity(){
        Button RegisterButton = findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(view -> createUser());
    }

    private void createUser(){
        String fullName = EditFullname.getText().toString();
        String email = EditPersonalEmail.getText().toString().trim();
        String password = EditPassword.getText().toString().trim();
        String checkPassword = EditPasswordCheck.getText().toString().trim();

        if(fullName.isEmpty()){
            EditFullname.setError("Fullname is required");
            EditFullname.requestFocus();
        }else if(email.isEmpty()){
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
        }else if(!checkPassword.equals(password)){
            EditPasswordCheck.setError("Password check is not equal to Password");
            EditPasswordCheck.requestFocus();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EditPersonalEmail.setError("Enter a valid email");
            EditPersonalEmail.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    client.setName(fullName);
                    clientReference.child(userUid).setValue(client);
                    Toast.makeText(RegisterScreen.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterScreen.this, LoginScreen.class));
                }else{
                    Toast.makeText(RegisterScreen.this, "Registration error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}