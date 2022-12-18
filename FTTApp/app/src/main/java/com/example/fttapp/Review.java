package com.example.fttapp;

public class Review {
    private String reviewText, reviewMarks;

    public Review() {
    }

    public Review(String reviewText, String reviewNumber) {
        this.reviewText = reviewText;
        this.reviewMarks = reviewNumber;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setReviewMarks(String reviewMarks) {
        this.reviewMarks = reviewMarks;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getReviewMarks() {
        return reviewMarks;
    }
}
