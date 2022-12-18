package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PortfolioScreen extends AppCompatActivity {

    FirebaseAuth mAuth;
    Query stockQuery, cryptoQuery;
    RecyclerView mRecyclerView1, mRecyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_screen);

        mRecyclerView1 = findViewById(R.id.recyclerViewStock);
        mRecyclerView2 = findViewById(R.id.recyclerViewCrypto);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userUid = firebaseUser.getUid();

        stockQuery = FirebaseDatabase.getInstance().getReference("clients/" + userUid + "/STOCK");
        cryptoQuery = FirebaseDatabase.getInstance().getReference("clients/" + userUid + "/CRYPTO");
        new FirebaseDatabaseHelper(stockQuery).readAssets((stocks, stockKeys) -> new RecyclerViewConfig().setConfigMyStock(mRecyclerView1, PortfolioScreen.this, stocks, stockKeys));
        new FirebaseDatabaseHelper(cryptoQuery).readAssets((cryptos, cryptoKeys) -> new RecyclerViewConfig().setConfigMyCrypto(mRecyclerView2, PortfolioScreen.this, cryptos, cryptoKeys));
    }
}