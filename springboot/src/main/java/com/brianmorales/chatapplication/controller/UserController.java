package com.brianmorales.chatapplication.controller;


import com.brianmorales.chatapplication.entity.ChatEntity;
import com.brianmorales.chatapplication.entity.UserEntity;

import com.brianmorales.chatapplication.model.UserModel;

import com.brianmorales.chatapplication.services.FileStorageService;
import com.brianmorales.chatapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private FileStorageService fileStorageService;


    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestParam("username") String username,
                                             @RequestParam("email") String email,
                                             @RequestParam("password") String password,
                                             @RequestParam(value = "imgUrl", required = false) MultipartFile imgUrl){

        UserModel newUser =new UserModel();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);

        if(imgUrl !=null && !imgUrl.isEmpty()){
            newUser.setImgUrl(fileStorageService.storeProfilePicture(imgUrl));
        }
        if( newUser.getUsername() == null || newUser.getPassword() ==null){
            return ResponseEntity.badRequest().body("Invalid  UserModel Data");
        }
        userService.saveUser(newUser);

        return ResponseEntity.ok("{\"message\": \"User registered successfully\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<UserEntity>> login(@RequestBody UserModel userRequest){

        boolean isExistingUser = userService.existsByEmail(userRequest.getEmail());
        if(!isExistingUser){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Optional<UserEntity> existingUser = userService.findByEmail(userRequest.getEmail());
        if(existingUser.isPresent() && !existingUser.get().getPassword().equals(userRequest.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(existingUser);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> fetchImage(@PathVariable String fileName){
        Resource imgResource = fileStorageService.fetchImage(fileName);
        if(imgResource.exists() && imgResource.isReadable()){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imgResource);
        }
        else{
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{email}/chats")
    public List<ChatEntity> getUserChats(@PathVariable String email){
        Optional<UserEntity> user = userService.findByEmail(email);
        return userService.findChatByUserId((user.get().getId().toString()));
    }

    @GetMapping(value = "/{friendId}/friendProfile", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<UserEntity> getFriendProfile(@PathVariable String friendId){
        return userService.findFriendById(friendId);
    }
}
