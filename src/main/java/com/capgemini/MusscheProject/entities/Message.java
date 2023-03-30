package com.capgemini.MusscheProject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String message;
    @NotNull
    private int version;
    @NotNull
    private LocalDateTime created;
    private LocalDateTime lastAdjusted;

    public Message(String title, String message) {
        this.title = title;
        this.message = message;
        this.created = LocalDateTime.now();
        this.version = 1;
    }

    protected Message(){}

    public void addVersion() {
        this.version += 1;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setLastAdjusted(LocalDateTime lastAdjusted) {
        this.lastAdjusted = lastAdjusted;
    }

    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }
    public int getVersion() {
        return version;
    }
    public LocalDateTime getCreated() {
        return created;
    }
    public LocalDateTime getLastAdjusted() {
        return lastAdjusted;
    }
}
