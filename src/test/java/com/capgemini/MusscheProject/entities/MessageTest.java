package com.capgemini.MusscheProject.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    private Message message;
    @BeforeEach
    void setUp() {
        message = new Message("junitTestTitle", "junitTestMessage");
    }

    @Test
    void createdDateIsSet(){
        assertThat(message.getCreated().isEqual(LocalDateTime.now()));
    }

    @Test
    void addVersionWorks() {
        assertThat(message.getVersion()).isEqualTo(1);
        message.addVersion();
        assertThat(message.getVersion()).isEqualTo(2);
        message.addVersion();
        assertThat(message.getVersion()).isEqualTo(3);
    }
}