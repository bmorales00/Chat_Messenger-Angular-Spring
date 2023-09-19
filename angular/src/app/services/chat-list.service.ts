import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatListService {

  constructor(private http: HttpClient) {}

  getUserChats( email: string): Observable<any[]>{
    return this.http.get<any[]>(`http://localhost:8080/api/${email}/chats`);
  }

  getFriendProfile(friendId:string):Observable<any>{
    return this.http.get<any>(`http://localhost:8080/api/${friendId}/friendProfile`)
  }

}
