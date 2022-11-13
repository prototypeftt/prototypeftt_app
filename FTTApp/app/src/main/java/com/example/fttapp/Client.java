package com.example.fttapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Client implements Parcelable {
    private String name, broker;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    protected Client(Parcel in) {
        name = in.readString();
        broker = in.readString();
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getBroker() {
        return broker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(broker);
    }
}
