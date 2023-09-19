package com.brianmorales.chatapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name="messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="senderId")
    private String senderId;

    @Column(name="chatId")
    private String chatId;

    private String content;

//    @ElementCollection
//    private List<FileEntity> files;
//    @Enumerated(EnumType.STRING)
//    @Column(name="content_type")
//    private ContentType contentType;

    @Column(name="created_at")
    private LocalDateTime createdAt;
}
