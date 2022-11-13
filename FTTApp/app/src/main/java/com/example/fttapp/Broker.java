package com.example.fttapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Broker implements Parcelable {
    private String name, institution, premium, uid;

    public Broker() {
    }

    public Broker(String name) {
        this.name = name;
    }

    public Broker(String name, String institution, String premium, String uid) {
        this.name = name;
        this.institution = institution;
        this.premium = premium;
        this.uid = uid;
    }

    protected Broker(Parcel in) {
        name = in.readString();
        institution = in.readString();
        premium = in.readString();
        uid = in.readString();
    }

    public static final Creator<Broker> CREATOR = new Creator<Broker>() {
        @Override
        public Broker createFromParcel(Parcel in) {
            return new Broker(in);
        }

        @Override
        public Broker[] newArray(int size) {
            return new Broker[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getInstitution() {
        return institution;
    }

    public String getPremium() {
        return premium;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(institution);
        parcel.writeString(premium);
        parcel.writeString(uid);
    }
}
