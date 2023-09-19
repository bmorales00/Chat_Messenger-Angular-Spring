import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { AutoResizeDirective } from './directive/auto-resize.directive';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { NbThemeModule, NbLayoutModule, NbMenuModule, NbDialogModule,NbCardModule, NbListModule, NbUserModule, NbIconModule, NbContextMenuModule, NbToastrModule} from '@nebular/theme';
import { ChatdetailComponent } from './components/home/chatdetail/chatdetail.component';
import { ProfiledetailComponent } from './components/home/profiledetail/profiledetail.component';
import { ChatListComponent } from './components/home/chat-list/chat-list.component';
import { ChatService } from './services/chat.service';

@NgModule({
  declarations: [
    AppComponent,
    AutoResizeDirective,
    SignUpComponent,
    LoginComponent,
    HomeComponent,
    ChatdetailComponent,
    ProfiledetailComponent,
    ChatListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NbThemeModule.forRoot({name:'default'}),
    NbLayoutModule,
    NbDialogModule,
    NbMenuModule.forRoot(),
    NbCardModule,
    NbListModule,
    NbUserModule,
    NbIconModule,
    NbContextMenuModule,
    NbToastrModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [ChatService],
  bootstrap: [AppComponent]
})
export class AppModule { }
