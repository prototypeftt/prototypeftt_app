package com.example.fttapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HelpScreen extends AppCompatActivity {

    private TextView helpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbot_screen);

        helpText = findViewById(R.id.helpResponse);

        changeActivity();
    }

    private void changeActivity(){
        Button assetSearch = findViewById(R.id.assetSearchButton);
        Button brokerSearch = findViewById(R.id.brokerSearchButton);
        Button buyAsset = findViewById(R.id.buyAssetButton);
        Button sellAsset = findViewById(R.id.sellAssetButton);
        Button seeMessages = findViewById(R.id.whereMessagesButton);
        Button contactBroker = findViewById(R.id.howToContactButton);
        assetSearch.setOnClickListener(view -> {
            helpText.setText("To search for an asset, go to the main screen, asset search and you will be able to see cryptos and stocks separated.");
            assetSearch.setVisibility (View.GONE);
            brokerSearch.setVisibility (View.GONE);
            buyAsset.setVisibility (View.GONE);
            sellAsset.setVisibility (View.GONE);
            seeMessages.setVisibility (View.GONE);
            contactBroker.setVisibility (View.GONE);
        });
        brokerSearch.setOnClickListener(view -> {
            helpText.setText("To search for a broker, go to the main screen, broker search and you will be able to see brokers filtered by associated institution");
            assetSearch.setVisibility (View.GONE);
            brokerSearch.setVisibility (View.GONE);
            buyAsset.setVisibility (View.GONE);
            sellAsset.setVisibility (View.GONE);
            seeMessages.setVisibility (View.GONE);
            contactBroker.setVisibility (View.GONE);
        });
        buyAsset.setOnClickListener(view -> {
            helpText.setText("To buy an asset, search for the asset in the asset search screen, select \"Buy asset\" and fill in the quantity");
            assetSearch.setVisibility (View.GONE);
            brokerSearch.setVisibility (View.GONE);
            buyAsset.setVisibility (View.GONE);
            sellAsset.setVisibility (View.GONE);
            seeMessages.setVisibility (View.GONE);
            contactBroker.setVisibility (View.GONE);
        });
        sellAsset.setOnClickListener(view -> {
            helpText.setText("To sell an asset, search the asset in your portfolio, select sell asset and fill the quantity to sell");
            assetSearch.setVisibility (View.GONE);
            brokerSearch.setVisibility (View.GONE);
            buyAsset.setVisibility (View.GONE);
            sellAsset.setVisibility (View.GONE);
            seeMessages.setVisibility (View.GONE);
            contactBroker.setVisibility (View.GONE);
        });
        seeMessages.setOnClickListener(view -> {
            helpText.setText("To see your messages, go to the main screen and select \"My messages\"");
            assetSearch.setVisibility (View.GONE);
            brokerSearch.setVisibility (View.GONE);
            buyAsset.setVisibility (View.GONE);
            sellAsset.setVisibility (View.GONE);
            seeMessages.setVisibility (View.GONE);
            contactBroker.setVisibility (View.GONE);
        });
        contactBroker.setOnClickListener(view -> {
            helpText.setText("To contact a broker, search for the broker in the broker search and after that click in contact broker");
            assetSearch.setVisibility (View.GONE);
            brokerSearch.setVisibility (View.GONE);
            buyAsset.setVisibility (View.GONE);
            sellAsset.setVisibility (View.GONE);
            seeMessages.setVisibility (View.GONE);
            contactBroker.setVisibility (View.GONE);
        });
    }
}