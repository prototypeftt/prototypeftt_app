package com.example.fttapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        changeActivity();
    }

    private void changeActivity(){
        Button BrokerSearchButton = (Button) findViewById(R.id.BrokerSearchButton);
        Button AssetSearchButton = (Button) findViewById(R.id.AssetSearchButton);
        Button ViewPortfolioButton = (Button) findViewById(R.id.ViewPortfolioButton);
        Button HelpButton = (Button) findViewById(R.id.HelpButton);
        Button RateButton = (Button) findViewById(R.id.RateButton);
        BrokerSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainScreen.this, BrokersScreen.class));
            }
        });
        AssetSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainScreen.this, AssetsScreen.class));
            }
        });
        ViewPortfolioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainScreen.this, PortfolioScreen.class));
            }
        });
        HelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainScreen.this, ChatbotScreen.class));
            }
        });
        RateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainScreen.this, ReviewScreen.class));
            }
        });
    }
}