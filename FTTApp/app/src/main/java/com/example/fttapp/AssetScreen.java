package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AssetScreen extends AppCompatActivity {

    private TextView assetName, assetPrice, assetPrediction, assetUpDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_screen);

        Intent intent = getIntent();
        Asset asset = intent.getParcelableExtra("asset");

        assetName = findViewById(R.id.AssetName);
        assetPrice = findViewById(R.id.CurrentValue);
        assetPrediction = findViewById(R.id.TomorrowPrediction);
        assetUpDown = findViewById(R.id.PredictedUpDpwn);

        assetName.setText(asset.getAssetName());
        assetPrice.setText(asset.getClosePrice());
        assetPrediction.setText(asset.getPredictedPrice());
        assetUpDown.setText(asset.getAssetPrediction());

        changeActivity();
    }

    private void changeActivity(){
        Button BuyAssetButton = (Button) findViewById(R.id.BuyAssetButton);
        Button SellAssetButton = (Button) findViewById(R.id.SellAssetButton);
        BuyAssetButton.setOnClickListener(view -> startActivity(new Intent(AssetScreen.this, BuyAssetScreen.class)));
        SellAssetButton.setOnClickListener(view -> startActivity(new Intent(AssetScreen.this, BuyAssetScreen.class)));
    }
}