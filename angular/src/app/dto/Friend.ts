
import { SafeUrl } from '@angular/platform-browser';

export class Friend{

    private _chatId!:string; 
    private _username!:string;
    private _email!:string;
    private _imgUrl!:SafeUrl;

    constructor(chatId:string, username:string, email:string,imgUrl:SafeUrl){
        this._chatId = chatId;
        this._username = username;
        this._email = email;
        this._imgUrl = imgUrl;
       
    }

    public get chatId(){
        return this._chatId;
    }
    public get username(){
        return this._username;
    }
    public get email(){
        return this._email;
    }
    public get imgUrl(){
        return this._imgUrl;
    }

}