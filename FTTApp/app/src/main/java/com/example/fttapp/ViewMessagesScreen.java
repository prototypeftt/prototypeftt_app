package com.example.fttapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ViewMessagesScreen extends AppCompatActivity {

    FirebaseAuth mAuth;
    Query messageQuery;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_messages_screen);

        mRecyclerView = findViewById(R.id.recyclerMessages);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userUid = firebaseUser.getUid();

        messageQuery = FirebaseDatabase.getInstance().getReference("messages/" + userUid);
        new FirebaseDatabaseHelper(messageQuery).readMessages((messages, keys) -> new RecyclerViewConfig().setConfigMessage(mRecyclerView, ViewMessagesScreen.this, messages, keys));
    }
}