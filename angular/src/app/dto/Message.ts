
export class Message{
    private senderId:string;
    private chatId:string;
    private content:string;

    constructor(senderId:string, chatId:string, content:string){
        this.senderId = senderId;
        this.chatId = chatId;
        this.content = content;
    }

    public setChatId(chatId:string){
        this.chatId = chatId;
    }
    public setContent(content:string){
        this.content = content;
    }
    public getSenderId():string{
        return this.senderId;
    }
    public getChatId():string{
        return this.chatId;
    }
    public getContent():string{
        return this.content;
    }
    
}