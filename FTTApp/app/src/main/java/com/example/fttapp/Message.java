package com.example.fttapp;

public class Message {
    private String message, name, uuid;

    public Message() {
    }

    public Message(String message, String name, String uuid) {
        this.message = message;
        this.name = name;
        this.uuid = uuid;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }
}
