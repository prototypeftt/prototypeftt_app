package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BuyAssetScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_asset_screen);

        changeActivity();
    }

    private void changeActivity(){
        Button SelectPaymentTypeButton = (Button) findViewById(R.id.SelectPaymentTypeButton);
        SelectPaymentTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyAssetScreen.this, PaymentTypeScreen.class));
            }
        });
    }
}