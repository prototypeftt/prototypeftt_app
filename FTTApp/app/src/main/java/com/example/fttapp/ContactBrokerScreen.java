package com.example.fttapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class ContactBrokerScreen extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference messageReference;
    private EditText EditMessage;
    Message message;
    Query userQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_broker_screen);
        EditMessage = findViewById(R.id.myMessageText);

        Intent intent = getIntent();
        Broker broker = intent.getParcelableExtra("broker");
        String brokerUid = broker.getUid();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userUid = firebaseUser.getUid();
        userQuery = FirebaseDatabase.getInstance().getReference("clients/" + userUid);
        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String userName = snapshot.child("name").getValue(String.class);
                    changeActivity(userUid, brokerUid, userName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        messageReference = FirebaseDatabase.getInstance().getReference("messages");
        message = new Message();
    }

    private void changeActivity(String userUid, String brokerUid, String userName){

        Button SendQueryButton = (Button) findViewById(R.id.SendQueryButton);
        SendQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageText = EditMessage.getText().toString();
                message.setMessage(messageText);
                message.setUuid(userUid);
                message.setName(userName);

                String keyId = messageReference.push().getKey();
                messageReference.child(brokerUid).child(keyId).setValue(message);
                startActivity(new Intent(ContactBrokerScreen.this, MainActivity.class));
            }
        });
    }
}