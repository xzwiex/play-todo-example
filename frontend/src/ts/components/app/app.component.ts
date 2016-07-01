import { Component } from '@angular/core';
import { HttpClient } from '/';

@Component({
  selector: 'my-app',
  template: require('./app.template'),
  providers : [HttpClient]
})
export class AppComponent {
  constructor() {}
}