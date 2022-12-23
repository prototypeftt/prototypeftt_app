package com.example.fttapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewScreen extends AppCompatActivity {

    private EditText EditNumericalReview, EditTextReview;
    DatabaseReference reviewReference;
    Review review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_screen);

        EditNumericalReview = findViewById(R.id.numericalReview);
        EditTextReview = findViewById(R.id.tellExperience);

        reviewReference = FirebaseDatabase.getInstance().getReference("reviews/list");
        review = new Review();

        changeActivity();
    }

    private void changeActivity(){
        Button sendReview = findViewById(R.id.sendReviewButton);
        sendReview.setOnClickListener(view -> createReview());
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        if (length > 1) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private void createReview(){
        String numReview = EditNumericalReview.getText().toString();
        String textReview = EditTextReview.getText().toString();

        if(numReview.isEmpty()){
            EditNumericalReview.setError("Numerical review is required");
            EditNumericalReview.requestFocus();
        }else if(textReview.isEmpty()){
            EditTextReview.setError("Review text is required");
            EditTextReview.requestFocus();
        }else if(!isInteger(numReview)){
            EditNumericalReview.setError("Number should be between 1 and 10");
            EditNumericalReview.requestFocus();
        }else{
            review.setReviewMarks(numReview);
            review.setReviewText(textReview);
            String keyId = reviewReference.push().getKey();
            reviewReference.child(keyId).setValue(review);
            startActivity(new Intent(ReviewScreen.this, MainActivity.class));
        }
    }
}