package com.brianmorales.chatapplication.model;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserModel {
    private Long id;

    private String username;

    private String password;

    private String email;

    private String imgUrl;
    private LocalDateTime createdAt;
}
