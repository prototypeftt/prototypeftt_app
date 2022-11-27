package com.example.fttapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Asset implements Parcelable {
    private String assetName, assetPrediction, closePrice, predictedPrice, reportPDF, assetCategory, Qty, assetId;

    public Asset() {
    }

    public Asset(String name, String prediction, String price, String predictedPrice, String reportPDF, String assetCategory, String Qty, String assetId) {
        this.assetName = name;
        this.assetPrediction = prediction;
        this.closePrice = price;
        this.predictedPrice = predictedPrice;
        this.reportPDF = reportPDF;
        this.assetCategory = assetCategory;
        this.Qty = Qty;
        this.assetId = assetId;
    }

    protected Asset(Parcel in) {
        assetName = in.readString();
        assetPrediction = in.readString();
        closePrice = in.readString();
        predictedPrice = in.readString();
        reportPDF = in.readString();
        assetCategory = in.readString();
        Qty = in.readString();
        assetId = in.readString();
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

    public String getPredictedPrice() {
        return predictedPrice;
    }

    public String getReportPDF() { return reportPDF; }

    public String getAssetCategory() {
        return assetCategory;
    }

    public String getQty() {
        return Qty;
    }

    public String getAssetId() { return assetId; }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setAssetPrediction(String assetPrediction) {
        this.assetPrediction = assetPrediction;
    }

    public void setClosePrice(String closePrice) {
        this.closePrice = closePrice;
    }

    public void setPredictedPrice(String predictedPrice) {
        this.predictedPrice = predictedPrice;
    }

    public void setReportPDF(String reportPDF) {
        this.reportPDF = reportPDF;
    }

    public void setAssetCategory(String assetCategory) {
        this.assetCategory = assetCategory;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
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
        parcel.writeString(predictedPrice);
        parcel.writeString(reportPDF);
        parcel.writeString(assetCategory);
        parcel.writeString(Qty);
        parcel.writeString(assetId);
    }
}
