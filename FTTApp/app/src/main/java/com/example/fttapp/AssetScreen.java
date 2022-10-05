package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AssetScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_screen);

        changeActivity();
    }

    private void changeActivity(){
        Button BuyAssetButton = (Button) findViewById(R.id.BuyAssetButton);
        Button SellAssetButton = (Button) findViewById(R.id.SellAssetButton);
        BuyAssetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AssetScreen.this, BuyAssetScreen.class));
            }
        });
        SellAssetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AssetScreen.this, BuyAssetScreen.class));
            }
        });
    }
}