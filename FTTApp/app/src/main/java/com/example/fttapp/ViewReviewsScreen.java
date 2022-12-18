package com.example.fttapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewReviewsScreen extends AppCompatActivity {

    Query reviewQuery, markQuery;
    RecyclerView mRecyclerView;
    TextView myAverageMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reviews_screen);

        mRecyclerView = findViewById(R.id.recyclerReviews);
        myAverageMark = (TextView) findViewById((R.id.appMarkingChange));

        reviewQuery = FirebaseDatabase.getInstance().getReference("reviews/list");
        markQuery = FirebaseDatabase.getInstance().getReference("reviews");
        new FirebaseDatabaseHelper(reviewQuery).readReviews((reviews, keys) -> new RecyclerViewConfig().setConfigReview(mRecyclerView, ViewReviewsScreen.this, reviews, keys));
        markQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String averageMark = snapshot.child("mark").getValue(String.class);
                    myAverageMark.setText(averageMark);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
