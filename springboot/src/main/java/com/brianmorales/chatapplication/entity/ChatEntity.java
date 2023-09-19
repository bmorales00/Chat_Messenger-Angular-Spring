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
@Table(name="chats")

public class ChatEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="user1Id")
    private String user1Id;

    @Column(name="user2Id")
    private String user2Id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

}
