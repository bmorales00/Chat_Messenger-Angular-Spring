import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/dto/User';
import { NbToastrService } from '@nebular/theme';
@Component({
  selector: 'app-profiledetail',
  templateUrl: './profiledetail.component.html',
  styleUrls: ['./profiledetail.component.css']
})
export class ProfiledetailComponent {

  user!:User;
  email:string = '';
  constructor(private http: HttpClient, private router:Router, private toastrService:NbToastrService){}

  ngOnInit():void{
    const user = localStorage.getItem('user');
    
    if(user){
      this.user= JSON.parse(user);
    }
  }
  submit(): void{
    const form = new FormData();
    form.append('email',this.email);
    form.append('sender_email', this.user.email);

    this.http.post('http://localhost:8080/api/new_chat', form)
    .subscribe({
      next: () => {
        console.log("Chat created successfully!");
        
      },
      error:(error) =>{
        if(this.email){
          this.toastrService.danger('Email not found. Please enter a valid email', 'Error',{
            duration: 3000, 
            preventDuplicates: true, 
            destroyByClick: true, 
            hasIcon: true, 
          })
        }
        console.error('Error while creating new chat' , error);
      }
    });
    
  }
}
