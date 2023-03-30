package com.capgemini.MusscheProject.controller;

import com.capgemini.MusscheProject.entities.Message;
import com.capgemini.MusscheProject.payload.IncomingMessage;
import com.capgemini.MusscheProject.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {
    private Message message;
    private MessageController messageController;
    private IncomingMessage incomingMessage;


    @Mock
    MessageService messageService;

    @BeforeEach
    void setUp() {
        messageController = new MessageController(messageService);
        incomingMessage = new IncomingMessage("junitTestTitle", "junitTestMessage");

    }

    @Test
    void getMessage() {
        messageController.getMessage();
        verify(messageService).getRandomMessage();
    }

    @Test
    void addMessageWithCorrectMessageWorks() {
        when(messageService.saveMessage(incomingMessage.getTitle(), incomingMessage.getMessage())).thenReturn("response inserted in body");

        ResponseEntity<String> response = messageController.addMessage(incomingMessage);

        verify(messageService).saveMessage(incomingMessage.getTitle(), incomingMessage.getMessage());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
        assertThat(response.getBody().toString()).isEqualTo("response inserted in body");
    }


    @Test
    void addMessageWithInvalidTitleIsRejected() {
        IncomingMessage incomingMessageInvalidTitle_1 = new IncomingMessage("", "junitTestMessage");
        IncomingMessage incomingMessageInvalidTitle_2 = new IncomingMessage(" ", "junitTestMessage");
        IncomingMessage incomingMessageInvalidTitle_3 = new IncomingMessage(new String(), "junitTestMessage");

        ResponseEntity<String> response = messageController.addMessage(incomingMessageInvalidTitle_1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(400));
        assertThat(response.getBody().toString()).isEqualTo("invalid title");

        ResponseEntity<String> response2 = messageController.addMessage(incomingMessageInvalidTitle_2);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(400));
        assertThat(response.getBody().toString()).isEqualTo("invalid title");

        ResponseEntity<String> response3 = messageController.addMessage(incomingMessageInvalidTitle_3);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(400));
        assertThat(response.getBody().toString()).isEqualTo("invalid title");
    }


    @Test
    void addMessageWithInvalidMessageIsRejected() {
        IncomingMessage incomingMessageInvalidMessage_1 = new IncomingMessage("junitTestTitle", "");
        IncomingMessage incomingMessageInvalidMessage_2 = new IncomingMessage("junitTestTitle", " ");
        IncomingMessage incomingMessageInvalidMessage_3 = new IncomingMessage("junitTestTitle", new String());

        ResponseEntity<String> response = messageController.addMessage(incomingMessageInvalidMessage_1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(400));
        assertThat(response.getBody().toString()).isEqualTo("invalid message");

        ResponseEntity<String> response2 = messageController.addMessage(incomingMessageInvalidMessage_2);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(400));
        assertThat(response.getBody().toString()).isEqualTo("invalid message");

        ResponseEntity<String> response3 = messageController.addMessage(incomingMessageInvalidMessage_3);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(400));
        assertThat(response.getBody().toString()).isEqualTo("invalid message");
    }

}