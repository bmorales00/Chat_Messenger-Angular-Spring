package com.brianmorales.chatapplication.controller;


import com.brianmorales.chatapplication.entity.UserEntity;
import com.brianmorales.chatapplication.model.ReceiverModel;
import com.brianmorales.chatapplication.services.ChatService;
import com.brianmorales.chatapplication.services.MessageService;
import com.brianmorales.chatapplication.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;



    @PostMapping(value = "/new_chat", produces = "application/json")
    public ResponseEntity<String> startChat(@RequestParam("sender_email") String senderEmail,@RequestParam("email") String email){
        Optional<UserEntity> sender = userService.findByEmail(senderEmail);
        if(chatService.chatExists(sender, email)){
            return ResponseEntity.ok("{\"message\": \"Chat already exists\"}");
        }
        if(sender.isPresent()){
            ReceiverModel friend = chatService.newConversation(sender, email);
            return ResponseEntity.ok("{\"message\": \"Chat registered successfully\"}");
        }
        return ResponseEntity.notFound().build();
    }
}
