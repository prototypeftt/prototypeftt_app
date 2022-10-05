package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BrokerScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broker_screen);

        changeActivity();
    }

    private void changeActivity(){
        Button BrokerInfoButton = (Button) findViewById(R.id.BrokerInfoButton);
        Button ContactBroker = (Button) findViewById(R.id.ContactBroker);
        Button BrokerPremium = (Button) findViewById(R.id.BrokerPremium);
        BrokerInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BrokerScreen.this, BrokerPersonalScreen.class));
            }
        });
        ContactBroker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BrokerScreen.this, ContactBrokerScreen.class));
            }
        });
        BrokerPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BrokerScreen.this, PaymentTypeScreen.class));
            }
        });
    }
}