package com.example.fttapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        mAuth = FirebaseAuth.getInstance();
        changeActivity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(MainActivity.this, LoginScreen.class));
        }
    }

    private void changeActivity(){
        Button BrokerSearchButton = (Button) findViewById(R.id.BrokerSearchButton);
        Button AssetSearchButton = (Button) findViewById(R.id.AssetSearchButton);
        Button ViewPortfolioButton = (Button) findViewById(R.id.ViewPortfolioButton);
        Button HelpButton = (Button) findViewById(R.id.HelpButton);
        Button RateButton = (Button) findViewById(R.id.RateButton);
        Button LogoutButton = (Button) findViewById(R.id.LogoutButton);
        BrokerSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BrokersScreen.class));
            }
        });
        AssetSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AssetsScreen.class));
            }
        });
        ViewPortfolioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PortfolioScreen.class));
            }
        });
        HelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChatbotScreen.class));
            }
        });
        RateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ReviewScreen.class));
            }
        });
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginScreen.class));
            }
        });
    }
}