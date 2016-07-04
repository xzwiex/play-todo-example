import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service/user.service';
import { GoogleSignIn } from '../google-sign-in/google-sign-in.component'
import {UserInfo} from "../../model/user.info";
import {NgIf, JsonPipe} from "@angular/common";

import GoogleUser = gapi.auth2.GoogleUser;

@Component({
  selector: 'my-app',
  templateUrl: './app.template.html',
  directives : [GoogleSignIn, NgIf],
  providers : [UserService],
  pipes : [JsonPipe]
})

export class AppComponent implements OnInit {

  userInfo : UserInfo = { authorized: false };

  user: GoogleUser;

  signInCallback : Function;

  constructor(private userService: UserService) {}

  ngOnInit(){

    console.debug('Init MyApp');

    this.signInCallback = this.onGoogleAuthorized.bind(this);

    this.getUserInfo();

  }

  private getUserInfo()  {
    this.userService.getUserInfo().subscribe( (userInfo: UserInfo) => this.handleUserInfo(userInfo), this.requestErrorHandler);
  }

  private handleUserInfo(userInfo):void {
    this.userInfo = userInfo;
  }

  private requestErrorHandler(error:any) : void {
    console.error(error);
  }

  private onGoogleAuthorized(user: GoogleUser) : void {

    let token = user.getAuthResponse().id_token;

    this.userService.authUser(token).subscribe(  (userInfo: UserInfo) => this.handleUserInfo(userInfo) , this.requestErrorHandler);
  }

}