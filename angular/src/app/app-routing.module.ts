import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { ChatdetailComponent } from './components/home/chatdetail/chatdetail.component';
import { ChatListComponent } from './components/home/chat-list/chat-list.component';


const routes: Routes = [
  {path:'', redirectTo: '/login', pathMatch: 'full'},
  {path:'login', component: LoginComponent},
  {path:'signup', component: SignUpComponent},
  {path: 'home/:id', component:HomeComponent, children:[
    {path:'chat/:id', component:ChatdetailComponent},
    //{path: 'chat', component: ChatdetailComponent}
  ]},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
