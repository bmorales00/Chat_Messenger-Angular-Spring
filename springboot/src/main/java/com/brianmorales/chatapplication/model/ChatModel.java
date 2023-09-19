package com.brianmorales.chatapplication.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ChatModel {
    private Long id;

    private String user1;

    private String user2;

    private LocalDateTime createdAt;
}
