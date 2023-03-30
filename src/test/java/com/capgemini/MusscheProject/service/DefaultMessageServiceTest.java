package com.capgemini.MusscheProject.service;

import com.capgemini.MusscheProject.entities.Message;
import com.capgemini.MusscheProject.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultMessageServiceTest {
    private DefaultMessageService messageService;
    private Message message;
    private Message message2;

    @Mock
    private MessageRepository messageRepository;

    @BeforeEach
    void beforeEach() {
        messageService = new DefaultMessageService(messageRepository);
        message = new Message("junitTestTitle", "junitTestMessage");
        message2 = new Message("junitTestTitle2", "junitTestMessage2");
    }

    @Test
    void saveMessageWorksCorrectWhenTitleExists() {
        when(messageRepository.existsByTitle("junitTestTitle")).thenReturn(true);
        when(messageRepository.save(any(Message.class))).thenAnswer(i -> i.getArguments()[0]);
        when(messageRepository.findByTitle("junitTestTitle")).thenReturn(message);

        String response = messageService.saveMessage("junitTestTitle", "junitTestMessage");

        verify(messageRepository).existsByTitle("junitTestTitle");
        verify(messageRepository).save(message);

        assertThat(message.getMessage()).isEqualTo("junitTestMessage");
        assertThat(message.getCreated().getYear()).isEqualTo(LocalDateTime.now().getYear());
        assertThat(message.getCreated().getMonthValue()).isEqualTo(LocalDateTime.now().getMonthValue());
        assertThat(message.getCreated().getDayOfYear()).isEqualTo(LocalDateTime.now().getDayOfYear());
        assertThat(message.getCreated().getHour()).isEqualTo(LocalDateTime.now().getHour());
        assertThat(message.getCreated().getMinute()).isEqualTo(LocalDateTime.now().getMinute());
        assertThat(message.getVersion()).isEqualTo(2);
        assertThat(response).isEqualTo("title existed, message overwritten");
    }

    @Test
    void saveMessageWorksWithNewTitle() {
        when(messageRepository.existsByTitle("junitTestTitle")).thenReturn(false);
        when(messageRepository.save(any(Message.class))).thenAnswer(i -> i.getArguments()[0]);

        String response = messageService.saveMessage("junitTestTitle", "junitTestMessage");
        assertThat(response).isEqualTo("added message");

        verify(messageRepository).save(any(Message.class));
    }

    @Test
    void messageExists() {
        messageService.messageExists("junitTestTitle");
        verify(messageRepository).existsByTitle("junitTestTitle");
    }

    @Test
    void getRandomMessage() {
        when(messageRepository.returnRandomMessage()).thenReturn(List.of(message2));
        String response = messageService.getRandomMessage();
        verify(messageRepository).returnRandomMessage();
        assertThat(response).isEqualTo("junitTestMessage2");
    }
}