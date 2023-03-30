package com.capgemini.MusscheProject.payload;

public class IncomingMessage {
    private String title;
    private String message;

    public IncomingMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
