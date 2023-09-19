import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Client,Stomp,StompConfig} from '@stomp/stompjs';
import { BehaviorSubject , Observable ,EMPTY} from 'rxjs';
import { Message } from '../dto/Message';


@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private stompClient?:Client;
  private messageSubject?:BehaviorSubject<any> = new BehaviorSubject<any>(null);

  constructor(private httpClient: HttpClient) { }

  public connect(){
    const stompConfig:StompConfig ={
      brokerURL:`ws://localhost:8080/ws`,
      debug:(str) =>{
        console.log(str)
      },
      reconnectDelay:5000,
      heartbeatIncoming:4000,
      heartbeatOutgoing:4000
    }

    this.stompClient = new Client(stompConfig);
    this.stompClient.onConnect=(frame)=>{
      console.log('websocket connected',frame);
      this.stompClient?.subscribe('/topic/message', (message)=>{
        const messageBody:Message = JSON.parse(message.body)
        this.messageSubject?.next(messageBody);
        // console.log('messagesubject = ', this.messageSubject)
      })

    }
    this.stompClient.onWebSocketError = (event) => {
      console.error('WebSocket error:', event);
    };
    this.stompClient.activate();
  }


  public disconnect():void{
    if(this.stompClient){
      this.stompClient.deactivate();
      console.log("WebSocket connection closed")
    }
  }


  public sendMessage(message: Message):void{
    this.stompClient?.publish({
      destination: '/app/send',
      body:JSON.stringify(message)
    });
  }

  public getMessages(chadId:String):Observable<any>{
    return this.httpClient.get(`http://localhost:8080/api/messages/${chadId}`)
  }

  public getRealTimeMessages():Observable<Message>{
    if(!this.messageSubject){
      return EMPTY
    }
    //console.log(this.messageSubject)
    return this.messageSubject?.asObservable();
  }
}

// this.stompClient?.subscribe(`/topic/message`, (message) => {
//   console.log('THIS IS THE MESSAGE OBJECT: ', message.body)
//   this.messageSubject?.next(JSON.parse(message.body));
//   console.log(this.messageSubject)
// })