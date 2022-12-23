package com.example.fttapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        Button BrokerSearchButton = findViewById(R.id.BrokerSearchButton);
        Button AssetSearchButton = findViewById(R.id.AssetSearchButton);
        Button ViewPortfolioButton = findViewById(R.id.ViewPortfolioButton);
        Button ViewMessagesButton = findViewById(R.id.ViewMessagesButton);
        Button HelpButton = findViewById(R.id.HelpButton);
        Button RateButton = findViewById(R.id.RateButton);
        Button LogoutButton = findViewById(R.id.LogoutButton);
        Button ViewReviewsButton = findViewById(R.id.viewReviewsButton);

        BrokerSearchButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, BrokersScreen.class)));
        AssetSearchButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AssetsScreen.class)));
        ViewPortfolioButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, PortfolioScreen.class)));
        ViewMessagesButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ViewMessagesScreen.class)));
        HelpButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, HelpScreen.class)));
        RateButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ReviewScreen.class)));
        ViewReviewsButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ViewReviewsScreen.class)));
        LogoutButton.setOnClickListener(view -> {
            mAuth.signOut();
            gsc.signOut();
            startActivity(new Intent(MainActivity.this, LoginScreen.class));
        });
    }
}