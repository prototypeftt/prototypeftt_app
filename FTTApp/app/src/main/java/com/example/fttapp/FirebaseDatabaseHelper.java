package com.example.fttapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private Query mReference;
    private List<Broker> brokers = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Broker> brokers, List<String> keys);
    }

    public FirebaseDatabaseHelper(Query mReference){
        this.mReference = mReference;
    }

    public void readBrokers(final DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                brokers.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode:snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Broker broker = keyNode.getValue(Broker.class);
                    brokers.add(broker);
                }
                dataStatus.DataIsLoaded(brokers, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
