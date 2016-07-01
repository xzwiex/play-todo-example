import { Component } from '@angular/core';
import { UserService } from 'services/user.service/user.service';

@Component({
  selector: 'my-app',
  template: require('./app.template'),
  providers : [UserService]
})
export class AppComponent {
  constructor(private userService: UserService) {
    userService.getUserInfo();
  }
}