package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BrokersScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brokers_screen);

        changeActivity();
    }

    private void changeActivity(){
        Button InfoBroker1 = (Button) findViewById(R.id.InfoBroker1Button);
        InfoBroker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BrokersScreen.this, BrokerScreen.class));
            }
        });
    }
}