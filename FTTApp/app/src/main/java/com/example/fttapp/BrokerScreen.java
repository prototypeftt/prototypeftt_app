package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BrokerScreen extends AppCompatActivity {

    private TextView brokerNameT, brokerInstitutionT, brokerFeeT;
    FirebaseAuth mAuth;
    DatabaseReference mDatabaseB, mDatabaseC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broker_screen);

        Intent intent = getIntent();
        Broker broker = intent.getParcelableExtra("broker");

        brokerNameT = findViewById(R.id.brokerNameTextview);
        brokerInstitutionT = findViewById(R.id.institutionTextview);
        brokerFeeT = findViewById(R.id.brokerFeeTextview);

        brokerNameT.setText(broker.getName());
        brokerInstitutionT.setText(broker.getInstitution());
        brokerFeeT.setText(broker.getPremium());

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        mDatabaseB = FirebaseDatabase.getInstance().getReference("brokers/" + broker.getUid() + "/clients");
        String userId = firebaseUser.getUid();

        mDatabaseB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String clientNumber = Long.toString(snapshot.getChildrenCount());
                changeActivity(clientNumber, userId, broker);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void changeActivity(String clientNumber, String userId, Broker broker){
        Button ContactBroker = (Button) findViewById(R.id.contactBrokerButton);
        Button hireBroker = (Button) findViewById(R.id.hireBrokerButton);
        ContactBroker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BrokerScreen.this, ContactBrokerScreen.class);
                intent.putExtra("broker", broker);
                startActivity(intent);
            }
        });
        hireBroker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseB.child(clientNumber).setValue(userId);
                mDatabaseC = FirebaseDatabase.getInstance().getReference("clients/" + userId);
                mDatabaseC.child("broker").setValue(broker.getUid());
                Toast.makeText(BrokerScreen.this, "Broker selected successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
}