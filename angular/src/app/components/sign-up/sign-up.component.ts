import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { User } from 'src/app/dto/User';
import { Router } from '@angular/router';


@Component({
  selector: 'sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {
  newUser:User;
  imgUrl!:File;
  

  isNameTouched: boolean = false;
  isEmailTouched: boolean = false;
  isPasswordTouched: boolean = false;

  constructor(private http:HttpClient, private router:Router){
    this.newUser = new User();
  }

  onFileSelected(event:any){
    this.imgUrl = event.target.files[0];
    
  }

  submitForm():void{

    const formData = new FormData();
    formData.append('username', this.newUser.username);
    formData.append('email', this.newUser.email);
    formData.append('password', this.newUser.password);
    formData.append('imgUrl', this.imgUrl);

    this.http.post('http://localhost:8080/api/signup', formData)
    .subscribe({
      next: () => {
        console.log('User created successfully!');
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.error('Error creating user:', error);
        
      }
    });
  }

  onNameBlur(){
    this.isNameTouched = true;
  }
  onPasswordBlur(){
    this.isPasswordTouched = true;
  }
  onEmailBlur(){
    this.isEmailTouched = true;
  }
  isFieldInvalid(field:string):boolean{
    switch(field){
      case 'username':
        return this.isNameTouched && !this.newUser.username;
        case 'email':
        return this.isEmailTouched && !this.newUser.email;
        case 'password':
        return this.isPasswordTouched && !this.newUser.password;
      default:
        return false;
    }
  }
}
