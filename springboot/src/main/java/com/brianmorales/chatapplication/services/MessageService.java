package com.brianmorales.chatapplication.services;


import com.brianmorales.chatapplication.entity.ChatEntity;
import com.brianmorales.chatapplication.entity.MessageEntity;
import com.brianmorales.chatapplication.entity.mapper.MessageMapper;
import com.brianmorales.chatapplication.model.MessageModel;
import com.brianmorales.chatapplication.repositories.ChatRepository;
import com.brianmorales.chatapplication.repositories.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MessageService {


    @Autowired
    private SimpMessagingTemplate simpMessageTemplate;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(MessageModel message) throws Exception {
        Optional<ChatEntity> chat = chatRepository.findById(Long.parseLong(message.getChatId()));
        if(chat.isEmpty()){
            throw new Exception("Chat does not exist");
        }
        if(chat.get().getUser1Id().equals(message.getSenderId()) || chat.get().getUser2Id().equals(message.getSenderId())){
            messageRepository.save(MessageMapper.from(message));
            simpMessageTemplate.convertAndSend("/topic/message", message);
            return;
        }
        throw new Exception("Resource Not found");
    }

    public Optional<List<MessageModel>> getMessages(String chatId){
        Optional<List<MessageEntity>> eMessages = messageRepository.findAllByChatId(chatId);
        List<MessageModel> messages = new ArrayList<>();
        if(eMessages.isPresent()){
            for(MessageEntity m : eMessages.get()){
                messages.add(MessageMapper.to(m));
            }
        }
        return Optional.of(messages);
    }

    /*
    Optional<ChatEntity> chat = chatRepository.findById(Long.parseLong(chatId));
        if(chat.isEmpty()){
            throw new Exception("Chat does not exist");
        }
        if(chat.get().getUser1Id().equals(senderId) || chat.get().getUser2Id().equals(senderId)){
            MessageModel msg = new MessageModel(null, senderId, chatId, content, LocalDateTime.now());
            messageRepository.save(MessageMapper.from(msg));

            simpMessageTemplate.convertAndSend("/notifications/${if (it.user1 == userId) it.user2 else it.user1}",
            "USER_MESSAGE_ADDED");
            return msg;
        }
        throw new Exception("Resource Not found");
     */


//    public List<MessageModel> getMessageByChat(String chatId){
//        List<MessageModel> msgs = new ArrayList<>();
//        chatRepository.findById(Long.parseLong(chatId)).stream().forEach(chat ->{
//            if(chat.getUser1Id().equals(chatId) || chat.getUser2Id().equals(chatId)){
//                Optional<List<MessageEntity>> msgsEntity = messageRepository.findAllByChatId(chatId);
//                msgsEntity.ifPresent(m -> {
//                    for(MessageEntity message: m){
//                        msgs.add(MessageMapper.to(message));
//                    }
//                });
//            }
//        });
//
//        return msgs;
//    }




//    public List<MessageModel> getAllMessages(List<Long> id, String userId) {
//        List<MessageModel> msgs = new ArrayList<>();
//        chatRepository.findAllById(id).forEach(chat ->  {
//            if(!chat.getUser1Id().equals(userId) && !chat.getUser2Id().equals(userId)){
//                return;
//            }
//            messageRepository.findAllByChatId(chat.getId().toString()).get().forEach(msg ->{
//                msgs.add(MessageMapper.to(msg));
//            });
//        });
//        return msgs;
//    }
}
