package com.capgemini.MusscheProject.repository;

import com.capgemini.MusscheProject.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long> {
    Boolean existsByTitle(String title);
    Message findByTitle(String title);
    @Query("SELECT m FROM Message m ORDER BY rand() LIMIT 1")
    List<Message> returnRandomMessage();
}
