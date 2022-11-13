package com.example.fttapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Asset implements Parcelable {
    private String assetName, assetPrediction, closePrice;

    public Asset() {
    }

    public Asset(String name, String prediction, String price) {
        this.assetName = name;
        this.assetPrediction = prediction;
        this.closePrice = price;
    }

    protected Asset(Parcel in) {
        assetName = in.readString();
        assetPrediction = in.readString();
        closePrice = in.readString();
    }

    public static final Creator<Asset> CREATOR = new Creator<Asset>() {
        @Override
        public Asset createFromParcel(Parcel in) {
            return new Asset(in);
        }

        @Override
        public Asset[] newArray(int size) {
            return new Asset[size];
        }
    };

    public String getAssetPrediction() {
        return assetPrediction;
    }

    public String getClosePrice() {
        return closePrice;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setAssetPrediction(String assetPrediction) {
        this.assetPrediction = assetPrediction;
    }

    public void setClosePrice(String closePrice) {
        this.closePrice = closePrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(assetName);
        parcel.writeString(assetPrediction);
        parcel.writeString(closePrice);
    }
}
