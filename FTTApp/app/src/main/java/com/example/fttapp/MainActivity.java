package com.example.fttapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        mAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        changeActivity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null & gsc == null){
            startActivity(new Intent(MainActivity.this, LoginScreen.class));
        }
    }

    private void changeActivity(){
        Button BrokerSearchButton = (Button) findViewById(R.id.BrokerSearchButton);
        Button AssetSearchButton = (Button) findViewById(R.id.AssetSearchButton);
        Button ViewPortfolioButton = (Button) findViewById(R.id.ViewPortfolioButton);
        Button ViewMessagesButton = (Button) findViewById(R.id.ViewMessagesButton);
        Button HelpButton = (Button) findViewById(R.id.HelpButton);
        Button RateButton = (Button) findViewById(R.id.RateButton);
        Button LogoutButton = (Button) findViewById(R.id.LogoutButton);
        Button ViewReviewsButton = (Button) findViewById(R.id.viewReviewsButton);
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
        ViewMessagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewMessagesScreen.class));
            }
        });
        HelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HelpScreen.class));
            }
        });
        RateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ReviewScreen.class));
            }
        });
        ViewReviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewReviewsScreen.class));
            }
        });
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                gsc.signOut();
                startActivity(new Intent(MainActivity.this, LoginScreen.class));
            }
        });
    }
}