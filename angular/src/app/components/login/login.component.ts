import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { User } from 'src/app/dto/User';
import {Router} from '@angular/router'

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user:User;

  incorrectEmail:boolean;
  incorrectPassword:boolean;
  errorMessage:string|null;

  constructor(private http: HttpClient, private router:Router) {
    this.user = new User();
    this.incorrectEmail = false;
    this.incorrectPassword = false;
    this.errorMessage = null;
  }

  loginForm():void{
    this.http.post<any>('http://localhost:8080/api/login',this.user)
    .subscribe({
      next:(response)=>{
        console.log('Login Successful!');
        localStorage.setItem('user', JSON.stringify(response));
        localStorage.setItem('parentUrl', `home/${response.id}`);
        this.router.navigate(['home',response.id]);
      },
      error:(error)=>{
        console.log('Login Failed', error);
        this.errorMessage = "Incorrect username or password. Please try again";
        this.incorrectEmail = true;
        this.incorrectPassword = true;
        this.router.navigate(['']);
      }
    })
  }

  navigateToSignUp(){
    this.router.navigate(['/signup']);
  }
}
