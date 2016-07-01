import { Component } from '@angular/core';

let template = require('to-string!./app.template');

@Component({
    selector : 'my-app',
    template : template
})
export class AppComponent { }