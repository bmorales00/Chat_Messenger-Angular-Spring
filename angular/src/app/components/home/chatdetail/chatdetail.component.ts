import { WebsocketService } from './../../../services/websocket.service';
import { Friend } from '../../../dto/Friend';
import { Component, Input, OnInit} from '@angular/core';
import {  SafeUrl } from '@angular/platform-browser';
import { User } from 'src/app/dto/User';
import { UserService } from 'src/app/services/user.service';
import { ChatService } from 'src/app/services/chat.service';
import { Message } from 'src/app/dto/Message';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-chatdetail',
  templateUrl: './chatdetail.component.html',
  styleUrls: ['./chatdetail.component.css']
})
export class ChatdetailComponent implements OnInit {
  @Input() messages:any[] = [];
  newMessage:string ='';
  
  user!:User;
  imgUrl!:SafeUrl;

  friend!:Friend;
  img!:string;

  nMessage!:Message;

  rlMessages!:Subscription;

  constructor(public userService:UserService, private chatService:ChatService, private webSocketService:WebsocketService) {
    this.rlMessages = new Subscription();
  }

  ngOnInit():void{
    
    const userJSON = localStorage.getItem('user');
    if(userJSON){
      const user = JSON.parse(userJSON); 
      this.user = user;
      this.nMessage = new Message(user.id, '', '');
    }
    this.chatService.selectedFriend$.subscribe((friend)=>{
      if(friend){
        this.friend = friend;
        this.img = this.friend.imgUrl.toString();
        this.webSocketService.connect();
        this.webSocketService.getMessages(this.friend.chatId).subscribe((message)=>{
           this.messages = message;
           console.log(this.messages);      
        });
        this.rlMessages=this.webSocketService.getRealTimeMessages().subscribe((message:any)=>{
          if(message && message.senderId != this.nMessage.getSenderId()){
            console.log('Testing again? I think: ', message)
            this.messages.push(message);
            
          }
        })
      }
    })
    this.webSocketService.disconnect();

  }

  ngOnDestroy():void {
    this.rlMessages.unsubscribe();
  }
  sendMessage():void{
    if(this.newMessage.trim() != ''){
      this.nMessage.setContent(this.newMessage);
      this.nMessage.setChatId(this.friend.chatId);
      this.webSocketService.sendMessage(this.nMessage);
      this.messages.push(this.nMessage);
      this.newMessage = '';

    }
  }

}
