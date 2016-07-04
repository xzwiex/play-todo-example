import { Component, OnInit, NgZone } from '@angular/core';
import { UserService } from '../../services/user.service/user.service';
import { GoogleSignIn } from '../google-sign-in/google-sign-in.component'
import { TodoListComponent } from '../todo.list/todo.list.component'
import { UserInfo } from "../../model/user.info";
import {NgIf} from "@angular/common";

import GoogleUser = gapi.auth2.GoogleUser;

@Component({
  selector: 'application',
  templateUrl: './app.template.html',
  directives : [GoogleSignIn, NgIf, TodoListComponent],
  providers : [UserService]
})

export class AppComponent implements OnInit {

  userInfo : UserInfo;

  private id : string;

  constructor(private userService: UserService, private zone: NgZone) {}

  ngOnInit() {

    console.debug('Init MyApp');

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

  onGoogleAuthorized(event) {

    const user: GoogleUser = event.value;

    let token = user.getAuthResponse().id_token;

    this.zone.run( () => {
      this.userService.authUser(token).subscribe(  (userInfo: UserInfo) => this.handleUserInfo(userInfo) , this.requestErrorHandler);
    });


  }

}