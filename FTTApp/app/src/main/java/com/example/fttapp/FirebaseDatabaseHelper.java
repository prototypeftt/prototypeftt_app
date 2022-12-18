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
    private DatabaseReference reviewMarkReference;
    private List<Broker> brokers = new ArrayList<>();
    private List<Asset> assets = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    public interface DataStatusBrokers {
        void DataIsLoaded(List<Broker> brokers, List<String> brokerKeys);
    }

    public interface DataStatusAssets {
        void DataIsLoaded(List<Asset> assets, List<String> assetKeys);
    }

    public interface DataStatusMessages {
        void DataIsLoaded(List<Message> messages, List<String> messageKeys);
    }

    public interface DataStatusReviews {
        void DataIsLoaded(List<Review> reviews, List<String> reviewKeys);
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

    public void readReviews(final DataStatusReviews dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reviews.clear();
                float reviewMarks = 0;
                float markAverage = 0;
                int counter = 0;
                List<String> reviewKeys = new ArrayList<>();
                for(DataSnapshot keyNode:snapshot.getChildren()){
                    reviewKeys.add(keyNode.getKey());
                    Review review = keyNode.getValue(Review.class);
                    reviewMarks += Float.valueOf(review.getReviewMarks());
                    counter++;
                    reviews.add(review);
                }
                if(counter > 0){
                    markAverage = reviewMarks / counter;
                    String markAverageString = String.format("%.02f", markAverage);
                    reviewMarkReference = FirebaseDatabase.getInstance().getReference("reviews");
                    reviewMarkReference.child("mark").setValue(markAverageString);
                }
                dataStatus.DataIsLoaded(reviews, reviewKeys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
