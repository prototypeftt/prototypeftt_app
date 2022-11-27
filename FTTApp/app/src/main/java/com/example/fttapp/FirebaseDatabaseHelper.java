package com.example.fttapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private Query mReference;
    private List<Broker> brokers = new ArrayList<>();
    private List<Asset> assets = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public interface DataStatusBrokers {
        void DataIsLoaded(List<Broker> brokers, List<String> brokerKeys);
    }

    public interface DataStatusAssets {
        void DataIsLoaded(List<Asset> assets, List<String> assetKeys);
    }

    public interface DataStatusMessages {
        void DataIsLoaded(List<Message> messages, List<String> messageKeys);
    }

    public FirebaseDatabaseHelper(Query mReference){
        this.mReference = mReference;
    }

    public void readBrokers(final DataStatusBrokers dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                brokers.clear();
                List<String> brokerKeys = new ArrayList<>();
                for(DataSnapshot keyNode:snapshot.getChildren()){
                    brokerKeys.add(keyNode.getKey());
                    Broker broker = keyNode.getValue(Broker.class);
                    broker.setUid(keyNode.getKey());
                    brokers.add(broker);
                }
                dataStatus.DataIsLoaded(brokers, brokerKeys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void readAssets(final DataStatusAssets dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assets.clear();
                List<String> assetKeys = new ArrayList<>();
                for(DataSnapshot keyNode:snapshot.getChildren()){
                    assetKeys.add(keyNode.getKey());
                    Asset asset = keyNode.getValue(Asset.class);
                    assets.add(asset);
                }
                dataStatus.DataIsLoaded(assets, assetKeys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void readMessages(final DataStatusMessages dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                List<String> messageKeys = new ArrayList<>();
                for(DataSnapshot keyNode:snapshot.getChildren()){
                    messageKeys.add(keyNode.getKey());
                    Message message = keyNode.getValue(Message.class);
                    messages.add(message);
                }
                dataStatus.DataIsLoaded(messages, messageKeys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
