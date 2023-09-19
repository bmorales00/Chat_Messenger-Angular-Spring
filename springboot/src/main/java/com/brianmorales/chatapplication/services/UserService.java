package com.brianmorales.chatapplication.services;


import com.brianmorales.chatapplication.entity.ChatEntity;
import com.brianmorales.chatapplication.entity.UserEntity;
import com.brianmorales.chatapplication.entity.mapper.UserMapper;
import com.brianmorales.chatapplication.model.UserModel;
import com.brianmorales.chatapplication.repositories.ChatRepository;
import com.brianmorales.chatapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    public UserModel saveUser(UserModel userModel){
        return UserMapper.to(userRepository.save(UserMapper.from(userModel)));
        //userRepository.save(userModel);
    }

    public Optional<UserEntity>findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }


    public List<ChatEntity> findChatByUserId(String userId){
        return chatRepository.findByUserId(userId);
    }

    public Optional<UserEntity> findByUserId(String userId){
        return userRepository.findById(Long.parseLong(userId));
    }

    public Optional<UserEntity> findFriendById(String friendId){
        Optional<UserEntity> friend = userRepository.findById(Long.parseLong(friendId));
        return userRepository.findById(Long.parseLong(friendId));
    }


}
