package com.capgemini.MusscheProject.service.interfaces;

public interface MessageService {
    String saveMessage(String title, String message);
    Boolean messageExists(String title);
    String getRandomMessage();
}
