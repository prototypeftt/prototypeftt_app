package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BuyAssetScreen extends AppCompatActivity {
    TextView aName, aPrice;
    EditText aAmount;
    DatabaseReference mDatabaseC;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_asset_screen);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userId = firebaseUser.getUid();

        Intent intent = getIntent();
        Asset asset = intent.getParcelableExtra("asset");
        String assetName = asset.getAssetName();
        String assetPrice = asset.getClosePrice();

        aName = findViewById(R.id.AssetName);
        aPrice = findViewById(R.id.AssetPrice);
        aAmount = findViewById(R.id.AmountToBuy);

        aName.setText(assetName);
        aPrice.setText(assetPrice);

        changeActivity(assetPrice, userId, asset);
    }

    private void changeActivity(String assetPrice, String userId, Asset asset){
        Button paymentButton = findViewById(R.id.SelectPaymentTypeButton);
        paymentButton.setOnClickListener(view -> makePayment(assetPrice, userId, asset));
    }

    private void makePayment(String assetPrice, String userId, Asset asset) {
        String amount = aAmount.getText().toString();
        if(amount.isEmpty()){
            aAmount.setError("Amount is required");
            aAmount.requestFocus();
        }else{
            asset.setQty(amount);
            mDatabaseC = FirebaseDatabase.getInstance().getReference("clients/" + userId + "/" + asset.getAssetCategory());
            mDatabaseC.child(asset.getAssetId()).setValue(asset);
            Toast.makeText(BuyAssetScreen.this, "Asset selected successfully", Toast.LENGTH_LONG).show();
            float fTotalAmount = Float.parseFloat(amount) * Float.parseFloat(assetPrice);
            String totalAmount = Float.toString(fTotalAmount);
            Intent intent = new Intent(BuyAssetScreen.this, PaypalPaymentScreenAsset.class);
            intent.putExtra("amount", totalAmount);
            startActivity(intent);
        }
    }
}