package com.brianmorales.chatapplication.controller;

import com.brianmorales.chatapplication.entity.UserEntity;
import com.brianmorales.chatapplication.model.MessageModel;
import com.brianmorales.chatapplication.services.ChatService;
import com.brianmorales.chatapplication.services.MessageService;
import com.brianmorales.chatapplication.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;


//    @PostMapping("/messages")
    @MessageMapping("/send")
    @SendTo("/topic/send")
    public void handleMessage(MessageModel message){
        message.setCreatedAt(LocalDateTime.now());
        Optional<UserEntity> user = userService.findByUserId(message.getSenderId());
        if(user.isPresent()){
            try {messageService.saveMessage(message);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/messages/{chatId}")
    public ResponseEntity<Optional<List<MessageModel>>> getChatMessages(@PathVariable String chatId){
        if(!chatService.chatExists(Long.parseLong(chatId))){
            return ResponseEntity.notFound().build();
        }
        Optional<List<MessageModel>> messages = messageService.getMessages(chatId);
        return ResponseEntity.ok(messages);
    }
}
