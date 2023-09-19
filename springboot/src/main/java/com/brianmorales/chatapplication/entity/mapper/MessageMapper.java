package com.brianmorales.chatapplication.entity.mapper;

import com.brianmorales.chatapplication.entity.MessageEntity;
import com.brianmorales.chatapplication.model.MessageModel;

import java.util.stream.Collectors;

public class MessageMapper {
    public static MessageModel to(MessageEntity message){
        return  new MessageModel(
                message.getId(),
                message.getSenderId(),
                message.getChatId(),
                message.getContent(),
//                message.getFiles().stream().map(FileMapper::to).collect(Collectors.toList()),
//                message.getContentType(),
                message.getCreatedAt());
    }
    public static MessageEntity from(MessageModel message){
        return  new MessageEntity(message.getId(),
                message.getSenderId(),
                message.getChatId(),
                message.getContent(),
//                null,
//                message.getContentType(),
                message.getCreatedAt());
    }
}
