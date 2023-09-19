package com.brianmorales.chatapplication.services;

import com.brianmorales.chatapplication.entity.ChatEntity;
import com.brianmorales.chatapplication.entity.UserEntity;
import com.brianmorales.chatapplication.exception.UserNotFoundException;
import com.brianmorales.chatapplication.model.ReceiverModel;
import com.brianmorales.chatapplication.repositories.ChatRepository;
import com.brianmorales.chatapplication.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class ChatService {

    //@Autowired
    //private  SimpMessagingTemplate simpMessageTemplate;

    @Autowired
    private  ChatRepository chatRepository;
    @Autowired
    private  UserRepository userRepository;


    public boolean chatExists(Optional<UserEntity> user1, String email){
        Optional<UserEntity> user2 = userRepository.findByEmail(email);
        String user1ID = user1.get().getId().toString();
        String user2ID = user2.get().getId().toString();

        Optional<ChatEntity> chat = chatRepository.findByUser1IdAndUser2Id(user1ID, user2ID);
        if(chat.isEmpty()){
            chat = chatRepository.findByUser1IdAndUser2Id(user2ID,user1ID);
        }
        return chat.isPresent();
    }
    public ReceiverModel newConversation(Optional<UserEntity> user1, String email) {
        Optional<UserEntity> user2 = userRepository.findByEmail(email);
        if(user2.isEmpty()){
            throw new UserNotFoundException("User either does not exist or cannot be found with email: "+ email);
        }
        String user1Id = user1.get().getId().toString();
        String user2Id = user2.get().getId().toString();
        chatRepository.save(new ChatEntity(null, user1Id, user2Id, LocalDateTime.now()));
        return new ReceiverModel(user2.get().getId(), user2.get().getEmail(), user2.get().getUsername(), user2.get().getImgUrl());
    }

    public boolean chatExists(Long chatId) {
        return chatRepository.findById(chatId).isPresent();
    }

}
/*
ptional<UserEntity> friendOp = userRepository.findByEmail(email);
        //UserEntity friend = friendOp.get();
        friendOp.ifPresent(userEntity -> chatRepository.findByUser1AndUser2(user.get().getId().toString(),
                userEntity.getId().toString(),
                friendOp.get().getId().toString(),
                user.get().getId().toString()).ifPresentOrElse(chat -> {
        }, () -> {
            ChatEntity newChat = chatRepository.save(new ChatEntity(null, user.get().getId().toString(), friendOp.get().getId().toString(), null));
            //simpMessageTemplate.convertAndSend("/notifications/" + friendOp.get().getId(), new ReceiverModel(newChat.getId(), friendOp.get().getEmail(), friendOp.get().getUsername(), friendOp.get().getImgUrl()));
        }));
        return new ReceiverModel(friendOp.get().getId(), friendOp.get().getEmail(), friendOp.get().getUsername(), friendOp.get().getImgUrl());
 */