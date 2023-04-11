package com.capgemini.MusscheProject.controller.integration;


import com.capgemini.MusscheProject.payload.IncomingMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerIntegrationTest /*extends AbstractTransactionalJUnit4SpringContextTests*/ {
    private final static String URI = "/message/";

    private ObjectMapper objectMapper;
    private final MockMvc mockMvc;

    private IncomingMessage incomingMessage;

    public MessageControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void setUp() {
        incomingMessage = new IncomingMessage("junitIntegrationTestTitle", "junitIntegrationTestMessage");
    }

    @Test
    void testGetMessage() throws Exception{
        mockMvc.perform(get(URI+"randommessage"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    void addMessageWithCorrectMessageWorks() throws Exception {
        mockMvc.perform(post(URI + "addMessage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incomingMessage)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    void addMessageWithInvalidTitleIsRejected() throws Exception{
        IncomingMessage incomingMessageInvalidTitle_1 = new IncomingMessage("", "junitTestMessage");
        IncomingMessage incomingMessageInvalidTitle_2 = new IncomingMessage(" ", "junitTestMessage");
        IncomingMessage incomingMessageInvalidTitle_3 = new IncomingMessage(new String(), "junitTestMessage");

        mockMvc.perform(post(URI+"addMessage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incomingMessageInvalidTitle_1)))
                .andExpect(status().isBadRequest());

        mockMvc.perform(post(URI+"addMessage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incomingMessageInvalidTitle_2)))
                .andExpect(status().isBadRequest());

        mockMvc.perform(post(URI+"addMessage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incomingMessageInvalidTitle_3)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addMessageWithInvalidMessageIsRejected() throws Exception{

        IncomingMessage incomingMessageInvalidMessage_1 = new IncomingMessage("junitTestTitle", "");
        IncomingMessage incomingMessageInvalidMessage_2 = new IncomingMessage("junitTestTitle", " ");
        IncomingMessage incomingMessageInvalidMessage_3 = new IncomingMessage("junitTestTitle", new String());

        mockMvc.perform(post(URI+"addMessage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incomingMessageInvalidMessage_1)))
                .andExpect(status().isBadRequest());

        mockMvc.perform(post(URI+"addMessage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incomingMessageInvalidMessage_2)))
                .andExpect(status().isBadRequest());

        mockMvc.perform(post(URI+"addMessage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incomingMessageInvalidMessage_3)))
                .andExpect(status().isBadRequest());
    }

}
