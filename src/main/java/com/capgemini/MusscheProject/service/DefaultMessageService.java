package com.capgemini.MusscheProject.service;

import com.capgemini.MusscheProject.entities.Message;
import com.capgemini.MusscheProject.repository.MessageRepository;
import com.capgemini.MusscheProject.service.interfaces.MessageService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DefaultMessageService implements MessageService {
    private final MessageRepository messageRepository;

    public DefaultMessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public String saveMessage(String title, String message) {
        if(messageRepository.existsByTitle(title)){
            Message messageToEdit = messageRepository.findByTitle(title);
            messageToEdit.setMessage(message);
            messageToEdit.setLastAdjusted(LocalDateTime.now());
            messageToEdit.addVersion();
            messageRepository.save(messageToEdit);
            return "title existed, message overwritten";
        } else {
            messageRepository.save(new Message(title, message));
            return "added message";
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean messageExists(String title) {
        return messageRepository.existsByTitle(title);
    }

    @Override
    @Transactional(readOnly = true)
    public String getRandomMessage() {
        return messageRepository.returnRandomMessage().get(0).getMessage();
    }

    @Override
    public Message getMessageForCity(String title) {
        return messageRepository.findByTitle(title);
    }
}
