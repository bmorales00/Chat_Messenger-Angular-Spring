import { NbMenuService , NbMenuBag} from '@nebular/theme';

import { SafeUrl } from '@angular/platform-browser';
import { Component, OnInit} from '@angular/core';
import { ChatListService } from 'src/app/services/chat-list.service';
import {ChatService} from 'src/app/services/chat.service';

import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';


import { User } from 'src/app/dto/User';
import { Friend } from 'src/app/dto/Friend';

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styleUrls: ['./chat-list.component.css']
})
export class ChatListComponent implements OnInit {
  user!:User;
  userChats: any[] = [];
  userFriends:Friend[] = [];
  userId!:string;
  userPP!:SafeUrl;
  items = [{title:'Log Out'}];

  
 

  constructor(private chatListService: ChatListService, public userService: UserService,private chatService:ChatService ,private router: Router, private nbMenuService:NbMenuService) {}
  
  ngOnInit(): void {
    this.toggleMenu();
    const userJson = localStorage.getItem('user');
    if(!userJson){return;}
    const user =JSON.parse(userJson);
    this.user = user;
    this.userId = user.id;
    //console.log(this.user);
    this.getProfilePicture(user.imgUrl);

    this.chatListService.getUserChats(this.user.email).subscribe(chats =>{
      this.userChats = chats;
      this.populateFriends();
      // console.log(this.userFriends);      
    })
    
  }

  populateFriends():void{
    for(const chat of this.userChats){
      const friendId = chat.user1Id == this.userId? chat.user2Id:chat.user1Id;
      this.chatListService.getFriendProfile(friendId).subscribe(friend => {
        this.userService.fetchImg(friend.imgUrl).subscribe({
          next:(response)=>{
            this.userFriends.push(new Friend(chat.id, friend.username, friend.email, response));
          },
          error:(error) => {
            console.error("Error", error);
          }
        });
      });
    }
  }

  currentChat(chatId:String, friend:Friend){
    const parentUrl = localStorage.getItem('parentUrl');
    this.chatService.setSelectedFriend(friend);
    this.router.navigate([parentUrl+'/chat',chatId]);
  }


  getProfilePicture(imgUrl:string):void{
    this.userService.fetchImg(imgUrl).subscribe({
      next:(response)=>{
        this.userPP = response;
      },
      error:(error)=>{
        console.error("Error has occured");
      }
    });
  }

  toggleMenu(){
    this.nbMenuService.onItemClick().subscribe((menuItem:NbMenuBag)=>{
      if(menuItem.item.title === 'Log Out'){
        this.RequestLogOut();
      }
    });
  }

  RequestLogOut(){
    this.router.navigate(['login'])
  }
}
