import { Injectable } from '@angular/core';
import { Friend } from '../dto/Friend';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {


  private _selectedFriend:BehaviorSubject<any> = new BehaviorSubject<any>(null);
  public selectedFriend$ = this._selectedFriend.asObservable();

  constructor(){}

  public setSelectedFriend(friend:Friend){
    this._selectedFriend.next(friend);
  }
}
