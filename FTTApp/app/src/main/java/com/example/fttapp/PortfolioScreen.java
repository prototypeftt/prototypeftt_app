package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PortfolioScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_screen);

        changeActivity();
    }

    private void changeActivity(){
        Button InfoAsset1Button = (Button) findViewById(R.id.InfoAsset1Button);
        InfoAsset1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PortfolioScreen.this, AssetScreen.class));
            }
        });
    }
}