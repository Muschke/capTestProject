package com.capgemini.MusscheProject.controller;

import com.capgemini.MusscheProject.payload.IncomingMessage;
import com.capgemini.MusscheProject.service.interfaces.ApiService;
import com.capgemini.MusscheProject.service.interfaces.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;

    }

    @GetMapping("/randommessage")
    public String getMessage(){
        return messageService.getRandomMessage();
    }

    @PostMapping("/addMessage")
    public ResponseEntity<String> addMessage(@RequestBody IncomingMessage incomingMessage){
        String title = incomingMessage.getTitle();
        String message = incomingMessage.getMessage();

        if(message.equals(" ") || message.isEmpty() || message.isBlank()){
            return ResponseEntity.badRequest().body(new String("invalid message"));
        }
        if(title.equals(" ") || title.isEmpty() || title.isBlank()){
            return ResponseEntity.badRequest().body(new String("invalid title"));
        }

        String response = messageService.saveMessage(title, message);
        return ResponseEntity.ok(response);
    }



}
