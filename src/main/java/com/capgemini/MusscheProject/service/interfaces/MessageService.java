package com.capgemini.MusscheProject.service.interfaces;

import com.capgemini.MusscheProject.entities.Message;

public interface MessageService {
    String saveMessage(String title, String message);
    Boolean messageExists(String title);
    String getRandomMessage();

    Message getMessageForCity(String title);
}
