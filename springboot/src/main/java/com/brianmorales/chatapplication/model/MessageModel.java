package com.brianmorales.chatapplication.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class MessageModel {

    private Long id;
    private String senderId;
    private String chatId;
    private String content;
    private LocalDateTime createdAt;

}
