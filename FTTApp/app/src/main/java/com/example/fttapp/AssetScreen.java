package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AssetScreen extends AppCompatActivity {

    private TextView assetName, assetPrice, assetPrediction, assetUpDown;
    FirebaseAuth mAuth;
    DatabaseReference mDatabaseC;

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

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userId = firebaseUser.getUid();

        changeActivity(asset, userId);
    }

    private void changeActivity(Asset asset, String userId){
        Button BuyAssetButton = (Button) findViewById(R.id.BuyAssetButton);
        Button SellAssetButton = (Button) findViewById(R.id.SellAssetButton);
        asset.setQty("1");
        BuyAssetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseC = FirebaseDatabase.getInstance().getReference("clients/" + userId + "/" + asset.getAssetCategory());
                mDatabaseC.child(asset.getAssetId()).setValue(asset);
                Toast.makeText(AssetScreen.this, "Asset selected successfully", Toast.LENGTH_LONG).show();
            }
        });
        SellAssetButton.setOnClickListener(view -> startActivity(new Intent(AssetScreen.this, BuyAssetScreen.class)));
    }
}