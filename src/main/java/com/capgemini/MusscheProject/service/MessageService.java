package com.capgemini.MusscheProject.service;

public interface MessageService {
    String saveMessage(String title, String message);
    Boolean messageExists(String title);
    String getRandomMessage();
}
