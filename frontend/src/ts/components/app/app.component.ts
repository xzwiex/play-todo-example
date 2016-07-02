import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service/user.service';
import { GoogleSignIn } from '../google-sign-in/google-sign-in'
import {UserInfo} from "../../model/user.info";
import {NgIf} from "@angular/common";

@Component({
  selector: 'my-app',
  template: /*require('./app.template')*/ '<div *ngIf="userInfo?.authorized"><google-sign-in></google-sign-in></div>',
  directives : [GoogleSignIn, NgIf],
  providers : [UserService]
})

export class AppComponent implements OnInit {

  userInfo : UserInfo;

  constructor(private userService: UserService) {

  }

  ngOnInit(){
    console.log('Init MyApp');
    this.userService.getUserInfo().subscribe(
        userInfo => this.userInfo = userInfo,
        error =>  console.log(error));
  }

}