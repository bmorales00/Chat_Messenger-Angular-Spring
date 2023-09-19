package com.brianmorales.chatapplication.entity.mapper;
import com.brianmorales.chatapplication.entity.UserEntity;
import com.brianmorales.chatapplication.model.UserModel;
public class UserMapper {
    public static UserEntity from(UserModel user){
        return new UserEntity(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getImgUrl(),
                user.getCreatedAt());
    }
    public static UserModel to(UserEntity user){
        return new UserModel(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getImgUrl(),
                user.getCreatedAt());
    }
}
