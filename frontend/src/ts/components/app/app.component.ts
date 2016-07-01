import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service/user.service';

@Component({
  selector: 'my-app',
  template: require('./app.template'),
  providers : [UserService]
})
export class AppComponent implements OnInit{
  constructor(private userService: UserService) {

  }

  ngOnInit(){
    console.log('Init MyApp');
    this.userService.getUserInfo().subscribe(
        userInfo => console.log(userInfo),
        error =>  console.log(error));
  }
}