package com.capgemini.MusscheProject.repository;

import com.capgemini.MusscheProject.entities.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql("/insertMessage.sql")
class MessageRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String MESSAGES = "messages";
    private MessageRepository messageRepository;

    public MessageRepositoryTest(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

//    Boolean existsByTitle(String title);
//    Message findByTitle(String title);
//    List<Message> returnRandomMessage();

    @Test
    void existsByTitleWorksWithExistingTitle(){
        assertThat(messageRepository.existsByTitle("JunitTestTitle")).isTrue();
    }
    @Test
    void existsByTitleDoesntWorksWithFakeTitle(){
        assertThat(messageRepository.existsByTitle("JunitTestFakeTitle")).isFalse();
    }

    @Test
    void findByTitleWorksWithExistingTitle(){
        assertThat(messageRepository.findByTitle("JunitTestTitle").getMessage()).isEqualTo("junitTestMessage");
    }

    @Test
    void findByTitleDoesntWorksWithFakeTitle(){
        assertThat(messageRepository.findByTitle("JunitTestFakeTitle")).isNull();
    }

    @Test
    void findById(){
        assertThat(messageRepository.findById(idMessage()))
                .hasValueSatisfying(message -> assertThat(message.getMessage()).isEqualTo("junitTestMessage"));
    }

    @Test
    void returnRandomMessageWorks() {
        assertThat(messageRepository.returnRandomMessage()).hasSize(1);
        assertThat(messageRepository.returnRandomMessage()).isNotEqualTo(messageRepository.returnRandomMessage());
    }

    private long idMessage(){
        return jdbcTemplate.queryForObject("SELECT id FROM messages WHERE title = 'JunitTestTitle'", Long.class);
    }
}